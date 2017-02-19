package org.quickorc.writer;

import static org.junit.Assert.assertEquals;

import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.TypeDescription;
import org.junit.Test;

public class WriterMappingRegistryTest extends WriterMappingRegistry {

	class DummyWriter implements Writer {
		@Override
		public void write(VectorizedRowBatch batch, int colIndex, int row, Object value)  {}
	}
	
	@Test
	public void testGetMapping() {
		TypeDescription typeDescription = TypeDescription.createStruct();
		DummyWriter dummyWriter = new DummyWriter();
		WriterMapping<DummyWriter> mapping = new WriterMapping<>(dummyWriter, typeDescription);
		register(int.class, mapping);
		assertEquals(mapping, getMapping(int.class));
	}

	@Test
	public void testGetWriter() {
		TypeDescription typeDescription = TypeDescription.createStruct();
		DummyWriter dummyWriter = new DummyWriter();
		WriterMapping<DummyWriter> mapping = new WriterMapping<>(dummyWriter, typeDescription);
		register(int.class, mapping);
		assertEquals(dummyWriter, getWriter(int.class));
	}

	@Test
	public void testGetTypeDescription() {
		TypeDescription typeDescription = TypeDescription.createStruct();
		DummyWriter dummyWriter = new DummyWriter();
		WriterMapping<DummyWriter> mapping = new WriterMapping<>(dummyWriter, typeDescription);
		register(int.class, mapping);
		assertEquals(typeDescription, getTypeDescription(int.class));
	}

}
