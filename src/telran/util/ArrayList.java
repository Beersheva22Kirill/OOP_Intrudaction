package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayList<T> extends AbstractCollection<T> implements List<T>  {
	static final int DEFAULT_CAPACITY = 16;
	private T [] array;
	
	private class ArrayListIterator implements Iterator<T>{
		int current = 0;
		boolean flagNext = false;
		
		@Override
		public boolean hasNext() {

			return current < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			flagNext = true;
			return array[current++];
		}
		
		@Override
		public void remove() {
			if (!flagNext) {
				throw new IllegalStateException(); 
			} 
		    ArrayList.this.remove(--current);
			flagNext = false;
		}
		
	}
	public ArrayList(int capacity) {
		array = (T[])new Object[capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	@Override
	public boolean add(T element) {
		if(size == array.length) {
			reallocate();
		}
		array[size++] = element;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}
	
	@Override
	public void add(int index, T element) {				
				checkIndex(index, true);
				if (size == array.length) {
					reallocate();
				}
				System.arraycopy(array, index, array, index + 1, size - index);
				array[index] = element;
				size++;
	
	}

	
	@Override
	public T remove(int index) {
		checkIndex(index, false);
		T res = array[index];
		size--;
		System.arraycopy(array, index + 1, array, index, size - index);
		array[size] = null;
		return res;
	}
	

	@Override
	public int indexOf(T pattern) {
		
		for (int i = 0; i < size; i++) {
			if (isEquals(pattern, array[i])) {
				return i;
			}
		}
		return -1;
		
	}	

	@Override
	public int lastIndexOf(T pattern) {
		for (int i = array.length - 1; i > 0; i--) {
			if (isEquals(pattern, array[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public T get(int index) {
		
		return array[index];
	}

	@Override
	public void set(int index, T element) {
		if (array[index] != null) {
			remove(index);
		}		
		add(index, element);
	}

	@Override
	public Iterator<T> iterator() {
		
		return new ArrayListIterator();
	}

}
