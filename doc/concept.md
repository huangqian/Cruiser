##概念

Cruiser只关注HBase的数据结构和Java对象的映射。在Cruiser中提供了HTable、Column等注解。下面针对这些注解的中内容做一些解释。

###HTable

`@HTable`注解用于类(Class)上,主要用于类和HBase的Table对应,在注解中提供了HTable的namespace、tableName、charset等元数据描述。

#### namespace
 
 namespace是对应HBase中表所处于的NameSpace中。默认的无namespace
 
#### tableName 
  
  类映射到表名称。

#### charset
 
 表中数据存储的字符集。默认的UTF-8
  
  
  
### Column

`@Column`注解用于类中字段和HBase中qualifier的映射关系,因此在`@Column`注解中,包含了columnFamily、qualifier、encode等元数据描述内容。

#### columnFamily

   表示类的字段和HBase表中的qualifier的列簇。使用Cruiser时必须填写该内容

#### qualifier
 
 表示类字段和HBase表中对应qualifier名称。使用Cruiser时必须填写该内容

#### encode

encode是一个很有意思的东西。在HBase中存储的都是二进制数据,有一些数据其可以直接转化二进制存储,也有的是先转化成其他的格式(比如String),然后在转化二进制。比如数字22,为了查看方便,有时候会将其转化字符串再转成二进制,然后存储到HBase中。对于这一类的数据,encode就会发挥左右了,我们认为是数组被编码为字符串后再转化为二进制,在取出来的数据就必须进行转码。