package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {
	
	private static final int DEFAULT_TABLE_SIZE = 16;
	
	private List<Entry<T>>[] linkedHashTable;
	private T previusObject;
	
	@SuppressWarnings("unchecked")
	public LinkedHashSet(int sizeTable) {
		
		previusObject = null;
		linkedHashTable = new List[sizeTable];
	}
	
	public LinkedHashSet() {
		this(DEFAULT_TABLE_SIZE);
	}
	
	private class LikedHashSetIterator implements Iterator<T>{
		int current = 0;
		T key = null;
		int index = 0;
		
		boolean flagNext = false;
		Iterator<Entry<T>> iteratorList;
		
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
			if (linkedHashTable[index] != null) {
				iteratorList = linkedHashTable[index].iterator(); 
				while(iteratorList.hasNext() && res == null) {
					Entry<T> entry = iteratorList.next();
					if (isEquals(entry.key, key)) { 
						res = entry.getValue();
						flagNext = true;
						index = 0;
						current++;
						key = entry.value;
					} 
				} index++;
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
			replaceReferences(key);
			key = getElement(key).key;
			iteratorList.remove();
			flagNext = false;
			size--;
			current--;
		}
		



		
		
	}
@SuppressWarnings("hiding")
class Entry<T> {
	private T key;
	private T value;
	
		public T getValue() {
			return value;
		}
		public void setValue(T value) {
			this.value = value;
		}
		public T getKey() {
			return key;
		}
		
		public Entry(T key, T value) {
			super();
			this.key = key;
			this.value = value;
		}

	}
			@Override
			public boolean add(T element) {
				
				boolean res = false;
				Entry<T> entry = new Entry<>(previusObject, element);
				int index = getHashIndex(element);
				List<Entry<T>> list = linkedHashTable[index];
				if (list == null) {
					list = new LinkedList<>();
					linkedHashTable[index] = list;
				}
				if (!contains(entry.getValue(),list)) {
					res = true;
					list.add(entry);
					previusObject = element;
					size++;
				}
				return res;
				
			}
			
			
			private Entry<T> getElement(T value) {
				Entry<T> res = null;
				int index = getHashIndex(value);
				List<Entry<T>> list = linkedHashTable[index];
				if(list != null) {
					Iterator<Entry<T>> iterator = list.iterator();
					while(iterator.hasNext() && res == null) {
						Entry<T> entry = iterator.next();
						if (entry.value.equals(value)) {
							res = entry;
						}
					}
				}
			return res;
		}

			private Entry<T> getElementByKey(T key) {
				Entry<T> res = null;
				int index = 0;
				while (index < linkedHashTable.length && res == null) {
					if(linkedHashTable[index] != null) {
						Iterator<Entry<T>> iterator = linkedHashTable[index].iterator(); 
						while(iterator.hasNext() && res == null) {
							Entry<T> entry = iterator.next();
							if (isEquals(entry.key, key)) {
								res = entry;
							}
						}
					}
						index++;	
				}
				return res;
			}
			
			private boolean contains(T value, List<Entry<T>> list) {
				boolean res = false;
				Iterator<Entry<T>> iterator = list.iterator();
				while(iterator.hasNext() && !res) {
					if (iterator.next().value.equals(value)) {
						res = true;
					}
				}
			return res;
		}

			private int getHashIndex(T element) {
				
				return Math.abs(element.hashCode()) % linkedHashTable.length;
			}

			@Override
			public boolean remove(T pattern) {
				int index = getHashIndex(pattern);
				boolean res = false;
				if (linkedHashTable[index] != null) {
					replaceReferences(pattern);
					res = linkedHashTable[index].remove(getElement(pattern));
					if (res) {
						size--;
						if (linkedHashTable[index].isEmpty()) {
							linkedHashTable[index] = null;
						}
					}
				}	
				return res;
			}
			
			private void replaceReferences(T key) {
				Entry<T> nextElement = getElementByKey(key);
				Entry<T> currentElement = getElement(key);
				if(nextElement != null) {
					nextElement.key = currentElement.key;
				}
			}

			@Override
			public boolean contains(T pattern) {
				boolean res = false;
				int index = getHashIndex(pattern);
				if(linkedHashTable[index] != null) {
					res = contains(pattern, linkedHashTable[index]);
				}
				return res;
			}

			@Override
			public Iterator<T> iterator() {
			
				return new LikedHashSetIterator();
			}

			@Override
			public T get(T pattern) {
				
				return getElement(pattern).value;
			}

	

}
