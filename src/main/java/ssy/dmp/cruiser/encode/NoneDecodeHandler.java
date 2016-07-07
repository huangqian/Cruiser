package ssy.dmp.cruiser.encode;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/22
 * Time: 上午11:10
 */
public class NoneDecodeHandler implements DecodeHandler<byte[]> {
    @Override
    public byte[] decode(byte[] value) {
	return value;
    }
}
