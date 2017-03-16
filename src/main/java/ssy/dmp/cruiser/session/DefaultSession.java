package ssy.dmp.cruiser.session;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import ssy.dmp.cruiser.cache.MapperCache;
import ssy.dmp.cruiser.encode.DecodeException;
import ssy.dmp.cruiser.encode.EncodeException;
import ssy.dmp.cruiser.excutor.Executor;
import ssy.dmp.cruiser.excutor.ExecutorFactory;
import ssy.dmp.cruiser.mapping.Mapper;
import ssy.dmp.cruiser.resolver.ClassResolver;
import ssy.dmp.cruiser.result.ResultHandlerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/22
 * Time: 下午2:54
 */
public class DefaultSession implements Session {


	private Configuration configuration;
	private Connection connection;


	public DefaultSession() {
	}

	public DefaultSession(Configuration configuration) {
		this.configuration = configuration;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public Connection getConnection() throws IOException {
		if (this.connection == null) {
			this.connection = ConnectionFactory.createConnection(configuration);
		}
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void closeConnection() throws IOException {
		if (connection != null)
			connection.close();
	}

	/***
	 * 获取一行数据
	 *
	 * @param get HBase的Get对象
	 * @return 返回HBase存储的数据
	 */
	@Override
	public <T> T get(Get get, Class<T> type) throws IOException,
			IllegalAccessException, InstantiationException, InvocationTargetException, ExecutionException, DecodeException {
		final Mapper mapper = this.getMapper(type);
		Result result = get(get, mapper.getTableName());
		return ResultHandlerFactory.getResultHandler(mapper).handle(result);
	}


	@Override
	public Result get(Get get, String tableName) throws IOException {
		validateTableName(tableName);
		TableName tableNameObject = TableName.valueOf(tableName);
		Table table = getConnection().getTable(tableNameObject);
		return table.get(get);
	}

	/***
	 * 批量获取一组数据
	 *
	 * @param gets Get对象集合
	 * @return 返回从HBase Get到的数据集合
	 */
	@Override
	public <T> List<T> getList(List<Get> gets, Class<T> type)
			throws IOException,
			IllegalAccessException,
			InvocationTargetException,
			InstantiationException,
			ExecutionException,
			DecodeException {
		final Mapper mapper = this.getMapper(type);
		Result[] results = get(gets, mapper.getTableName());
		return ResultHandlerFactory.createIteratorResultHandler(mapper)
				.handle(results);
	}


	/***
	 * HBase 批量Get操作。
	 * HBase的原始操作Result,自己转化数据
	 *
	 * @param gets      Get对象集合
	 * @param tableName 表名称
	 * @return 返回Get的对应的结果集
	 * @throws IOException
	 */
	@Override
	public Result[] get(List<Get> gets, String tableName) throws IOException {
		validateTableName(tableName);
		TableName tableNameObject = TableName.valueOf(tableName);
		Table table = getConnection().getTable(tableNameObject);
		return table.get(gets);
	}

	/***
	 * 扫描数据
	 *
	 * @param scan 扫描对象
	 * @return 返回扫描的数据集合
	 */
	@Override
	public <T> List<T> scan(Scan scan, Class<T> type)
			throws IOException,
			IllegalAccessException,
			InvocationTargetException,
			InstantiationException,
			ExecutionException,
			DecodeException {

		final Mapper mapper = this.getMapper(type);
		ResultScanner resultScanner = scan(scan, mapper.getTableName());
		List<T> list = ResultHandlerFactory.createIteratorResultHandler(mapper)
				.handle(resultScanner);
		//close ResultScanner release Resources
		resultScanner.close();
		return list;
	}


	/***
	 * 扫描表
	 *
	 * @param scan      扫描器
	 * @param tableName 表名称
	 * @return ResultScanner
	 * @throws IOException
	 */
	@Override
	public ResultScanner scan(Scan scan, String tableName) throws IOException {
		validateTableName(tableName);
		TableName tableNameObj = TableName.valueOf(tableName.trim());
		Table table = getConnection().getTable(tableNameObj);
		return table.getScanner(scan);
	}

	private void validateTableName(String tableName) {
		if (StringUtils.isBlank(tableName)) {
			throw new IllegalArgumentException("tableName can not be blank or empty!");
		}
	}

	@Override
	public List<String> getRowKeyStringList(Scan scan, String hTableName) throws IOException {

		TableName tableName = TableName.valueOf(hTableName);
		Table table = getConnection().getTable(tableName);
		ResultScanner resultScanner = table.getScanner(scan);

		List<String> rowKeys = Lists.newArrayList();
		for (Result result : resultScanner) {
			rowKeys.add(Bytes.toString(result.getRow()));
		}
		//close ResultScanner release Resource
		resultScanner.close();
		
		return rowKeys;
	}


	@Override
	public Mapper getMapper(final Class<?> type) throws ExecutionException {
		String id = type.getName().toUpperCase();
		Callable<? extends Mapper> mapperResolverCallable = new Callable<Mapper>() {
			@Override
			public Mapper call() throws Exception {
				ClassResolver classResolver = new ClassResolver(type);
				return classResolver.resolver();
			}
		};
		return MapperCache.getMapperCache()
				.get(id, mapperResolverCallable);
	}


	@Override
	public void close() throws IOException {
		this.closeConnection();
	}

	/***
	 * 向HBase中put一个对象
	 *
	 * @param e      被HBase存储的对象
	 * @param rowKey
	 * @return 如果成功put到HBase返回true, 否则返回false.
	 */
	@Override
	public <E> void put(E e, byte[] rowKey) throws IOException, IllegalAccessException, ExecutionException, EncodeException {
		final Executor executor = ExecutorFactory
				.getExecutor(this.configuration, this.getMapper(e.getClass()));
		executor.put(e, rowKey);
	}

	@Override
	public <E> void put(E e, String rowKey)
			throws IOException, IllegalAccessException, ExecutionException, EncodeException {
		final Executor executor = ExecutorFactory
				.getExecutor(this.configuration, this.getMapper(e.getClass()));
		byte[] bytes = rowKey.getBytes();
		executor.put(e, bytes);
	}

	@Override
	public <E> void put(Map<byte[], E> map, Class<E> type) throws IOException, IllegalAccessException, ExecutionException, EncodeException {
		final Executor executor = ExecutorFactory
				.getExecutor(this.configuration, this.getMapper(type));
		executor.put(map);
	}

	@Override
	public <E> void delete(E e, byte[] rowKey)
			throws IOException, IllegalAccessException, ExecutionException {
		final Executor executor = ExecutorFactory
				.getExecutor(this.configuration, this.getMapper(e.getClass()));
		executor.delete(e, rowKey);
	}

	@Override
	public <E> void delete(Map<byte[], E> map, Class<E> type)
			throws IOException, IllegalAccessException, ExecutionException {
		final Executor executor = ExecutorFactory
				.getExecutor(this.configuration, this.getMapper(type));
		executor.delete(map);

	}

	@Override
	public <E> void delete(E e, String rowKey) throws IOException, IllegalAccessException, ExecutionException {
		final Executor executor = ExecutorFactory
				.getExecutor(this.configuration, this.getMapper(e.getClass()));
		byte[] bytes = rowKey.getBytes();
		executor.delete(e, bytes);

	}
}
