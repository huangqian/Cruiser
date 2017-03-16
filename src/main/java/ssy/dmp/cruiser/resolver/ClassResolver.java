package ssy.dmp.cruiser.resolver;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import ssy.dmp.cruiser.annotation.HTable;
import ssy.dmp.cruiser.mapping.FieldMapping;
import ssy.dmp.cruiser.mapping.Mapper;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午11:06
 */
public class ClassResolver {

    private Class<?> domainClass;

    public ClassResolver(Class<?> domainClass) {
	this.domainClass = domainClass;
    }

    public Mapper resolver(){
	HTable hTable = this.domainClass.getAnnotation(HTable.class);
	if (hTable == null) return null;
	Mapper classMapping = new Mapper();
	classMapping.setId(this.domainClass.getName().toUpperCase());
	classMapping.setNamespace(hTable.namespace());
	classMapping.setTableName(hTable.tableName());
	classMapping.setType(this.domainClass);
	classMapping.setCharset(StringUtils.isBlank(hTable.charset()) ?
		Charset.defaultCharset() : Charset.forName(hTable.charset()));
	Set<FieldMapping> fieldMappingSet = new HashSet<FieldMapping>();
	for(Class<?> cls = this.domainClass ; cls != null && !Object.class.equals(cls) ; cls = cls.getSuperclass()) {
	    fieldMappingSet.addAll(resolverFields(cls.getDeclaredFields()));
	}
	classMapping.setFieldMappings(fieldMappingSet);
	return classMapping;

    }

    protected List<FieldMapping> resolverFields(Field[] fields){
	List<FieldMapping> fieldMappings = Lists.newArrayList();
	FieldResolver fieldResolver;
	FieldMapping fieldMapping;
	for(Field field : fields){
	    fieldResolver = new FieldResolver(field);
	    fieldMapping = fieldResolver.resolver();
	    if(fieldMapping != null){
		fieldMappings.add(fieldMapping);
	    }
	}
	return fieldMappings;

    }
}
