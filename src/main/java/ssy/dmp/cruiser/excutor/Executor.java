package ssy.dmp.cruiser.excutor;

import org.apache.hadoop.conf.Configuration;
import ssy.dmp.cruiser.mapping.Mapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/27
 * Time: 下午4:31
 */
public interface Executor{

    Executor prepare(Configuration configuration, Mapper mapper) throws IOException;


    <E> void put(E e, byte[] rowKey) throws IllegalAccessException, IOException;

    <E> void put(Map<byte[], E> map) throws IllegalAccessException, IOException;


   <E> void delete(E e, byte[] rowKey) throws IOException, IllegalAccessException;

   <E> void delete(Map<byte[],E> map) throws IllegalAccessException, IOException;

    void close() throws IOException;


}
