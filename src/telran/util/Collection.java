package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
	
	default Stream<T> stream(){
		
		return StreamSupport.stream(this.spliterator(), false);
	}
	
	default Stream<T> parallelStream(){
		
		return StreamSupport.stream(this.spliterator(), true);
	}
	
	
	default T[] toArrayShuffling(T[] array) {
		ArrayList<Integer> indexes = new ArrayList<>();
		new Random().ints(0,array.length).distinct().limit(8).forEach(indexes::add);
		T[] res = array;
		for (int i = 0; i < array.length; i++) {
			T temp = res[i];
			res[i] = res[indexes.get(i)];
			res[indexes.get(i)] = temp;	
		}
		
		return res;	
	}

}
