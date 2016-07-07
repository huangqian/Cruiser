package ssy.dmp.cruiser.annotation;

import ssy.dmp.cruiser.enumeration.Encode;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/3
 * Time: 上午11:56
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Column {

    /***
     * @return 列族
     */
    public String columnFamily();

    /***
     * @return 列标识
     */
    public String qualifier();

    /***
     * @return HBase中存储的类型
     */
    public Encode encode();
}
