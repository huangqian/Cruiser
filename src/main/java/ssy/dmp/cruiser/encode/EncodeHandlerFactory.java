package ssy.dmp.cruiser.encode;

import ssy.dmp.cruiser.enumeration.Encode;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/27
 * Time: 上午11:53
 */
public class EncodeHandlerFactory {

    public static EncodeHandler getEncodeHandler(Encode encode){
	switch (encode){
	    case String:
		return new StringEncodeHandler();
	    case None:
	    default:
		return new NoneEncodeHandler();

	}
    }
}
