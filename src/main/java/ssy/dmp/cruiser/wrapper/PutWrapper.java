package ssy.dmp.cruiser.wrapper;

import org.apache.hadoop.hbase.client.Put;
import ssy.dmp.cruiser.encode.EncodeException;
import ssy.dmp.cruiser.mapping.FieldMapping;
import ssy.dmp.cruiser.mapping.Mapper;

import java.nio.charset.Charset;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/27
 * Time: 下午2:33
 */
public class PutWrapper extends StatementWrapper<Put> {

    public PutWrapper(Mapper mapper) {
        this.setMapper(mapper);
    }


    @Override
    public <E> Put from(E obj, byte[] rowKey) throws IllegalAccessException, EncodeException {
        Put put = new Put(rowKey);
        byte[] value;
        byte[] qualifier;
        for(FieldMapping fieldMapping : this.mapper.getFieldMappings()){
            value = this.getValue(fieldMapping,obj);
            if(value != null) {
                qualifier = fieldMapping.getQualifier().getBytes(mapper.getCharset());
                put.addColumn(fieldMapping.getColumnFamily(), qualifier, value);
            }
        }
	return put;
    }



    @Override
    public String getNameSpace() {
        return this.getMapper().getNamespace();
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
