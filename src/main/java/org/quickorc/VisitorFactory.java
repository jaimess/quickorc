package org.quickorc;

import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.TypeDescription;

public class VisitorFactory {
	static Visitor<?> createVisitor(VectorizedRowBatch batch, Class c) {
		switch (c.getTypeName()) {
		case "java.lang.String": return new StringVisitor(batch);
		case "java.lang.Long" : return new LongVisitor(batch);
		case "long" : return new LongVisitor(batch);
		case "java.lang.Integer" : return new IntVisitor(batch);
		case "int" : return new IntVisitor(batch);
//		case "java.util.Date" : return TypeDescription.createDate();
		default: return null;
	}
	}
}
