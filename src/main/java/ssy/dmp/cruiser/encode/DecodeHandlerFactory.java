package ssy.dmp.cruiser.encode;

import ssy.dmp.cruiser.enumeration.Encode;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/22
 * Time: 上午11:10
 */
public class DecodeHandlerFactory {

    public static DecodeHandler<?> getDecodeHandler(Encode encode){
	switch (encode){
	    case String:
		return new StringDecodeHandler();
	    case None:
	    default:
		return new NoneDecodeHandler();

	}
    }
}
