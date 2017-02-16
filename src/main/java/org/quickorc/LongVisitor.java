package org.quickorc;

import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

public class LongVisitor implements Visitor<Long> {
	VectorizedRowBatch batch;
	
	public LongVisitor(VectorizedRowBatch vrb) {
		this.batch = vrb;
	}

	public void visit(int colIndex, int row, Object value) {
		if (value != null)
			visit(colIndex, row, ((Long)value).longValue());
	}
	
	void visit(int colIndex, int row, long value) {
		LongColumnVector x = (LongColumnVector) batch.cols[row];
		x.vector[row] = value;
	}
}
