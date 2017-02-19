package org.quickorc.writer;

import java.nio.charset.Charset;

import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

public class StringWriter implements Writer {
	private static final Charset UTF8 = Charset.forName("UTF-8");
	
	@Override
	public void write(VectorizedRowBatch batch, int colIndex, int row, Object value) {
		write(batch, colIndex, row, value, null);
	}
	
	public void write(VectorizedRowBatch batch, int colIndex, int row, Object value, Charset charset) {
		if (value != null) {
			BytesColumnVector x = (BytesColumnVector) batch.cols[colIndex];
			byte[] stringBytes = ((String)value).getBytes((charset != null)? charset : UTF8);
			x.setRef(row, stringBytes, 0, stringBytes.length);
		}
	}
}
