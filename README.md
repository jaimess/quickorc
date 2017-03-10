# quickorc [![Build Status](https://travis-ci.org/jaimess/quickorc.svg?branch=master)](https://travis-ci.org/jaimess/quickorc#master)
Easy way to write java objects to apache orc files. Use @QuickOrc annotation to define which fields will be mapped. If @QuickOrc annotation is not present all fields will be mapped using default String comparator as sorter.
# schema creation
TypeDescription schema = new SchemaBuilder().build(AnnotatedDummyObject.class);
# writing pojos
    VectorizedRowBatch batch = schema.createRowBatch();
    for (int i = 0; i < 5; i++) {
        int row = batch.size++;
        DummyObject obj = new DummyObject();
        QuickOrcWriter quickWriter = new QuickOrcWriter(schema, new WriterMappingRegistry());
        batch = quickWriter.writeObject(obj, batch, row);
        if (batch.size == batch.getMaxSize()) {
            writer.addRowBatch(batch);
            batch.reset();
        }
    }

# supported fields and mappings
Default mappings:

    registry.put(int.class, new WriterMapping<>(new IntWriter(), TypeDescription.createInt()));
    registry.put(Integer.class, new WriterMapping<>(new IntWriter(), TypeDescription.createLong()));
    registry.put(long.class, new WriterMapping<>(new LongWriter(), TypeDescription.createLong()));
    registry.put(Long.class, new WriterMapping<>(new LongWriter(), TypeDescription.createLong()));
    registry.put(String.class, new WriterMapping<>(new StringWriter(), TypeDescription.createString()));
    registry.put(Date.class, new WriterMapping<>(new DateWriter(), TypeDescription.createDate()));
    registry.put(Boolean.class, new WriterMapping<>(new BooleanWriter(), TypeDescription.createBoolean()));
    registry.put(boolean.class, new WriterMapping<>(new BooleanWriter(), TypeDescription.createBoolean()));
    registry.put(double.class, new WriterMapping<>(new DoubleWriter(), TypeDescription.createDouble()));
    registry.put(Double.class, new WriterMapping<>(new DoubleWriter(), TypeDescription.createDouble()));
    registry.put(Timestamp.class, new WriterMapping<>(new TimestampWriter(), TypeDescription.createTimestamp()));
    registry.put(BigDecimal.class, new WriterMapping<>(new DecimalWriter(), TypeDescription.createDecimal()));
Different mappings can be registered in WriterMappingRegistry.

# contributions and roadmap

Reader support will probably added. Any contributions are welcome.
