package telran.util;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.AbstractRowProcessor;

public class LinkedList<T> extends AbstractCollection<T> implements List<T> {

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
	
	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;
		boolean flagNext = false;
		@Override
		public boolean hasNext() {
			
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.object;
					current = current.next;
					flagNext = true;
			return res;
		}
		@Override
		public void remove() {
			if (!flagNext) {
				throw new IllegalStateException(); 
			}
			Node<T> removedNode = current == null ? tail : current.previus;
			removeNode(removedNode);
			flagNext = false;
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
	
	private void removeNode(Node<T> current) {
		if (current == head) {
			removeHead();
		}else if (current == tail) {
			removeTail();
		}else {
			removeMiddle(current);
		}
		size--;
		
	}

	@Override
	public T remove(int index) {
		checkIndex(index, false);
		Node<T> node = getNode(index);
		if (node == null) {
			throw new IllegalStateException("removedNode in method remove(int index) is null");
		}
		removeNode(node);
		return node.object;
	}
	
	private void removeMiddle(Node<T> current) {
		Node<T> nodePrev = current.previus;
		Node<T> nodeNext = current.next;
		nodePrev.next = nodeNext;
		nodeNext.previus = nodePrev;		
	}

	private void removeHead() {		
		if (head.next == null) {
			head = tail = null;
		} else {
			Node<T> nextNode = head.next;
			nextNode.previus = null;
			head = nextNode;
		}
	}

	private void removeTail() {
		Node<T> previus = tail.previus;
		previus.next = null;
		tail = previus;		
	}

	@Override
	public int indexOf(T pattern) {
		Node<T> current = head; 
		int i = 0;	
		while (current !=null && !isEquals(pattern, current.object)) {
			i++;
			current = current.next;
		}
		
		return i < size ? i : -1;
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
	
	@Override
	public Iterator<T> iterator() {
		
		return new LinkedListIterator();
	}
	
	/************************************************************************************/
	//Comments only for LinkedList task of loop existence
	/**
	 * sets next of element at index1 to element at index2
	 */
	public void setNext(int index1, int index2) {
		if (index1 < index2) {
			throw new IllegalArgumentException();
		}
		Node<T> node1 = getNode(index1);
			Node<T> node2 = getNode(index2);
				node1.next = node2;		
	}
	
	/**
	 * //method returns true if there is loop by next reference referring to a previous element
		// use neither "size" nor "size()"
		// no use prev filed in a Node
		// O[N]  with no using collections
	 */
	public boolean hasLoop() {
				
		Node<T> current = head;
		Node<T> current_next = head;
		boolean res = false;		
		while (current_next != null && !res) {
			current = current.next;
			current_next = current_next.next;
			if (current_next != null) {
				current_next = current_next.next;
			} 
			if(current == current_next) {
				res = true;
			}
			
		}		
		return res;
	}
	/*********************************************************************************************/

}
