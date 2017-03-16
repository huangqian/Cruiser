package ssy.dmp.cruiser.type;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:33
 */
public class IntegerTypeHandler extends TypeHandler<Integer> {

    @Override
    public Integer getValue(byte[] source) {
	return Bytes.toInt(source);
    }

    @Override
    public Integer getValue(String source) {
	return NumberUtils.toInt(source);
    }

    @Override
    public Integer defaultValue() {
        return 0;
    }

    @Override
    public byte[] toBytes(Object value) {
        return Bytes.toBytes((Integer)value);
    }
}
