package org.quickorc;

import java.util.Date;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.OrcFile;
import org.apache.orc.Reader;
import org.apache.orc.RecordReader;
import org.apache.orc.TypeDescription;
import org.quickorc.writer.WriterMappingRegistry;

public class Mapper {

	public static void main(String[] args) throws Exception {

		new Mapper().toORC();
	}

	Car buildCar() {
		Random r = new Random();
		Car c = new Car();
		c.setDate(new Date());
		c.setCabrio(r.nextBoolean());
		c.setModel(System.currentTimeMillis() + "model");
		c.setTyres(r.nextInt(1000));
		c.setWeight(r.nextDouble());
		return c;
	}

	VectorizedRowBatch toORC() throws Exception {

		TypeDescription schema = new SchemaBuilder().build(Car.class);
		System.out.println(schema);

		Configuration configuration = new Configuration();
		configuration.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
		configuration.set("fs.file.impl", "org.apache.hadoop.fs.LocalFileSystem");
//		FileSystem hdfs = FileSystem.get(new URI("hdfs://127.0.0.1:9000"), configuration);
		Path file = new Path("hdfs://127.0.0.1:9000/tmp/my-file-" + System.currentTimeMillis() + ".orc");

		org.apache.orc.Writer writer = OrcFile.createWriter(file,
				OrcFile.writerOptions(new Configuration()).setSchema(schema));

		VectorizedRowBatch batch = schema.createRowBatch();
		for (int i = 0; i < 5; i++) {
			int row = batch.size++;
			Car obj = buildCar();
			long t1 = System.currentTimeMillis();
			QuickOrcWriter quickWriter = new QuickOrcWriter(schema, new WriterMappingRegistry());
			batch = quickWriter.writeObject(obj, batch, row);
			//System.out.println(System.currentTimeMillis() - t1);
			if (batch.size == batch.getMaxSize()) {
				writer.addRowBatch(batch);
				batch.reset();
			}
		}
		//System.out.println(batch);
			writer.addRowBatch(batch);

		writer.close();
		
		Reader reader = OrcFile.createReader(file,
                OrcFile.readerOptions(configuration));
		RecordReader rows = reader.rows();
		System.out.println(reader.getSchema());
		System.out.println(reader.getNumberOfRows());
		VectorizedRowBatch rbatch = reader.getSchema().createRowBatch();
		int counter = 0;
		while (rows.nextBatch(rbatch)) {
				  	System.out.print(rbatch + "\n");
			  counter ++;
			}
			rows.close();
		return null;
	}

}
