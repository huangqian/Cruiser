package ssy.dmp.cruiser.encode;

import org.apache.hadoop.hbase.client.Result;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:16
 */
public interface DecodeHandler<T> {

    public T decode(byte[] value);
}
