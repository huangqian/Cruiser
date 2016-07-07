package ssy.dmp.cruiser.session;

import org.apache.hadoop.conf.Configuration;

/**
 * Session工厂
 *
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:04
 */
public interface SessionFactory {

    public Session openSession();

    public Session openSession(Configuration configuration);

    public Configuration getConfiguration();

}
