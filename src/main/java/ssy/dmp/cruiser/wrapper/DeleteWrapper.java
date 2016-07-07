package ssy.dmp.cruiser.wrapper;

import org.apache.hadoop.hbase.client.Delete;
import ssy.dmp.cruiser.mapping.FieldMapping;
import ssy.dmp.cruiser.mapping.Mapper;

import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/27
 * Time: 下午4:22
 */
public class DeleteWrapper extends StatementWrapper<Delete> {

    public DeleteWrapper(Mapper mapper) {
        this.setMapper(mapper);
    }

    @Override
    public <E> Delete from(E obj, byte[] rowKey) throws IllegalAccessException {
	Delete delete = new Delete(rowKey);
	byte[] qualifier;
	for(FieldMapping fieldMapping : this.mapper.getFieldMappings()){
	    qualifier = fieldMapping.getQualifier().getBytes(mapper.getCharset());
	    delete.addColumn(fieldMapping.getColumnFamily(), qualifier);
	}
	return delete;
    }


    @Override
    public String getNameSpace() {
	return this.mapper.getNamespace();
    }

    @Override
    public String getTableName() {
	return this.mapper.getTableName();
    }

    @Override
    public Charset getCharset() {
	return this.mapper.getCharset();
    }
}
