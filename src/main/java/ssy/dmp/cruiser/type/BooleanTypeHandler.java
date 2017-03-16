package ssy.dmp.cruiser.type;

import org.apache.commons.lang.BooleanUtils;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:42
 */
public class BooleanTypeHandler extends TypeHandler<Boolean> {

    @Override
    public Boolean getValue(byte[] source) {
	return Bytes.toBoolean(source);
    }

    @Override
    public Boolean getValue(String source) {
	return BooleanUtils.toBoolean(source);
    }

    @Override
    public Boolean defaultValue() {
        return null;
    }

    @Override
    public Boolean getValue(Object value) {
        return null;
    }

    @Override
    public byte[] toBytes(Object value) {
        return Bytes.toBytes((Boolean) value);
    }
}
