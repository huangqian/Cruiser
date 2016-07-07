package ssy.dmp.cruiser.result;

import ssy.dmp.cruiser.mapping.Mapper;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/22
 * Time: 下午2:26
 */
public class ResultHandlerFactory {

    public static ResultHandler getResultHandler(Mapper mapper){
        ResultHandler handler = new DefaultResultHandler();
        handler.init(mapper);
	return handler;
    }

    public static IteratorResultHandler createIteratorResultHandler(Mapper mapper){
        IteratorResultHandler iteratorResultHandler = new DefaultIteratorResultHandler();
        iteratorResultHandler.init(mapper);
        return iteratorResultHandler;
    }

}
