package ssy.dmp.cruiser.type;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:47
 */
public class ByteArrayTypeHandler extends TypeHandler<byte[]> {
    @Override
    public byte[] getValue(byte[] source) {
	return source;
    }

    @Override
    public byte[] getValue(String source) {
	return source.getBytes();
    }

    @Override
    public byte[] toBytes(Object value) {
        return (byte[])value;
    }
}
