package org.quickorc.writer;

import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

public class LongWriter implements Writer {

	public void write(VectorizedRowBatch batch, int colIndex, int row, Object value) {
		if (value != null)
			write(batch, colIndex, row, ((Long)value).longValue());
	}
	
	void write(VectorizedRowBatch batch, int colIndex, int row, long value) {
		LongColumnVector v = (LongColumnVector) batch.cols[colIndex];
		v.vector[row] = value;
	}
}
