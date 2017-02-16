package org.quickorc;

public interface OrcType<T> {
	void accept(Visitor<T> v);
}
