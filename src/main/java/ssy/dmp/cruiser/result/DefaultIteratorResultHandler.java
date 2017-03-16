package ssy.dmp.cruiser.result;

import com.google.common.collect.Lists;
import org.apache.hadoop.hbase.client.Result;
import ssy.dmp.cruiser.encode.DecodeException;
import ssy.dmp.cruiser.mapping.Mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/22
 * Time: 下午2:25
 */
public class DefaultIteratorResultHandler implements IteratorResultHandler {

	private ResultHandler resultHandler;

	@Override
	public void init(Mapper mapper) {
		this.resultHandler = ResultHandlerFactory.getResultHandler(mapper);
	}


	@Override
	public <T> List<T> handle(Iterable<Result> iterator) throws IllegalAccessException, InstantiationException, InvocationTargetException, DecodeException {
		if (iterator == null) return null;
		List<T> list = Lists.newArrayList();
		T t;
		for (Result result : iterator) {
			t = resultHandler.handle(result);
			list.add(t);
		}
		return list;
	}

	@Override
	public <T> List<T> handle(Result... results) throws IllegalAccessException, InstantiationException, InvocationTargetException, DecodeException {
		if (results == null) return null;
		List<T> list = Lists.newArrayList();
		T t;
		for (Result result : results) {
			if (!result.isEmpty()) {
				t = resultHandler.handle(result);
				list.add(t);
			}
		}
		return list;
	}
}
