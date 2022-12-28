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
			System.arraycopy(array, 0, array, 0, index);
			System.arraycopy(array, index + 1, array, index, array.length - index - 1);
			res = true;
		}
		return res;
	}	


	@SuppressWarnings("unchecked")
	@Override
	public boolean removeIf(Predicate<T> predicate) {
		boolean res = false;
		for (int i = 0; i < size; i++) {
			if (predicate.test(array[i])) {
				remove(i);
				res = true;
			}
		}
		// TODO Auto-generated method stub
		return res;
	}

	@Override
	public boolean isEmpty() {

		return array.length == 0;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, T element) {		
		
		System.arraycopy(array, 0, array, 0, index-1);
			add(element);
				System.arraycopy(array, index, array, index + 1, size - index - 1);
	}

	@Override
	public T remove(int index) {
		T element = array[index];
			System.arraycopy(array, 0, array, 0, index);
			System.arraycopy(array, index + 1, array, index, size - index - 1);
		return element;
	}

	@Override
	public int indexOf(T pattern) {
		
		for (int i = 0; i < size; i++) {
			if (array[i].equals(pattern)) {
				return i;
			}
		}
		return -1;
		
	}

	@Override
	public int lastIndexOf(T pattern) {
		int index = -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null && array[i].equals(pattern)) {
				index = i;
			}
		}
		return index;
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(int index, T element) {
		// TODO Auto-generated method stub

	}

}
