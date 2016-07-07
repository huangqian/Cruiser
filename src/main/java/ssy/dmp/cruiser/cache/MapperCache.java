package ssy.dmp.cruiser.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import ssy.dmp.cruiser.mapping.Mapper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/22
 * Time: 下午3:09
 */
public class MapperCache {

    private static final MapperCache instance = new MapperCache();

    private Cache<String,Mapper> mapperCache = CacheBuilder.newBuilder()
	    .build();



    public void put(String id, Mapper mapper){
	this.mapperCache.put(id,mapper);
    }

    public Mapper get(String id, Callable<? extends Mapper> valueLoader) throws ExecutionException {
	return this.mapperCache.get(id,valueLoader);
    }

    private MapperCache() {
    }

    public static MapperCache getMapperCache(){
	return instance;
    }
}
