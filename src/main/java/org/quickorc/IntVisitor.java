package org.quickorc;

import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

public class IntVisitor implements Visitor<Integer> {
	VectorizedRowBatch batch;
	
	public IntVisitor(VectorizedRowBatch vrb) {
		this.batch = vrb;
	}

	public void visit(int colIndex, int row, Object value) {
		if (value != null)
			visit(colIndex, row, ((Integer)value).intValue());
	}
	
	void visit(int colIndex, int row, int value) {
		LongColumnVector x = (LongColumnVector) batch.cols[row];
		x.vector[row] = value;
	}
}
