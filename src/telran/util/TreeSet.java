package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Set<T> {

	static private class Node<T> {
		T object;
		Node<T> parent;
		Node<T> left;
		Node<T> right;
		
		public Node(T object) {	
			this.object = object;
		}
				
	}
	
	private class TreeSetIterator implements Iterator<T> {
		private int currentElement = 0;
		Node<T> current = root;
		

		@Override
		public boolean hasNext() {
		
			return currentElement < size;
		}

		@Override
		public T next() {
			T res = null;
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			res = current.object;
			currentElement++;
			if (current.right == null) {
				current = current.parent;
				
			} else {
				current = current.right;
				while (current.left != null) {
					current = current.left;
				}
			}

			return res;
		}

		public TreeSetIterator() {
			while (current.left != null)	
			current = current.left;
		}
		
	}
	private Node<T> root;
	private Comparator<T> comparator;
	
	public TreeSet(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}
	
	
	@Override
	public boolean add(T element) {	
		Node<T> node = new Node<>(element);		
		boolean flagAdd = false;
		if (size != 0) {
			Node<T> current = root;
			if(!contains(element)) {
				while(!flagAdd) {
					if (comparator.compare(node.object, current.object) == -1) {
						if (current.left != null) {
							current = current.left;
						} else {
							current.left = node;
							node.parent = current;
							flagAdd = true;
							size++;
						}
						
					} else {
						if (current.right != null) {
							current = current.right;
						} else {
							current.right = node;
							node.parent = current;
							flagAdd = true;
							size++;
						}
					}
				}
			} 				
		} else {
			root = node; 
			flagAdd = true;
			size++;
		}

		return flagAdd;
	}

	@Override
	public boolean remove(T pattern) {
		//Not implemented yet
		return false;
	}

	@Override
	public boolean contains(T pattern) {
		boolean next = true;
		boolean flagContains = false;
		if (size != 0) {
			Node<T> current = root;
			while(next) {
				if (!current.object.equals(pattern)) {
					if (comparator.compare(pattern, current.object) == -1) {
						if (current.left != null) {
							current = current.left;
						} else {
							next = false;
						}
						
					} else if (comparator.compare(pattern, root.object) == 1) {
						if (current.right != null) {
							current = current.right;
						} else {
							next = false;
						}
					}
				} else {
					flagContains = true;
					next = false;
				}
			
			}
				
		} else {
			flagContains = false; 
		}
		
		return flagContains;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new TreeSetIterator();
	}

}
