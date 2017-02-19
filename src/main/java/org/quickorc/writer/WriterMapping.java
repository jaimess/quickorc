package org.quickorc.writer;

import org.apache.orc.TypeDescription;

public class WriterMapping<V extends Writer, T extends TypeDescription> {

	private V visitor;
	private T typeDescription;

	public WriterMapping(V visitor, T typeDescription) {
		this.visitor = visitor;
		this.typeDescription = typeDescription;
	}

	public V getVisitor() {
		return visitor;
	}

	public void setVisitor(V visitor) {
		this.visitor = visitor;
	}

	public T getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(T typeDescription) {
		this.typeDescription = typeDescription;
	}
}
