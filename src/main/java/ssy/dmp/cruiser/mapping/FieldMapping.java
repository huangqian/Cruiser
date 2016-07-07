package ssy.dmp.cruiser.mapping;

import ssy.dmp.cruiser.enumeration.Encode;
import ssy.dmp.cruiser.type.TypeHandler;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午10:12
 */
public class FieldMapping {

    private Field field;
    private String qualifier;
    private byte[] columnFamily;
    private Class<?> fieldType;
    private Encode encode;
    private TypeHandler<?> typeHandler;

    public FieldMapping() {
    }

    public Field getField() {
	return field;
    }

    public void setField(Field field) {
	this.field = field;
    }

    public String getQualifier() {
	return qualifier;
    }

    public void setQualifier(String qualifier) {
	this.qualifier = qualifier;
    }

    public byte[] getColumnFamily() {
	return columnFamily;
    }

    public void setColumnFamily(byte[] columnFamily) {
	this.columnFamily = columnFamily;
    }

    public Class<?> getFieldType() {
	return fieldType;
    }

    public void setFieldType(Class<?> fieldType) {
	this.fieldType = fieldType;
    }

    public Encode getEncode() {
	return encode;
    }

    public void setEncode(Encode encode) {
	this.encode = encode;
    }

    public TypeHandler<?> getTypeHandler() {
	return typeHandler;
    }

    public void setTypeHandler(TypeHandler<?> typeHandler) {
	this.typeHandler = typeHandler;
    }

    @Override
    public String toString() {
	return "FieldMapping{" +
		"field=" + field +
		", qualifier='" + qualifier + '\'' +
		", columnFamily=" + Arrays.toString(columnFamily) +
		", fieldType=" + fieldType +
		", encode=" + encode +
		", typeHandler=" + typeHandler +
		'}';
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;

	FieldMapping fieldMapping = (FieldMapping) o;

	if (field != null ? !field.equals(fieldMapping.field) : fieldMapping.field != null) return false;
	if (qualifier != null ? !qualifier.equals(fieldMapping.qualifier) : fieldMapping.qualifier != null) return false;
	if (!Arrays.equals(columnFamily, fieldMapping.columnFamily)) return false;
	if (fieldType != null ? !fieldType.equals(fieldMapping.fieldType) : fieldMapping.fieldType != null) return false;
	if (encode != fieldMapping.encode) return false;
	return !(typeHandler != null ? !typeHandler.equals(fieldMapping.typeHandler) : fieldMapping.typeHandler != null);

    }

    @Override
    public int hashCode() {
	int result = field != null ? field.hashCode() : 0;
	result = 31 * result + (qualifier != null ? qualifier.hashCode() : 0);
	result = 31 * result + (columnFamily != null ? Arrays.hashCode(columnFamily) : 0);
	result = 31 * result + (fieldType != null ? fieldType.hashCode() : 0);
	result = 31 * result + (encode != null ? encode.hashCode() : 0);
	result = 31 * result + (typeHandler != null ? typeHandler.hashCode() : 0);
	return result;
    }
}
