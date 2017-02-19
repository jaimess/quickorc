package org.quickorc.writer;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.junit.Test;

public class StringWriterTest extends StringWriter {

	@Test
	public void testWrite() throws UnsupportedEncodingException {
		BytesColumnVector v = new BytesColumnVector();
		VectorizedRowBatch batch = createMock(VectorizedRowBatch.class);
		replay(batch);
		batch.cols = new ColumnVector[2];
		batch.cols[0] = v;
		
		write(batch, 0, 1, "string");

		assertEquals(null, v.vector[0]);
		byte[] data = Arrays.copyOfRange(v.vector[1], v.start[1], v.length[1]);
		assertTrue(Arrays.equals("string".getBytes("UTF-8"), data));
		verify(batch);	
	}

}
