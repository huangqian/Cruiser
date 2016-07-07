package ssy.dmp.cruiser.type;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:40
 */
public class DoubleTypeHandler extends TypeHandler<Double> {
    @Override
    public Double getValue(byte[] source) {
	return Bytes.toDouble(source);
    }

    @Override
    public Double getValue(String source) {
	return NumberUtils.toDouble(source);
    }

    @Override
    public byte[] toBytes(Object value) {
        return Bytes.toBytes((Double)value);
    }
}
