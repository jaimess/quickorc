package org.quickorc.writer;

import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

public class DoubleWriter implements Writer {

	public void write(VectorizedRowBatch batch, int colIndex, int row, Object value) {
		if (value != null)
			visit(batch, colIndex, row, ((Double)value).doubleValue());
	}
	
	void visit(VectorizedRowBatch batch, int colIndex, int row, double value) {
		DoubleColumnVector v = (DoubleColumnVector) batch.cols[colIndex];
		 v.vector[row] = value;
	}
}
