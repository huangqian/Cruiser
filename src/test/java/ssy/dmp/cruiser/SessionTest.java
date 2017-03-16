package ssy.dmp.cruiser;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Scan;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import ssy.dmp.cruiser.encode.DecodeException;
import ssy.dmp.cruiser.encode.EncodeException;
import ssy.dmp.cruiser.session.DefaultSessionFactory;
import ssy.dmp.cruiser.session.Session;
import ssy.dmp.cruiser.session.SessionFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/22
 * Time: 下午5:03
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class SessionTest {

	private SessionFactory sessionFactory;
	private Configuration configuration;

	@Before
	public void init() {
		this.sessionFactory = new DefaultSessionFactory();
		configuration = HBaseConfiguration.create();
		//替换zookeeper config
		configuration.set("hbase.zookeeper.quorum", "${zk.host}");
		configuration.set("hbase.zookeeper.property.clientPort", "${zk.client.port}");
	}

	@Test
	public void testGetRowKeys() throws IOException {
		String tableName = "hbase_test";
		Session session = this.sessionFactory.openSession(configuration);
		Scan scan = new Scan();
		List<String> rowKeys = session.getRowKeyStringList(scan, tableName);
		for (String rowKey : rowKeys) {
			System.out.println(rowKey);
		}
	}

	@Test
	public void testGet() throws InvocationTargetException,
			ExecutionException, InstantiationException, IllegalAccessException, IOException, DecodeException {
		Session session = this.sessionFactory.openSession(configuration);
		String date = "20160620";
		String rowKey = genRowKey(24039, "99817749", date);
		System.out.println(rowKey);
		Get get = new Get(rowKey.getBytes(Charset.forName("UTF-8")));
		ProductCTR bean = session.get(get, ProductCTR.class);
		System.out.println(bean);

	}

	@Test
	public void testGetList() throws InvocationTargetException,
			ExecutionException, InstantiationException, IllegalAccessException, IOException, DecodeException {
		Session session = this.sessionFactory.openSession(configuration);
		//计算查询天数
		DateTime start = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20160620");
		DateTime end = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20160621");
		Days days = Days.daysBetween(start, end);

		//构建GetList
		List<Get> getList = Lists.newArrayList();
		String date;
		String rowKey;
		for (int i = 0; i <= days.getDays(); i++) {
			date = start.plusDays(i).toString("yyyyMMdd");
			rowKey = genRowKey(24039, null, date);
			System.out.println(rowKey);
			getList.add(new Get(rowKey.getBytes(Charset.forName("UTF-8"))));
		}
		//batch get
		List<ProductCTR> list = session.getList(getList, ProductCTR.class);
		for (ProductCTR productCTR : list) {
			System.out.println(productCTR.toString());
		}
	}


	private String genRowKey(long topicId, String appKey, String date) {
		StringBuilder sb = new StringBuilder();
		sb.append(topicId).append("_");
		if (StringUtils.isEmpty(appKey)) {
			sb.append("all");
		} else {
			sb.append("app=").append(appKey);
		}
		sb.append("_")
				.append(date);
		String prefix = StringUtils.substring(MD5.INSTANCE.encode2String(sb.toString()), 0, 4);
		return sb.insert(0, "_")
				.insert(0, prefix).toString();

	}


	@Test
	public void testPut() throws IllegalAccessException, ExecutionException, IOException, EncodeException {
		User user = new User();
		user.setId("1001");
		user.setName("jerry");
		user.setAge(25);
		user.setAddress("北京");
		Session session = this.sessionFactory.openSession(configuration);
		session.put(user, user.getId().getBytes());
		session.close();
	}


	@Test
	public void testBatchPut() throws IllegalAccessException, ExecutionException, IOException, EncodeException {
		final Map<byte[], User> map = Maps.newHashMap();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setId("100100" + i);
			user.setName("jerry_" + i);
			user.setAge(25 + i);
			user.setAddress("北京区,第" + i + "街道");
			map.put(user.getId().getBytes(), user);
		}
		Session session = this.sessionFactory.openSession(configuration);
		session.put(map, User.class);
		session.close();
	}


	@Test
	public void testDelete() throws IllegalAccessException, ExecutionException, IOException {
		User user = new User();
		user.setId("1001");
		user.setName("jerry");
		user.setAge(25);
		user.setAddress("北京");
		Session session = this.sessionFactory.openSession(configuration);
		session.delete(user, user.getId().getBytes());
		session.close();
	}


	@Test
	public void testBatchDelete() throws IllegalAccessException, ExecutionException, IOException {
		final Map<byte[], User> map = Maps.newHashMap();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setId("100100" + i);
			user.setName("jerry_" + i);
			user.setAge(25 + i);
			user.setAddress("北京区,第" + i + "街道");
			map.put(user.getId().getBytes(), user);
		}
		Session session = this.sessionFactory.openSession(configuration);
		session.delete(map, User.class);
		session.close();
	}
}
