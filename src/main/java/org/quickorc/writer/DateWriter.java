package org.quickorc.writer;

import java.util.Date;

import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

public class DateWriter extends LongWriter {

	public void write(VectorizedRowBatch batch, int colIndex, int row, Object value) {
		if (value != null)
			write(batch, colIndex, row, ((Date)value).getTime());
	}
	
}
