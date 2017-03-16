package ssy.dmp.cruiser.encode;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/27
 * Time: 上午11:46
 */
public class NoneEncodeHandler implements EncodeHandler {

    @Override
    public Object encode(Object o) throws EncodeException {
	return o;
    }
}
