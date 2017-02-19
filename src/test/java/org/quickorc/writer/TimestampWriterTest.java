package org.quickorc.writer;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.junit.Test;

public class TimestampWriterTest extends TimestampWriter {

	@Test
	public void testWrite() {
		TimestampColumnVector v = new TimestampColumnVector(2);
		VectorizedRowBatch batch = createMock(VectorizedRowBatch.class);
		replay(batch);
		batch.cols = new ColumnVector[2];
		batch.cols[0] = v;
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		write(batch, 0, 1, timestamp);
		assertEquals(0L, v.time[0]);
		assertEquals(0L, v.nanos[0]);
		assertEquals(timestamp.getTime(), v.time[1]);
		assertEquals(timestamp.getNanos(), v.nanos[1]);
		verify(batch);	
	}

}
