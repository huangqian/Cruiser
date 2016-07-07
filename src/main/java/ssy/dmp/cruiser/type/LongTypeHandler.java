package ssy.dmp.cruiser.type;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:34
 */
public class LongTypeHandler extends TypeHandler<Long> {
    @Override
    public Long getValue(byte[] source) {
	return Bytes.toLong(source);
    }

    @Override
    public Long getValue(String source) {
	return NumberUtils.toLong(source);
    }

    @Override
    public byte[] toBytes(Object value) {
        return Bytes.toBytes((Long)value);
    }
}
