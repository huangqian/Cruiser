package ssy.dmp.cruiser.session;

import org.apache.hadoop.conf.Configuration;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/22
 * Time: 下午4:10
 */
public class DefaultSessionFactory implements SessionFactory {

    private Configuration configuration;

    public void setConfiguration(Configuration configuration) {
	this.configuration = configuration;
    }

    @Override
    public Session openSession() {
	return new DefaultSession(this.configuration);
    }

    @Override
    public Session openSession(Configuration configuration) {
	this.configuration = configuration;
	return new DefaultSession(configuration);
    }

    @Override
    public Configuration getConfiguration() {
	return this.configuration;
    }
}
