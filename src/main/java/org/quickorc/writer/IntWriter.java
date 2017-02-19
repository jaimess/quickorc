package org.quickorc.writer;

import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

public class IntWriter implements Writer {

	public void write(VectorizedRowBatch batch, int colIndex, int row, Object value) {
		if (value != null)
			visit(batch, colIndex, row, ((Integer)value).intValue());
	}
	
	void visit(VectorizedRowBatch batch, int colIndex, int row, int value) {
		LongColumnVector v = (LongColumnVector) batch.cols[colIndex];
		v.vector[row] = value;
	}
}
