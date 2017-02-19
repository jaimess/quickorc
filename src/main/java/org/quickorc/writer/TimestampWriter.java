package org.quickorc.writer;

import java.sql.Timestamp;

import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

public class TimestampWriter implements Writer {

	public void write(VectorizedRowBatch batch, int colIndex, int row, Object value) {
		if (value != null) {
			TimestampColumnVector v = (TimestampColumnVector) batch.cols[colIndex];
			Timestamp timestamp = (Timestamp) value;
			v.nanos[row] = timestamp.getNanos();
			v.time[row] = timestamp.getTime();
		}
	}
}
