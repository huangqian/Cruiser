package ssy.dmp.cruiser.session;

import org.apache.hadoop.hbase.client.*;
import ssy.dmp.cruiser.encode.DecodeException;
import ssy.dmp.cruiser.encode.EncodeException;
import ssy.dmp.cruiser.mapping.Mapper;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * HBase的操作Session
 * <p>
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午6:28
 */
public interface Session extends Closeable {

	/***
	 * 获取一行数据
	 *
	 * @param get HBase的Get对象
	 * @param <T> 返回数据的类型
	 * @return 返回HBase存储的数据
	 */
	<T> T get(Get get, Class<T> type) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException, ExecutionException, DecodeException;


	/***
	 * 批量获取一组数据
	 *
	 * @param gets Get对象集合
	 * @param <T>  返回值的类型
	 * @return 返回从HBase Get到的数据集合
	 */
	<T> List<T> getList(List<Get> gets, Class<T> type) throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException, ExecutionException, DecodeException;


	/***
	 * 扫描数据
	 *
	 * @param scan 扫描对象
	 * @param <T>  返回对象的类型
	 * @return 返回扫描的数据集合
	 */
	<T> List<T> scan(Scan scan, Class<T> type) throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException, ExecutionException, DecodeException;

	List<String> getRowKeyStringList(Scan scan, String tableName) throws IOException;


	@Override
	void close() throws IOException;

	/***
	 * 向HBase中put一个对象
	 *
	 * @param e   被HBase存储的对象
	 * @param <E> t的类型
	 * @return 如果成功put到HBase返回true, 否则返回false.
	 */
	<E> void put(E e, byte[] rowKey) throws IOException, ExecutionException, IllegalAccessException, EncodeException;

	<E> void put(E e, String rowKey) throws IOException, IllegalAccessException, ExecutionException, EncodeException;


	<E> void put(Map<byte[], E> map, Class<E> type) throws IOException, IllegalAccessException, ExecutionException, EncodeException;

	<E> void delete(E e, byte[] rowKey) throws IOException, IllegalAccessException, ExecutionException;


	<E> void delete(Map<byte[], E> map, Class<E> type)
			throws IOException, IllegalAccessException, ExecutionException;

	<E> void delete(E e, String rowKey) throws IOException, IllegalAccessException, ExecutionException;


	Mapper getMapper(Class<?> type) throws ExecutionException;

	ResultScanner scan(Scan scan, String tableName) throws IOException;

	Result[] get(List<Get> gets, String tableName) throws IOException;

	Result get(Get get, String tableName) throws IOException;




}
