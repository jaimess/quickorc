package org.quickorc.writer;

import java.sql.Timestamp;

import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

public class StringWriter implements Writer {

	@Override
	public void write(VectorizedRowBatch batch, int colIndex, int row, Object value) throws Exception {
		TimestampColumnVector v = (TimestampColumnVector) batch.cols[colIndex];
		Timestamp timestamp = (Timestamp) value;
		v.nanos[row] = timestamp.getNanos();
		v.time[row] = timestamp.getTime();
	}
}
