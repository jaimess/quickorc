package org.quickorc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.TypeDescription;
import org.quickorc.writer.WriterMappingRegistry;

public class QuickOrcWriter {

	protected Map<Class<?>, Map<String, Field>> fieldsCache = new HashMap<>();
	private TypeDescription typeDescription;
	protected WriterMappingRegistry writerMappingRegistry = new WriterMappingRegistry();
	
	public QuickOrcWriter(TypeDescription typeDescription, WriterMappingRegistry writerMappingRegistry) {
		this.writerMappingRegistry = writerMappingRegistry;
		this.typeDescription = typeDescription;
	}

	public VectorizedRowBatch writeObject(Object obj, VectorizedRowBatch batch, int rowIndex)
			throws IllegalAccessException, Exception {
		
		Map<String, Field> fields = getFields(obj.getClass());
		List<String> tdNames = typeDescription.getFieldNames();
		
		int colIndex = 0;
		for (String td : tdNames) {
			Field field = fields.get(td);
			writerMappingRegistry.getVisitor(field.getType()).write(batch, colIndex, rowIndex,
					FieldUtils.readField(field, obj, true));
			colIndex++;
		}
		return batch;
	}

	protected Map<String, Field> getFields(Class<?> clazz) {
		Map<String, Field> fieldMap = fieldsCache.get(clazz);

		if (fieldMap == null) {
			fieldMap = new HashMap<>();
			List<Field> fields = FieldUtils.getAllFieldsList(clazz);
			for (Field field : fields) {
				fieldMap.put(field.getName(), field);
			}
		}
		return fieldMap;
	}
}
