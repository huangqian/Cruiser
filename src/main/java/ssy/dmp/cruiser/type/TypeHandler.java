package ssy.dmp.cruiser.type;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:30
 */
public abstract class TypeHandler<T> {

    public abstract T getValue(byte[] source);

    public abstract T getValue(String source);

    public abstract T defaultValue();

    public T getValue(Object source){
        if (source == null){
            return defaultValue();
        }else if(source instanceof String){
            return getValue((String)source);
        }else if(source instanceof byte[]){
            return getValue((byte[])source);
        }else {
            throw new UnSupportType("getValue parameter type must be byte[] or String");
        }
    }

    public abstract byte[] toBytes(Object value);


}
