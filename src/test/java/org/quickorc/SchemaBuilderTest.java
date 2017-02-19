package org.quickorc;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Comparator;

import org.apache.orc.TypeDescription;
import org.junit.Test;

public class SchemaBuilderTest extends SchemaBuilder {

	@Test
	public void testBuildClassOfQ() {
		TypeDescription expected = TypeDescription.fromString("struct<bigDecimal:decimal(38,10),"
				+ "booleanValue:boolean,date:date,doubleValue:double,intValue:int,longValue:bigint,"
				+ "string:string,timestamp:timestamp>");
		TypeDescription actual = build(DummyObject.class);
		assertEquals(expected, actual);
	}

	@Test
	public void testBuildClassOfQComparatorOfField() {
		TypeDescription expected = TypeDescription.fromString("struct<timestamp:timestamp,"
				+ "string:string,longValue:bigint,intValue:int,doubleValue:double,date:date,booleanValue:boolean,"
				+ "bigDecimal:decimal(38,10)>");
		TypeDescription actual = build(DummyObject.class, new AlphabeticalInvertedComparator());
		assertEquals(expected, actual);
	}

	private class AlphabeticalInvertedComparator implements Comparator<Field> {
		@Override
		public int compare(Field o1, Field o2) {
			return -1 * o1.getName().compareTo(o2.getName());
		}
	}
}
