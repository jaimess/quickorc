package org.quickorc.writer;

import java.math.BigDecimal;

import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;

public class DecimalWriter implements org.quickorc.writer.Writer {

	@Override
	public void write(VectorizedRowBatch batch, int colIndex, int row, Object value) {
		if (value != null) {
			DecimalColumnVector v = (DecimalColumnVector) batch.cols[colIndex];
			BigDecimal bd = (BigDecimal) value;
			v.vector[row] = new HiveDecimalWritable(HiveDecimal.create(bd));
		}
	}
}