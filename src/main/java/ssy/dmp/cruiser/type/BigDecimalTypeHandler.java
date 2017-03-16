package ssy.dmp.cruiser.type;

import org.apache.hadoop.hbase.util.Bytes;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:45
 */
public class BigDecimalTypeHandler extends TypeHandler<BigDecimal> {

    @Override
    public BigDecimal getValue(byte[] source) {
	return Bytes.toBigDecimal(source);
    }

    @Override
    public BigDecimal getValue(String source) {
	return new BigDecimal(source);
    }

    @Override
    public BigDecimal defaultValue() {
        return null;
    }

    @Override
    public byte[] toBytes(Object value) {
        return Bytes.toBytes((BigDecimal)value);
    }

}
