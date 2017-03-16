package ssy.dmp.cruiser.type;

import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午10:49
 */
public class TypeHandlerFactory {

    public static TypeHandler<?> getTypeHandler(Type type){
	if(type == short.class || type == Short.class){
	    return new ShortTypeHandler();
	}else if(type == int.class || type == Integer.class){
	    return new IntegerTypeHandler();
	}else if(type == long.class || type == Long.class){
	    return new LongTypeHandler();
	}else if(type == char.class || type == Character.class){
	    return new CharacterTypeHandler();
	}else if(type == float.class || type == Float.class){
	    return new FloatTypeHandler();
	}else if(type == double.class || type == Double.class ){
	    return new DoubleTypeHandler();
	}else if( type == BigDecimal.class){
	    return new BigDecimalTypeHandler();
	}else if(type == boolean.class || type == Boolean.class){
	    return new BooleanTypeHandler();
	}else if(type == java.lang.String.class){
	    return new StringTypeHandler();
	}else{
	    throw new UnSupportTypeHandlerException("UnSupport type exception");
	}

    }
}
