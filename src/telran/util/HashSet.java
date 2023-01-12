package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<T> extends AbstractCollection<T> implements Set<T> {
	
	private static final int DEFAULT_TABLE_SIZE = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	private List<T>[] hashTable;
	private float factor;
	
	private class HashSetIterator implements Iterator<T>{
		int current = 0;
		int counter = 0;
		int index = 0;
		boolean flagNext = false;
		Iterator<T> iteratorList;
		@Override
		public boolean hasNext() {
			
			return current < size;
		}

		@Override
		public T next() {
			T res = null;
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
					
			if (hashTable[index] != null) {

					iteratorList = hashTable[index].iterator();
					
				if (iteratorList.hasNext()) {
					for(int i = 0; i < counter; i++) {
						iteratorList.next();
					}
					if(iteratorList.hasNext()) {
						counter++;
						current++;
						res = iteratorList.next();
						flagNext = true;
					} else {
						counter = 0;
						index++;
					}		
				} else {
					index++;
				}	
			} else {
				index++;
			}		
			return res == null ? next() : res;
		}
		

		@Override
		public void remove() {
			if(!flagNext) {
				throw new IllegalStateException();
			}
			iteratorList.remove();
			flagNext = false;
			size--;
			current--;
			counter--;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public HashSet(int sizeTable, float factor) {
		if(sizeTable < 1) {
			throw new IllegalArgumentException("Wrong initials size of Hash Table");
		}
		if(factor < 0.3 || factor > 1) {
			throw new IllegalArgumentException("Wrong initials factor of Hash Table");
		}
		this.factor = factor;
		hashTable = new List[sizeTable];
	}
	
	public HashSet() {
		this(DEFAULT_TABLE_SIZE,DEFAULT_FACTOR);
	}
	
	@Override
	public boolean add(T element) {
		if (size >= hashTable.length*factor) {
			tableRecreation();
		}
		int index = getHashIndex(element);
		List<T> list = hashTable[index];
		boolean res = false;
		if (list == null) {
			list = new LinkedList<>();
			hashTable[index] = list;
		}
		if (!list.contains(element)) {
			res = true;
			list.add(element);
			size++;
		}

		return res;
	}

	private void tableRecreation() {
		HashSet<T> hashSet = new HashSet<>(hashTable.length * 2,factor);
		
		for (List<T> list : hashTable) {
			if (list != null) {
				for (T object : list) {
					hashSet.add(object);
				}
			}
		}
		hashTable = hashSet.hashTable;
	}

	private int getHashIndex(T element) {
		
		return Math.abs(element.hashCode()) % hashTable.length;
	}

	@Override
	public boolean remove(T pattern) {
		int index = getHashIndex(pattern);
		boolean res = false;
		if (hashTable[index] != null) {
			res = hashTable[index].remove(pattern);
			if (res) {
				size--;
				if (hashTable[index].isEmpty()) {
					hashTable[index] = null;
				}
			}
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		int index = getHashIndex(pattern);
		if(hashTable[index] != null) {
			res = hashTable[index].contains(pattern);
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new HashSetIterator();
	}
	
	//FEXME The following method is only for initial test 
	// After HashTableIterator implementation is done the method should be removed
//	@Override
//	public T[] toArray(T[] array) {
//		if (array.length < size) {
//			array = Arrays.copyOf(array, size);
//			int index = 0;
//			for (List<T> list : hashTable) {
//				if (list != null) {
//					for (T object : list) {
//						array[index++] = object;
//					}
//				}
//			}
//		}
//		return array;
//	}



}
