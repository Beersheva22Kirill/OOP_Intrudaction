package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T>{
	
	boolean add(T element);
	boolean remove (T pattern);
	
	default boolean removeIf(Predicate<T> predicate) {
			Iterator<T> iterator = iterator();
			int oldSize = size();
			while(iterator.hasNext()) {
				T odject = iterator.next();
				if (predicate.test(odject)) {
					iterator.remove();
				}
			}
		return oldSize > size();
	}
	
	default T[] toArray(T[] array) {
		int size = size();
		Iterator<T> iterator = iterator();
		if (array.length < size) {
			array = Arrays.copyOf(array, size());
		} 	
		int index = 0;
		while(iterator.hasNext()) {
			T object = iterator.next();
			array[index++] = object;
		}
		Arrays.fill(array, size, array.length, null);
		return array;
	}
	
	boolean isEmpty();
	int size();
	boolean contains(T pattern);
	
	/******************************************/ 
	/**
	 * 
	 * @param ar 
	 * @return array containing elements of a Collection
	 * if ar refers to memory that is enough for all elements of Collection then new array is not created
	 * otherwise there will be created new array.  
	 * if ar refers to memory that is greater than required for all elements of Collection then all elements 
	 * Collection will be put in the array and rest of memory will be filled by null's
	 */
	//T[] toArray (T[]ar);

}
