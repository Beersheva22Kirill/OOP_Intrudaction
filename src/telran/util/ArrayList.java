package telran.util;

import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T [] array;
	private int size;
	
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
	public boolean remove(T pattern) {
		boolean res = false;
		int index = indexOf(pattern);
		if (index > -1) {
			System.arraycopy(array, index + 1, array, index, array.length - index - 1);
			res = true;
		}
		return res;
	}	


	@Override
	public boolean removeIf(Predicate<T> predicate) {
		boolean res = false;
		for (int i = 0; i < size; i++) {
			if (array[i] != null && predicate.test(array[i])) {
				remove(i);
				res = true;
			}
		}
		return res;
	}

	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	@Override
	public int size() {
	
		return size;
	}

	@Override
	public boolean contains(T pattern) {
		int index = 0;
		while (index < size && !array[index].equals(pattern)) {
				index++;
			}
		return index < array.length;
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(array, size);
		} else {
			System.arraycopy(array, 0, ar, 0, size);
			for (int i = size; i < ar.length; i++) {
				ar[i] = null;
			}
		}
		
			
		return ar;
	}

	@Override
	public void add(int index, T element) {		
			array[index] = element;
				System.arraycopy(array, index, array, index + 1, size - index - 1);
	}

	@Override
	public T remove(int index) {
		T element = array[index];		
			System.arraycopy(array, index + 1, array, index, size - index);
		return element;
	}

	@Override
	public int indexOf(T pattern) {
		
		for (int i = 0; i < size; i++) {
			if (isEquals(pattern, i)) {
				return i;
			}
		}
		return -1;
		
	}

	private boolean isEquals(T pattern, int i) {
		return array[i] == null ? pattern == null : array[i].equals(pattern);
	}

	@Override
	public int lastIndexOf(T pattern) {
		for (int i = array.length - 1; i > 0; i--) {
			if (isEquals(pattern, i)) {
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

}
