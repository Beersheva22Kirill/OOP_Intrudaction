package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {

	private static class Node<T> {
		T object;
		Node<T> previus;
		Node<T> next;
		
		Node(T object) {
			this.object = object;
		}
	}
	
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	private class LinkedListIterator implements Iterator<T> {
		@Override
		public boolean hasNext() {
			
			return head != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = head.object;
				head = head.next;
			return res;
		}
		
	}
	
	@Override
	public boolean add(T element) {
		
		Node<T> node = new Node<>(element);
		if (head == null) {
				head = tail = node;
		} else {
				tail.next = node;
				node.previus = tail;
				tail = node;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int index = indexOf(pattern);
		if (index > -1) {
			remove(index);
			res = true;
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		Node<T> current = getNode(0);

		while (current != tail) {
			if(predicate.test(current.object)) {
				remove(current.object);
			}
			current = current.next;
		}
		
		if(predicate.test(current.object)) {
			head = null;
			size--;
		}
		
		return oldSize > size;
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
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		} 
		Node<T> current = head;
		for (int i = 0; i < size; i++) {
			ar[i] = current.object;
			current = current.next;
		}
		Arrays.fill(ar, size, ar.length, null);
		return ar;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new LinkedListIterator();
	}

	@Override
	public void add(int index, T element) {
		// FIXME 
		checkIndex(index, true);
		if (index == size) {
			add(element);
		} else if (index == 0) {
			addHead(element);
		}else {
			addMiddle(index, element);
		}
		
	}

	private void addMiddle(int index, T element) {
		Node<T> node = new Node<>(element);
		Node<T> nodeIndex = getNode(index);
		Node<T> nodePrev = nodeIndex.previus;
		nodePrev.next = node;
		node.previus = nodePrev;
		nodeIndex.previus = node;
		node.next = nodeIndex;
		size++;
		
	}

	private Node<T> getNode(int index) {
		
		return index < size / 2 ? getNodeFromLeft(index) : getNodeFromRight(index);
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.previus;
		}
		return current;
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private void addHead(T element) {
		Node<T> node = new Node(element);
		node.next = head;
		head.previus = node;
		head = node;
		size++;
	}

	@Override
	public T remove(int index) {
		T nodeReturn;
		if (index == size) {
			nodeReturn = removeTail();
		} else if (index == 0) {
			nodeReturn = removeHead();
		}else {
			nodeReturn = removeMiddle(index);
		}
		return nodeReturn;
	}

	private T removeMiddle(int index) {
		checkIndex(index, false);
		Node<T> node = getNode(index);
		Node<T> nodeNext = getNode(index + 1);
		Node<T> nodePrev = node.previus;
		
		nodePrev.next = nodeNext;
		nodeNext.previus = nodePrev;
		
		Node<T> nodeReturn = node;
		
		node = null;
		size--;
		return nodeReturn.object;
	}

	private T removeHead() {
		Node<T> nodeReturn = getNode(0);
		head = head.next;
		head.previus = null;
		size--;
		return nodeReturn.object;
	}

	private T removeTail() {
		Node<T> nodeReturn = getNode(size - 1);
		tail = tail.previus;
		tail.next = null;
		size--;
		return nodeReturn.object;
		
	}

	@Override
	public int indexOf(T pattern) {
		Node<T> current = head; 
		int i = 0;	
		while (i < size && !isEquals(pattern, current.object)) {
			i++;
			current = current.next;
		}
		
		return i < size ? i : -1;
	}
	
	private boolean isEquals(T pattern, T object) {
		return object == null ? pattern == null : object.equals(pattern);
	}
	@Override
	public int lastIndexOf(T pattern) {
		Node<T> current = tail; 
		int i = size - 1;	
		while (i > -1 && !isEquals(pattern, current.object)) {
			i--;
			current = current.previus;
		}	
		return i > -1 ? i : -1;
	}

	@Override
	public T get(int index) {
		checkIndex(index, false);
		return getNode(index).object;
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, false);
		Node<T> node = getNode(index);
		node.object = element;
		
	}

}
