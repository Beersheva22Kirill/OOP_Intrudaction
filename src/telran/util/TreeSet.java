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

		Node<T> current = root;
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
			current = getNextCurrent(current);
			flagNext = true;
			return res;
		}


		public TreeSetIterator() {
			if (current != null) {
				current = getLeastNode(current);
			}
			

		}
		
		@Override
		public void remove() {
			if (!flagNext) {
				throw new IllegalStateException(); 
			}
//			
//			size--;
//			flagNext = false;
		}
		
	}
	private Node<T> root;
	private Comparator<T> comparator;
	
	public TreeSet(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	private Node<T> getNextCurrent(Node<T> current) {
		
		return current.right == null ? getGreaterParent(current) : getLeastNode(current.right);
	}

	private Node<T> getGreaterParent(Node<T> current) {
		while (current.parent != null && current.parent.left != current) {
			current = current.parent;
		}
		return current.parent;
	}

	private Node<T> getLeastNode(Node<T> current) {
		while (current.left != null) {
			current = current.left;
		}
		return current;
	}

	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}
	
	
	@Override
	public boolean add(T element) {	
		boolean flagAdd = false;
		Node<T> parent = getNode(element);
		int compRes = 0;
		if(parent == null || (compRes = comparator.compare(element, parent.object)) != 0) {
			flagAdd  = true;
			size++;
			Node<T> node = new Node<>(element);
			node.parent = parent;
			if(parent == null) {
				root = node;
			} else {
				if (compRes < 0) {
					parent.left = node;
				} else {
					parent.right = node;
				}
			}
		}

		return flagAdd;
	}
	
	private Node<T> getNode(T element) {
		Node<T> current = root;
		Node<T> parent = null;
		int resCompare;
			while(current != null && (resCompare = comparator.compare(element, current.object)) != 0) {
				parent = current;
				current = resCompare < 0 ? current.left : current.right;
			}
		return current == null ? parent : current;
	}

	@Override
	public boolean remove(T pattern) {
		// FIXME
		Node<T> nodeDeleted = getNode(pattern);
		Node<T> nodeReplaced;
		boolean res = false;
		if (nodeDeleted.object.equals(pattern)){ 
			res = true;
			size--;
			if (nodeDeleted.left == null || nodeDeleted.right == null) {
				if(nodeDeleted.left != null) {
					if (comparator.compare(nodeDeleted.object, nodeDeleted.parent.object) > 0) {
						nodeDeleted.parent.right = nodeDeleted.left;
					}else {
						nodeDeleted.parent.left = nodeDeleted.left;
					}
				} else if(nodeDeleted.right != null){
					if (comparator.compare(nodeDeleted.object, nodeDeleted.parent.object) > 0) {
						nodeDeleted.parent.right = nodeDeleted.right;
					}else {
						nodeDeleted.parent.left = nodeDeleted.right;
					}
				} else {
					if (comparator.compare(nodeDeleted.object, nodeDeleted.parent.object) > 0) {
						nodeDeleted.parent.right = null;
					}else {
						nodeDeleted.parent.left = null;
					}
				}
				
			} else {
				nodeReplaced = getBigNode(nodeDeleted.left);
				nodeDeleted.object = nodeReplaced.object;
				nodeReplaced.parent.left = nodeReplaced.left;
			}
		}
		return res;
	}
	
	private Node<T> getBigNode(Node<T> current) {
		while (current.right != null) {
			current = current.right;
		}
		return current;
	}
	@Override
	public boolean contains(T pattern) {
		Node<T> node = getNode(pattern);
		
		return node != null && comparator.compare(pattern, node.object) == 0;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new TreeSetIterator();
	}

	public T floor(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public T celling(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public T first() {
		Node<T> current = root;
		while(current.left != null) {
			current = current.left;
		}
		return current.object;
	}

	public T last() {
		Node<T> current = root;
		while(current.right != null) {
			current = current.right;
		}
		return current.object;
	}

}
