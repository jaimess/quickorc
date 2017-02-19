package org.quickorc.writer;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.junit.Test;

public class LongWriterTest extends LongWriter {

	@Test
	public void testWriteVectorizedRowBatchIntIntObject() {
		LongColumnVector v = new LongColumnVector(2);
		VectorizedRowBatch batch = createMock(VectorizedRowBatch.class);
		replay(batch);
		batch.cols = new ColumnVector[2];
		batch.cols[0] = v;
		Object obj = 2L;
		write(batch, 0, 1, obj);
		assertEquals(0L, v.vector[0]);
		assertEquals(2L, v.vector[1]);
		verify(batch);	
	}

	@Test
	public void testWriteVectorizedRowBatchIntIntLong() {
		LongColumnVector v = new LongColumnVector(2);
		VectorizedRowBatch batch = createMock(VectorizedRowBatch.class);
		replay(batch);
		batch.cols = new ColumnVector[2];
		batch.cols[0] = v;
		long two = 2L;
		write(batch, 0, 1, two);
		assertEquals(0L, v.vector[0]);
		assertEquals(2L, v.vector[1]);
		verify(batch);	
	}

}
