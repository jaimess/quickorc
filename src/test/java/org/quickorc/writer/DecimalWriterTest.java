package org.quickorc.writer;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.junit.Test;

public class DecimalWriterTest extends DecimalWriter {

	@Test
	public void testWrite() {
		DecimalColumnVector v = new DecimalColumnVector(2, 1, 0);
		VectorizedRowBatch batch = createMock(VectorizedRowBatch.class);
		replay(batch);
		batch.cols = new ColumnVector[2];
		batch.cols[0] = v;
		BigDecimal bigDecimal = new BigDecimal(123456789.123456789);
		
		write(batch, 0, 1, bigDecimal);
		
		assertEquals(new HiveDecimalWritable(HiveDecimal.create(0.0)), v.vector[0]);
		assertEquals(bigDecimal.scale(), v.vector[1].scale());
		assertEquals(bigDecimal.precision(), v.vector[1].precision());
		assertEquals(bigDecimal, v.vector[1].getHiveDecimal().bigDecimalValue());
		verify(batch);	
	}
}
