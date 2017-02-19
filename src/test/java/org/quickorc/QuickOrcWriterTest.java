package org.quickorc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.TypeDescription;
import org.junit.Test;
import org.quickorc.writer.WriterMappingRegistry;

public class QuickOrcWriterTest {

	@Test
	public void testWriteObject() throws UnsupportedEncodingException {
		DummyObject dummyObject = buildDummyObject();
		TypeDescription typeDescription = new SchemaBuilder().build(DummyObject.class);
		QuickOrcWriter orcWriter = new QuickOrcWriter(typeDescription, new WriterMappingRegistry());
		VectorizedRowBatch batch = typeDescription.createRowBatch();
		orcWriter.writeObject(dummyObject, batch, 0);
		
		HiveDecimal hDecimal =  ((DecimalColumnVector) batch.cols[0]).vector[0].getHiveDecimal();
		assertEquals(dummyObject.getBigDecimal()
				.round(new MathContext(hDecimal.precision(), RoundingMode.HALF_UP)), //hive decimal precision is lower than java's 
				hDecimal.bigDecimalValue());
		assertEquals(dummyObject.isBooleanValue(), ((LongColumnVector) batch.cols[1]).vector[0] == 1L);
		assertEquals(dummyObject.getDate().getTime(), ((LongColumnVector) batch.cols[2]).vector[0]);
		assertEquals(dummyObject.getDoubleValue(), ((DoubleColumnVector) batch.cols[3]).vector[0], 0);
		assertEquals(dummyObject.getIntValue(), ((LongColumnVector) batch.cols[4]).vector[0], 0);
		assertEquals(dummyObject.getLongValue(), ((LongColumnVector) batch.cols[5]).vector[0], 0);
		assertTrue(Arrays.equals(dummyObject.getString().getBytes("UTF-8"), ((BytesColumnVector) batch.cols[6]).vector[0]));
		assertEquals(dummyObject.getTimestamp().getNanos(), ((TimestampColumnVector) batch.cols[7]).nanos[0], 0);
		assertEquals(dummyObject.getTimestamp().getTime(), ((TimestampColumnVector) batch.cols[7]).time[0], 0);
	}

	private DummyObject buildDummyObject() {
		DummyObject dummyObject = new DummyObject();
		dummyObject.setBigDecimal(new BigDecimal(1234.1234));
		dummyObject.setBooleanValue(true);
		dummyObject.setDate(new Date());
		dummyObject.setDoubleValue(4567.89);
		dummyObject.setIntValue(1);
		dummyObject.setLongValue(2L);
		dummyObject.setString("string");
		dummyObject.setTimestamp(new Timestamp(new Date().getTime()));
		return dummyObject;
	}

}
