package ssy.dmp.cruiser.excutor;

import org.apache.hadoop.conf.Configuration;
import ssy.dmp.cruiser.mapping.Mapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/27
 * Time: 下午5:55
 */
public class ExecutorFactory {

    public static Executor getExecutor(Configuration configuration, Mapper mapper) throws IOException {
	Executor executor = new SimpleExecutor();
	executor.prepare(configuration, mapper);
	return executor;
    }

}
