package org.quickorc;

public interface Visitor<T> {
	void visit(int colIndex, int row, Object value) throws Exception;
}
