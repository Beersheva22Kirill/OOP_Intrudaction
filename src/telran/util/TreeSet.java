package telran.util;

import java.util.Comparator;
import java.util.Iterator;

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
		//TODO
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(T pattern) {
		//Not implemented yet
		return false;
	}

	@Override
	public boolean contains(T pattern) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new TreeSetIterator();
	}

}
