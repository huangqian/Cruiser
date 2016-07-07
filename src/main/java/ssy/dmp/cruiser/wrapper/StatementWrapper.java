package ssy.dmp.cruiser.wrapper;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.hbase.client.Mutation;
import ssy.dmp.cruiser.encode.EncodeHandler;
import ssy.dmp.cruiser.encode.EncodeHandlerFactory;
import ssy.dmp.cruiser.mapping.FieldMapping;
import ssy.dmp.cruiser.mapping.Mapper;
import ssy.dmp.cruiser.type.TypeHandler;

import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/27
 * Time: 下午2:29
 */
public abstract class StatementWrapper<T extends Mutation> {

    protected Mapper mapper;


    public byte[] getValue(FieldMapping fieldMapping, Object object) throws IllegalAccessException {
        if(fieldMapping == null) return null;
        EncodeHandler encodeHandler = EncodeHandlerFactory.getEncodeHandler(fieldMapping.getEncode());
        fieldMapping.getField().setAccessible(true);
        Object fieldValue = fieldMapping.getField().get(object);
        Object value = encodeHandler.encode(fieldValue);
        return fieldMapping.getTypeHandler().toBytes(value);
    }

    public abstract  <E> T from(E obj, byte[] rowKey) throws IllegalAccessException;

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public Mapper getMapper() {
        return mapper;
    }

    public abstract String getNameSpace();

    public abstract String getTableName();

    public abstract Charset getCharset();


}
