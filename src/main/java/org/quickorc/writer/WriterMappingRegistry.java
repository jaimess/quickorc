package org.quickorc.writer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.orc.TypeDescription;

public class WriterMappingRegistry {
	private Map<Class<?>, WriterMapping<Writer, TypeDescription>> registry = new HashMap<>();
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
	}
	
	public void register(Class<?> c, WriterMapping<Writer, TypeDescription> mapping) {
		registry.put(c, mapping);
	}
	
	public WriterMapping<Writer, TypeDescription>  getMapping(Class<?> c) {
		return registry.get(c);
	}
	
	public Writer getVisitor(Class<?> c) {
		return registry.get(c).getVisitor();
	}
	
	public TypeDescription getTypeDescription(Class<?> c) {
		return registry.get(c).getTypeDescription().clone();
	}
}
