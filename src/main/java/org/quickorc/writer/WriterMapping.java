package org.quickorc.writer;

import org.apache.orc.TypeDescription;

public class WriterMapping<V extends Writer> {

	private V writer;
	private TypeDescription typeDescription;

	public WriterMapping(V writer, TypeDescription typeDescription) {
		this.writer = writer;
		this.typeDescription = typeDescription;
	}

	public V getWriter() {
		return writer;
	}

	public void setWriter(V writer) {
		this.writer = writer;
	}

	public TypeDescription getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(TypeDescription typeDescription) {
		this.typeDescription = typeDescription;
	}
}
