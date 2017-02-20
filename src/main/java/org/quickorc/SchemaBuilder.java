package org.quickorc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.orc.TypeDescription;
import org.quickorc.annotation.QuickOrc;
import org.quickorc.writer.WriterMappingRegistry;

public class SchemaBuilder {
	
	public TypeDescription build(Class<?> clazz) {
		TypeDescription td = TypeDescription.createStruct();
		List<Field> fields = null; 
		
		QuickOrc quickOrc = clazz.getAnnotation(QuickOrc.class);
		if (quickOrc != null) {
			fields = new ArrayList<>();
			String[] fieldNames = quickOrc.fields();
			for (int i = 0; i < fieldNames.length; i++) {
				fields.add(FieldUtils.getField(clazz, fieldNames[i], true));
			}
		} else {
			fields = FieldUtils.getAllFieldsList(clazz);
			AlphabeticalComparator comparator = new AlphabeticalComparator();
			fields.sort(comparator);
		}
		
		WriterMappingRegistry writerMappingRegistry = new WriterMappingRegistry();
		for (Field field : fields) {
			td.addField(field.getName(), writerMappingRegistry.getTypeDescription(field.getType()));
		}
		return td;
	}
	
	private class AlphabeticalComparator implements Comparator<Field> {
		@Override
		public int compare(Field o1, Field o2) {
			return o1.getName().compareTo(o2.getName());
		}
	}
}
