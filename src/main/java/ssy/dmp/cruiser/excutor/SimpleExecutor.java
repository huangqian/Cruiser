package ssy.dmp.cruiser.excutor;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/27
 * Time: 下午5:31
 */
public class SimpleExecutor extends BaseExecutor{
    @Override
    protected void doPut(Put put, Table table) throws IOException {
	try {
	    if(isClose()){
		retryConnection();
	    }
	    table.put(put);
	} finally {
	    this.close();
	}
    }

    @Override
    protected void doPut(List<Put> putList, Table table) throws IOException {
	try {
	    if(isClose()){
		retryConnection();
	    }
	    table.put(putList);
	} finally {
	    this.close();
	}
    }

    @Override
    protected void doDelete(Delete delete, Table table) throws IOException {
	try {
	    if(isClose()){
		retryConnection();
	    }
	    table.delete(delete);
	} finally {
	    this.close();
	}
    }

    @Override
    protected void doDelete(List<Delete> deleteList, Table table) throws IOException {
	try {
	    if(isClose()){
		retryConnection();
	    }
	    table.delete(deleteList);
	} finally {
	    this.close();
	}
    }
}
