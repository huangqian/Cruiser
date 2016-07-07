## quick-start

Cruiser是一个HBase的OM(Object Mapping)框架，主要为了解决API操作的繁琐问题。

## Example

+ 创建一个User类，用于测试

```
@HTable(tableName = "hbase_user")
public class User {

    @Column(columnFamily = "T", qualifier = "name", encode = Encode.None)
    private String name;

    @Column(columnFamily = "T", qualifier = "age", encode = Encode.None)
    private int age;

    @Column(columnFamily = "T", qualifier = "id", encode = Encode.None)
    private String id;

    @Column(columnFamily = "T", qualifier = "addr", encode = Encode.None)
    private String address;


    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }
}
```

### Put

```
@RunWith(BlockJUnit4ClassRunner.class)
public class SessionTest {

    private SessionFactory sessionFactory;
    private Configuration configuration;

    @Before
    public void init(){
    	this.sessionFactory = new DefaultSessionFactory();
    	configuration = HBaseConfiguration.create();
    	configuration.set("hbase.zookeeper.quorum","zk1.com,zk2.com,zk3.com");
    	configuration.set("hbase.zookeeper.property.clientPort", "2181");
    }


    @Test
    public void testPut() throws IllegalAccessException, ExecutionException, IOException {
    	User user = new User();
    	user.setId("1001");
    	user.setName("jerry");
    	user.setAge(25);
    	user.setAddress("北京");
    	Session session = this.sessionFactory.openSession(configuration);
    	session.put(user,user.getId().getBytes());
    	session.close();
    }


    @Test
    public void testBatchPut() throws IllegalAccessException, ExecutionException, IOException {
      	final Map<byte[],User> map = Maps.newHashMap();
      	for(int i = 0 ; i < 10; i++) {
      	    User user = new User();
      	    user.setId("100100" + i);
      	    user.setName("jerry_" + i);
      	    user.setAge(25 + i);
      	    user.setAddress("北京区,第"+i+"街道");
      	    map.put(user.getId().getBytes(),user);
    	}
    	Session session = this.sessionFactory.openSession(configuration);
    	session.put(map, User.class);
    	session.close();
    }
}

```


### Delete

```
@RunWith(BlockJUnit4ClassRunner.class)
public class SessionTest {

    private SessionFactory sessionFactory;
    private Configuration configuration;

    @Before
    public void init(){
    	this.sessionFactory = new DefaultSessionFactory();
    	configuration = HBaseConfiguration.create();
    	configuration.set("hbase.zookeeper.quorum","zk1.com,zk2.com,zk3.com");
    	configuration.set("hbase.zookeeper.property.clientPort", "2181");
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
    	final Map<byte[],User> map = Maps.newHashMap();
    	for(int i = 0 ; i < 10; i++) {
    	    User user = new User();
    	    user.setId("100100" + i);
    	    user.setName("jerry_" + i);
    	    user.setAge(25 + i);
    	    user.setAddress("北京区,第"+i+"街道");
    	    map.put(user.getId().getBytes(),user);
    	}
    	Session session = this.sessionFactory.openSession(configuration);
    	session.delete(map, User.class);
    	session.close();
    }
}


```


### Get

```
@RunWith(BlockJUnit4ClassRunner.class)
public class SessionTest {

    private SessionFactory sessionFactory;
    private Configuration configuration;

    @Before
    public void init(){
    	this.sessionFactory = new DefaultSessionFactory();
    	configuration = HBaseConfiguration.create();
    	configuration.set("hbase.zookeeper.quorum","zk1.com,zk2.com,zk3.com");
    	configuration.set("hbase.zookeeper.property.clientPort", "2181");
    }



    @Test
    public void testGet() throws InvocationTargetException,
	    ExecutionException, InstantiationException, IllegalAccessException, IOException {
    	Session session = this.sessionFactory.openSession(configuration);
    	String date = "20160529";
    	String rowKey = genRowKey(12895, null, date);
    	System.out.println(rowKey);
    	Get get = new Get(rowKey.getBytes(Charset.forName("UTF-8")));
    	ProductCTR bean = session.get(get, ProductCTR.class);
    	System.out.println(bean);

    }


    @Test
    public void testGetList() throws InvocationTargetException,
	    ExecutionException, InstantiationException, IllegalAccessException, IOException {
      	Session session = this.sessionFactory.openSession(configuration);
      	//计算查询天数
      	DateTime start = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20160529");
      	DateTime end = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime("20160531");
      	Days days = Days.daysBetween(start, end);

      	//构建GetList
      	List<Get> getList = Lists.newArrayList();
      	String date;
      	String rowKey;
      	for(int i = 0; i <= days.getDays(); i++){
      	    date = start.plusDays(i).toString("yyyyMMdd");
      	    rowKey = genRowKey(12895, null, date);
      	    System.out.println(rowKey);
      	    getList.add(new Get(rowKey.getBytes(Charset.forName("UTF-8"))));
      	}
      	//batch get
      	List<ProductCTR> list = session.getList(getList, ProductCTR.class);
      	for(ProductCTR productCTR : list){
      	    System.out.println(productCTR.toString());
      	}
    }

    private String genRowKey(long topicId,String appKey, String date){
    	StringBuilder sb = new StringBuilder();
    	sb.append(topicId).append("_");
    	if(StringUtils.isEmpty(appKey)){
    	    sb.append("all");
    	}else{
    	    sb.append("app=").append(appKey);
    	}
    	sb.append("_")
    		.append(date);
    	String prefix = StringUtils.substring(MD5.INSTANCE.encode2String(sb.toString()), 0, 4);
    	return sb.insert(0,"_")
    		.insert(0,prefix).toString();

    }

}

```


### Scan

```
@RunWith(BlockJUnit4ClassRunner.class)
public class SessionTest {

    private SessionFactory sessionFactory;
    private Configuration configuration;

    @Before
    public void init(){
    	this.sessionFactory = new DefaultSessionFactory();
    	configuration = HBaseConfiguration.create();
    	configuration.set("hbase.zookeeper.quorum","zk1.com,zk2.com,zk3.com");
    	configuration.set("hbase.zookeeper.property.clientPort", "2181");
    }

    @Test
    public void testGetRowKeys() throws IOException {
    	String tableName = "hbase_dim_channel";
    	Session session = this.sessionFactory.openSession(configuration);
    	Scan scan = new Scan();
    	List<String> rowKeys = session.getRowKeyStringList(scan, tableName);
    	for(String rowKey : rowKeys){
    	    System.out.println(rowKey);
    	}
    }

}
```
