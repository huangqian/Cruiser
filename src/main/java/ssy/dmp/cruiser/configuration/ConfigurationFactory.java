package ssy.dmp.cruiser.configuration;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/7/7
 * Time: 上午10:37
 */
public class ConfigurationFactory {

    private String zkUrl;
    private String zkPort;

    public ConfigurationFactory() {
    }

    public ConfigurationFactory(String zkUrl, String zkPort) {
        this.zkUrl = zkUrl;
        this.zkPort = zkPort;
    }

    public String getZkUrl() {
        return zkUrl;
    }

    public void setZkUrl(String zkUrl) {
        this.zkUrl = zkUrl;
    }

    public String getZkPort() {
        return zkPort;
    }

    public void setZkPort(String zkPort) {
        this.zkPort = zkPort;
    }

    public Configuration getHBaseConfiguration(){
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum",zkUrl);
        configuration.set("hbase.zookeeper.property.clientPort",zkPort);
        return configuration;
    }
}
