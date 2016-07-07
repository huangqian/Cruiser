package ssy.dmp.cruiser.encode;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/27
 * Time: 上午11:38
 */
public interface EncodeHandler {

    public <E,T> T encode(E e);

}
