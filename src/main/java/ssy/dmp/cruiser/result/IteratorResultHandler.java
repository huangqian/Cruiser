package ssy.dmp.cruiser.result;

import org.apache.hadoop.hbase.client.Result;
import ssy.dmp.cruiser.encode.DecodeException;
import ssy.dmp.cruiser.mapping.Mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/22
 * Time: 上午9:59
 */
public interface IteratorResultHandler {

    public void init(Mapper mapper);

    <T> List<T> handle(Iterable<Result> iterable) throws IllegalAccessException, InstantiationException, InvocationTargetException, DecodeException;

    <T> List<T> handle(Result ... results) throws IllegalAccessException, InstantiationException, InvocationTargetException, DecodeException;
}
