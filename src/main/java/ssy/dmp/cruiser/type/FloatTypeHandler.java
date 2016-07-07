package ssy.dmp.cruiser.type;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:39
 */
public class FloatTypeHandler extends TypeHandler<Float> {

    @Override
    public Float getValue(byte[] source) {
	return Bytes.toFloat(source);
    }

    @Override
    public Float getValue(String source) {
	return NumberUtils.toFloat(source);
    }

    @Override
    public byte[] toBytes(Object value) {
        return Bytes.toBytes((Float)value);
    }
}
