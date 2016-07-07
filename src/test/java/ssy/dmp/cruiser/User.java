package ssy.dmp.cruiser;

import ssy.dmp.cruiser.annotation.Column;
import ssy.dmp.cruiser.annotation.HTable;
import ssy.dmp.cruiser.enumeration.Encode;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/27
 * Time: 下午6:09
 */
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
