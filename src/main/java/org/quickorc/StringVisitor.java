package org.quickorc;

import java.io.UnsupportedEncodingException;

import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

public class StringVisitor implements Visitor<String> {
	VectorizedRowBatch batch;
	public StringVisitor(VectorizedRowBatch batch) {
		this.batch = batch;
	}

	@Override
	public void visit(int colIndex, int row, Object value) throws Exception {
		BytesColumnVector x = (BytesColumnVector) batch.cols[colIndex];
		if (value != null) {
			x.setVal(row, ((String)value).getBytes("UTF-8"));
		}
		
	}

}
