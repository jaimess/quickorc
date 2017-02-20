package org.quickorc;

import static org.junit.Assert.assertEquals;

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
		TypeDescription expected = TypeDescription.fromString("struct<string:string,intValue:int>");
		TypeDescription actual = build(AnnotatedDummyObject.class);
		assertEquals(expected, actual);
	}
}
