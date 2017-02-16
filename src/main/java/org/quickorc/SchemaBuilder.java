package org.quickorc;

import java.lang.reflect.Field;

import org.apache.orc.TypeDescription;

public class SchemaBuilder {
	TypeDescription td;
	
	public SchemaBuilder(int colLength) {
		td = TypeDescription.createStruct();
	}
	
	public SchemaBuilder append(Field f) {
		td.addField(f.getName(), resolveType(f.getType()));
		return this;
	}
	
	TypeDescription resolveType(Class<?> c) {
		switch (c.getTypeName()) {
			case "java.lang.String": return TypeDescription.createString();
			case "java.lang.Long" : return TypeDescription.createLong();
			case "long" : return TypeDescription.createLong();
			case "java.lang.Integer" : return TypeDescription.createInt();
			case "int" : return TypeDescription.createInt();
			case "java.util.Date" : return TypeDescription.createDate();
			default: return null;
		}
	}
	
	public TypeDescription build() {
		return td;
	}
}
