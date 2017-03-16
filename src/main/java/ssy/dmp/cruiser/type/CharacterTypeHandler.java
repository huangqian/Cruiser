package ssy.dmp.cruiser.type;

import org.apache.commons.lang.CharUtils;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午9:37
 */
public class CharacterTypeHandler extends TypeHandler<Character> {
    @Override
    public Character getValue(byte[] source) {
	return CharUtils.toChar(Bytes.toString(source));
    }

    @Override
    public Character getValue(String source) {
	return CharUtils.toChar(source);
    }

    @Override
    public Character defaultValue() {
        return null;
    }

    @Override
    public byte[] toBytes(Object value) {
        return Bytes.toBytes((Character)value);
    }
}
