package ssy.dmp.cruiser.type;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:41
 */
public class StringTypeHandler extends TypeHandler<String> {
    @Override
    public String getValue(byte[] source) {
	return Bytes.toString(source);
    }

    @Override
    public String getValue(String source) {
	return source;
    }

    @Override
    public String defaultValue() {
        return null;
    }

    @Override
    public byte[] toBytes(Object value) {
        return Bytes.toBytes((String)value);
    }
}
