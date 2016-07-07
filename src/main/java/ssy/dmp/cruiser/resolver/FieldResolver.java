package ssy.dmp.cruiser.resolver;

import ssy.dmp.cruiser.annotation.Column;
import ssy.dmp.cruiser.mapping.FieldMapping;
import ssy.dmp.cruiser.type.TypeHandlerFactory;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/21
 * Time: 下午10:39
 */
public class FieldResolver {

    private Field field;


    public FieldResolver(Field field) {
	this.field = field;
    }

    /***
     * 解析类的域信息
     * @return 返回域和Column的映射对象
     */
    public FieldMapping resolver(){
	Column column = this.field.getAnnotation(Column.class);
	if(column == null) return null;
	FieldMapping fieldMapping = new FieldMapping();
	fieldMapping.setColumnFamily(column.columnFamily().getBytes());
	fieldMapping.setField(field);
	fieldMapping.setFieldType(field.getType());
	fieldMapping.setEncode(column.encode());
	fieldMapping.setQualifier(column.qualifier());
	fieldMapping.setTypeHandler(TypeHandlerFactory.getTypeHandler(field.getType()));
	return fieldMapping;
    }

}
