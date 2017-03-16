package ssy.dmp.cruiser.encode;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:16
 */
public interface DecodeHandler<T> {

    public T decode(byte[] value)throws DecodeException;
}
