package org.quickorc;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.OrcFile;
import org.apache.orc.TypeDescription;

public class Mapper {

	public static void main(String[] args) throws Exception {
		Car c = new Car();
		new Mapper().toORC(c);
	}
	
	VectorizedRowBatch  toORC(Object obj) throws Exception {
		Map<String, Object> properties = BeanUtils.describe(obj);
		
		
		List<Field> fields = FieldUtils.getAllFieldsList(obj.getClass());
		fields.sort(new Comparator<Field>() {
			@Override
			public int compare(Field o1, Field o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		SchemaBuilder sb = new SchemaBuilder(fields.size());
		for (Field field : fields) {
			sb.append(field);
		}
		TypeDescription schema = sb.build();
		System.out.println(schema);
		
		org.apache.orc.Writer writer = OrcFile.createWriter(new Path("my-file.orc"),
                OrcFile.writerOptions(new Configuration()).setSchema(schema));
		
		VectorizedRowBatch batch = schema.createRowBatch();
		
		int colIndex = 0;
		for (Field field : fields) {
			VisitorFactory.createVisitor(batch, field.getType()).visit(colIndex, 0, FieldUtils.readField(field, obj, true));;
			
			colIndex++;
		}
		return null;
	}


}
