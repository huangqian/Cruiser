package ssy.dmp.cruiser.excutor;

import com.google.common.collect.Lists;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import ssy.dmp.cruiser.mapping.Mapper;
import ssy.dmp.cruiser.wrapper.DeleteWrapper;
import ssy.dmp.cruiser.wrapper.PutWrapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/27
 * Time: 下午4:58
 */
public abstract class BaseExecutor implements Executor{

    protected Configuration configuration;
    protected Connection connection;
    protected Table table;
    protected Mapper mapper;


    @Override
    public Executor prepare(Configuration configuration, Mapper mapper) throws IOException {
	this.configuration = configuration;
	this.connection = ConnectionFactory.createConnection(configuration);
	TableName tableName = TableName.valueOf(mapper.getTableName());
	this.table = connection.getTable(tableName);
	this.mapper = mapper;
	return this;
    }

    protected void retryConnection() throws IOException {
	this.connection = ConnectionFactory.createConnection(configuration);
	TableName tableName = TableName.valueOf(mapper.getTableName());
	this.table = connection.getTable(tableName);
    }

    @Override
    public <E> void put(E e, byte[] rowKey) throws IllegalAccessException, IOException {
	final PutWrapper putWrapper = new PutWrapper(this.mapper);
	Put put = putWrapper.from(e, rowKey);
	this.doPut(put, table);
    }

    @Override
    public <E> void put(Map<byte[], E> map) throws IllegalAccessException, IOException {
	final PutWrapper putWrapper = new PutWrapper(this.mapper);
	List<Put> puts = Lists.newArrayList();
	for(Map.Entry<byte[],E> entry : map.entrySet()){
	    puts.add(putWrapper.from(entry.getValue(),entry.getKey()));
	}
	this.doPut(puts, table);
    }

    @Override
    public <E> void delete(E e, byte[] rowKey) throws IOException, IllegalAccessException {
	final DeleteWrapper deleteWrapper = new DeleteWrapper(this.mapper);
	Delete delete = deleteWrapper.from(e, rowKey);
	this.doDelete(delete, table);
    }

    @Override
    public <E> void delete(Map<byte[], E> map) throws IllegalAccessException, IOException {
	final DeleteWrapper deleteWrapper = new DeleteWrapper(this.mapper);
	List<Delete> deletes = Lists.newArrayList();
	for(Map.Entry<byte[],E> entry : map.entrySet()){
	    deletes.add(deleteWrapper.from(entry.getValue(),entry.getKey()));
	}
	this.doDelete(deletes, table);
    }

    @Override
    public void close() throws IOException {
	if(this.connection != null){
	    this.connection.close();
	}
    }

    public boolean isClose(){
	return connection == null || connection.isClosed() || table == null ;
    }

    protected abstract void doPut(Put put, Table table) throws IOException;

    protected abstract void doPut(List<Put> putList, Table table) throws IOException;

    protected abstract void doDelete(Delete delete, Table table) throws IOException;

    protected abstract void doDelete(List<Delete> deleteList, Table table) throws IOException;
}
