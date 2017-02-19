package org.quickorc.writer;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.orc.TypeDescription;

public class WriterMappingRegistry {
	private Map<Class<?>, WriterMapping<?>> registry = new HashMap<>();
	
	public WriterMappingRegistry () {
		registry.put(int.class, new WriterMapping<>(new IntWriter(), TypeDescription.createInt()));
		registry.put(Integer.class, new WriterMapping<>(new IntWriter(), TypeDescription.createLong()));
		registry.put(long.class, new WriterMapping<>(new LongWriter(), TypeDescription.createLong()));
		registry.put(Long.class, new WriterMapping<>(new LongWriter(), TypeDescription.createLong()));
		registry.put(String.class, new WriterMapping<>(new StringWriter(), TypeDescription.createString()));
		registry.put(Date.class, new WriterMapping<>(new DateWriter(), TypeDescription.createDate()));
		registry.put(Boolean.class, new WriterMapping<>(new BooleanWriter(), TypeDescription.createBoolean()));
		registry.put(boolean.class, new WriterMapping<>(new BooleanWriter(), TypeDescription.createBoolean()));
		registry.put(double.class, new WriterMapping<>(new DoubleWriter(), TypeDescription.createDouble()));
		registry.put(Double.class, new WriterMapping<>(new DoubleWriter(), TypeDescription.createDouble()));
		registry.put(Timestamp.class, new WriterMapping<>(new TimestampWriter(), TypeDescription.createTimestamp()));
		registry.put(BigDecimal.class, new WriterMapping<>(new DecimalWriter(), TypeDescription.createDecimal()));
	}
	
	public void register(Class<?> c, WriterMapping<?> mapping) {
		registry.put(c, mapping);
	}
	
	public WriterMapping<?>  getMapping(Class<?> c) {
		return registry.get(c);
	}
	
	public Writer getWriter(Class<?> c) {
		return registry.get(c).getWriter();
	}
	
	public TypeDescription getTypeDescription(Class<?> c) {
		return registry.get(c).getTypeDescription().clone();
	}
}
