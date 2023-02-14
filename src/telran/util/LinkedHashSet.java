package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.Map.*;


public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {
	
	private static final int DEFAULT_TABLE_SIZE = 16;
	
	private HashMap<T,Entry<T>> linkedHashTable;
	private T previusObject;
	private Entry<T> headObject;
	
	public LinkedHashSet(int sizeTable) {
		
		previusObject = null;
		linkedHashTable = new HashMap<T,Entry<T>>();	
	}
	
	public LinkedHashSet() {
		this(DEFAULT_TABLE_SIZE);
	}
	
	private class LikedHashSetIterator implements Iterator<T>{

		int current = 0;
		boolean flagNext = false;
		Entry<T> key = headObject;
		Entry<T> previusObject;
			
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
			res = get(key.value);
			previusObject = linkedHashTable.get(res);
			key = linkedHashTable.get(key.keyNext);
			flagNext = true;
			current++;
			return res;
		}
		
		@Override
		public void remove() {
			if(!flagNext) {
				throw new IllegalStateException();
			}
			Entry<T> removed = linkedHashTable.get(previusObject.value);
			replaceReferences(removed);
			linkedHashTable.remove(removed.value);
			flagNext = false;
			size--;
			current--;
		}
		
	}
	
	@SuppressWarnings("hiding")
	private class Entry<T> {
		private T keyPrevius;
		private T keyNext;
		private T value;
			
			public Entry(T keyPrevius, T value) {
				super();
				this.keyPrevius = keyPrevius;
				this.value = value;
			}
		}

	@Override
	public boolean add(T element) {
		boolean res = false;
		Entry<T> entry = new Entry<T>(previusObject, element);
		if(previusObject == null) {
			headObject = entry;
		}
		if (linkedHashTable.putIfAbsent(element, entry) == null) {
			entry = linkedHashTable.getOrDefault(previusObject, entry);
			entry.keyNext = element;
			previusObject = element;
			size++;
			res = true;
		}
		
		return res;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Entry<T> entry = linkedHashTable.get(pattern);
		
		if(entry != null) {
			replaceReferences(entry);
			linkedHashTable.remove(pattern);
			size--;
			res = true;
		}	
		return res;
	}

	private void replaceReferences(Entry<T> entry) {
		Entry<T> entryNext = linkedHashTable.get(entry.keyNext);
		Entry<T> entryPrevius = linkedHashTable.get(entry.keyPrevius);
		if (entryNext != null) {
			entryNext.keyPrevius = entry.keyPrevius;
		} 
		if (entryPrevius != null) {
			entryPrevius.keyNext = entry.keyNext;
		}else {
			headObject = entryNext;
		}
		
		
	}

	@Override
	public boolean contains(T pattern) {
		
		return linkedHashTable.containsKey(pattern);
	}

	@Override
	public Iterator<T> iterator() {
		
		return new LikedHashSetIterator();
	}

	@Override
	public T get(T pattern) {
		
		return linkedHashTable.get(pattern).value;
	}
	
	

	

}
