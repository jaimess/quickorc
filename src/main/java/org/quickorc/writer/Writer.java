package org.quickorc.writer;

import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

public interface Writer {
	void write(VectorizedRowBatch batch, int colIndex, int row, Object value) throws Exception;
}
