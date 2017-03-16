package ssy.dmp.cruiser;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 2016-08-11
 * Time: 09:41
 */
public class ReflectionTest {

    @Test
    public void testClassName(){
	System.out.println("name="+User.class.getName());
	System.out.println("canonicalName="+User.class.getCanonicalName());
    }
}
