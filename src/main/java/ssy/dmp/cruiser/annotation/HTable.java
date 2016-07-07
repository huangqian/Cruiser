package ssy.dmp.cruiser.annotation;

import java.lang.annotation.*;

/**
 * HBase Table注解
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/3
 * Time: 上午11:54
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HTable {

    public String namespace() default "";

    public String tableName();

    public String charset() default "UTF-8";
}
