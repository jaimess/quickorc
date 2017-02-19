package org.quickorc.writer;

import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

public class BooleanWriter extends LongWriter {

	public void write(VectorizedRowBatch batch, int colIndex, int row, Object value) {
		if (value != null)
			write(batch, colIndex, row, (((Boolean)value).booleanValue())? 1L : 0L);
	}
	
}
