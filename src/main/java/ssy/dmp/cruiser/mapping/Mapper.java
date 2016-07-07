package ssy.dmp.cruiser.mapping;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午10:25
 */
public class Mapper {
    private String id;
    private Class<?> type;
    private Set<FieldMapping> fieldMappings;
    private String namespace;
    private String tableName;
    private Charset charset;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public Class<?> getType() {
	return type;
    }

    public void setType(Class<?> type) {
	this.type = type;
    }

    public Set<FieldMapping> getFieldMappings() {
	return fieldMappings;
    }

    public void setFieldMappings(Set<FieldMapping> fieldMappings) {
	this.fieldMappings = fieldMappings;
    }

    public String getNamespace() {
	return namespace;
    }

    public void setNamespace(String namespace) {
	this.namespace = namespace;
    }

    public String getTableName() {
	return tableName;
    }

    public void setTableName(String tableName) {
	this.tableName = tableName;
    }

    public Charset getCharset() {
	return charset;
    }

    public void setCharset(Charset charset) {
	this.charset = charset;
    }

    public Mapper() {
    }

    @Override
    public String toString() {
	return "Mapper{" +
		"id='" + id + '\'' +
		", type=" + type +
		", fieldMappings=" + fieldMappings +
		", namespace='" + namespace + '\'' +
		", tableName='" + tableName + '\'' +
		", charset=" + charset +
		'}';
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;

	Mapper classMapping = (Mapper) o;

	if (id != null ? !id.equals(classMapping.id) : classMapping.id != null) return false;
	if (type != null ? !type.equals(classMapping.type) : classMapping.type != null) return false;
	if (fieldMappings != null ? !fieldMappings.equals(classMapping.fieldMappings) : classMapping.fieldMappings != null)
	    return false;
	if (namespace != null ? !namespace.equals(classMapping.namespace) : classMapping.namespace != null) return false;
	if (tableName != null ? !tableName.equals(classMapping.tableName) : classMapping.tableName != null) return false;
	return !(charset != null ? !charset.equals(classMapping.charset) : classMapping.charset != null);

    }

    @Override
    public int hashCode() {
	int result = id != null ? id.hashCode() : 0;
	result = 31 * result + (type != null ? type.hashCode() : 0);
	result = 31 * result + (fieldMappings != null ? fieldMappings.hashCode() : 0);
	result = 31 * result + (namespace != null ? namespace.hashCode() : 0);
	result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
	result = 31 * result + (charset != null ? charset.hashCode() : 0);
	return result;
    }
}
