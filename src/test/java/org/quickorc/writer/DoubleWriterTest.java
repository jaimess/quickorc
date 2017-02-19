package org.quickorc.writer;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.junit.Test;

public class DoubleWriterTest extends DoubleWriter {

	@Test
	public void testWrite() {
		DoubleColumnVector v = new DoubleColumnVector(2);
		VectorizedRowBatch batch = createMock(VectorizedRowBatch.class);
		replay(batch);
		batch.cols = new ColumnVector[2];
		batch.cols[0] = v;
		write(batch, 0, 1, 1.1);
		assertEquals(0L, v.vector[0], 0);
		assertEquals(1.1, v.vector[1], 0);
		verify(batch);	
	}

}
