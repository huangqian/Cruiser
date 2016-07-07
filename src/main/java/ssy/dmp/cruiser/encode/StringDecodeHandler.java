package ssy.dmp.cruiser.encode;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:23
 */
public class StringDecodeHandler implements DecodeHandler<String> {
    @Override
    public String decode(byte[] value) {
	return Bytes.toString(value);
    }
}
