package telran.util;

import java.util.Iterator;
import java.util.function.Predicate;

public abstract class AbstractCollection<T> implements Collection<T> {
	protected int size;
	@Override
	public int size() {
		
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		
		return size == 0;
	}
	
	protected boolean isEquals(T pattern, T object) {
		return object == null ? pattern == null : object.equals(pattern);
	}

}
