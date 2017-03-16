package ssy.dmp.cruiser.result;

import org.apache.hadoop.hbase.client.Result;
import ssy.dmp.cruiser.encode.DecodeException;
import ssy.dmp.cruiser.mapping.Mapper;

import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/22
 * Time: 上午9:57
 */
public interface ResultHandler {

    void init(Mapper mapper);

    <T> T handle(Result result) throws IllegalAccessException, InvocationTargetException, InstantiationException, DecodeException;

}
