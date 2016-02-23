package org.eclipse.emf.parsley.tests.util;

public class Wrapper<T> {

	private T value;

	public static <T> Wrapper<T> wrap(T value) {
		return new Wrapper<T>(value);
	}

	public static <T> Wrapper<T> forType(Class<T> type) {
		return new Wrapper<T>();
	}

	public Wrapper() {
		this(null);
	}

	public Wrapper(T value) {
		this.value = value;
	}

	public T get() {
		return value;
	}

	public void set(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Wrapper of (" + value + ")";
	}
}
