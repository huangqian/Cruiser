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
    public String decode(byte[] value) throws DecodeException{
	try {
	    return Bytes.toString(value);
	} catch (Exception e) {
	    throw new DecodeException("HBase qualifier binary data can't decode to String, please you check encode value");
	}
    }
}
