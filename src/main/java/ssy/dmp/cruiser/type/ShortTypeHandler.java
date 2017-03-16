package ssy.dmp.cruiser.type;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:35
 */
public class ShortTypeHandler extends TypeHandler<Short> {
    @Override
    public Short getValue(byte[] source) {
	return Bytes.toShort(source);
    }

    @Override
    public Short getValue(String source) {
	return NumberUtils.toShort(source);
    }

    @Override
    public Short defaultValue() {
        return 0;
    }

    @Override
    public byte[] toBytes(Object value) {
        return Bytes.toBytes((Short)value);
    }
}
