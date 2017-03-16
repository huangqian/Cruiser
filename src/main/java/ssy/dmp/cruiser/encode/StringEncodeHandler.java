package ssy.dmp.cruiser.encode;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/27
 * Time: 上午11:46
 */
public class StringEncodeHandler implements EncodeHandler {

    @Override
    public <E,T> T encode(E e) throws EncodeException{
	try {

	    return (T) String.valueOf(e);
	} catch (Exception ex) {
	    throw new EncodeException(e.getClass() +" can't cast to String ");
	}
    }
}
