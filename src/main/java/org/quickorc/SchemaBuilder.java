package org.quickorc;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.orc.TypeDescription;
import org.quickorc.writer.WriterMappingRegistry;

public class SchemaBuilder {
	
	public TypeDescription build(Class<?> clazz) {
		TypeDescription td = TypeDescription.createStruct();
		List<Field> fields = FieldUtils.getAllFieldsList(clazz);
		fields.sort(new Comparator<Field>() {
			@Override
			public int compare(Field o1, Field o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		WriterMappingRegistry writerMappingRegistry = new WriterMappingRegistry();
		for (Field field : fields) {
			td.addField(field.getName(), writerMappingRegistry.getTypeDescription(field.getType()));
		}
		return td;
	}
}
