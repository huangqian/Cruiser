package ssy.dmp.cruiser.result;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.hbase.client.Result;
import ssy.dmp.cruiser.encode.DecodeException;
import ssy.dmp.cruiser.encode.DecodeHandler;
import ssy.dmp.cruiser.encode.DecodeHandlerFactory;
import ssy.dmp.cruiser.mapping.FieldMapping;
import ssy.dmp.cruiser.mapping.Mapper;
import ssy.dmp.cruiser.type.TypeHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ReflectPermission;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/22
 * Time: 上午10:27
 */
public class DefaultResultHandler implements ResultHandler {

	private Constructor<?> defaultConstructor;
	private Class<?> type;
	private Set<FieldMapping> fieldMappingSet;
	private Mapper mapper;

	@Override
	public void init(Mapper mapper) {
		this.type = mapper.getType();
		this.fieldMappingSet = mapper.getFieldMappings();
		this.mapper = mapper;
		addDefaultConstructor(this.type);

	}

	@Override
	public <T> T handle(Result result) throws IllegalAccessException, InvocationTargetException, InstantiationException, DecodeException {
		if (result == null || result.isEmpty()) return null;
		T obj = newInstance((Constructor<T>) defaultConstructor);
		Object fieldValue;
		byte[] bytes;
		for (FieldMapping fieldMapping : fieldMappingSet) {
			bytes = getValueFromResult(result, fieldMapping, this.mapper.getCharset());
			fieldValue = getFieldValue(bytes, fieldMapping);
			if (fieldValue != null) {
				BeanUtils.setProperty(obj, fieldMapping.getField().getName(), fieldValue);
			}
		}
		return obj;
	}

	public byte[] getValueFromResult(Result result, FieldMapping fieldMapping, Charset charset) {
		byte[] cf = fieldMapping.getColumnFamily();
		byte[] qualifier = fieldMapping.getQualifier().getBytes(charset);
		return result.getValue(cf, qualifier);
	}

	public <T> T newInstance(Constructor<T> constructor) throws IllegalAccessException, InvocationTargetException, InstantiationException {
		if (constructor != null) {
			return constructor.newInstance();
		} else {
			throw new ReflectionException("not found constructor");
		}
	}


	public Object getFieldValue(byte[] bytes, FieldMapping fieldMapping) throws DecodeException {
		if (fieldMapping == null) return null;
		TypeHandler<?> typeHandler = fieldMapping.getTypeHandler();
		DecodeHandler<?> encodeHandler = DecodeHandlerFactory.getDecodeHandler(fieldMapping.getEncode());
		return typeHandler.getValue(encodeHandler.decode(bytes));

	}


	public Constructor<?> getDefaultConstructor() {
		if (defaultConstructor != null) {
			return defaultConstructor;
		} else {
			throw new RuntimeException("Not found default constructor for " + type);
		}
	}

	public void addDefaultConstructor(Class<?> clazz) {
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		for (Constructor<?> constructor : constructors) {
			if (constructor.getParameterTypes().length == 0) {
				if (canAccessPrivateMethods()) {
					try {
						constructor.setAccessible(true);
					} catch (Exception e) {
						//Ignored
					}
				}
				if (constructor.isAccessible()) {
					this.defaultConstructor = constructor;
				}
			}
		}
	}

	private static boolean canAccessPrivateMethods() {
		try {
			SecurityManager securityManager = System.getSecurityManager();
			if (null != securityManager) {
				securityManager.checkPermission(new ReflectPermission("suppressAccessChecks"));
			}
		} catch (SecurityException e) {
			return false;
		}
		return true;
	}
}
