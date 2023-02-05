package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class TreeSet<T> extends AbstractCollection<T> implements Sorted<T>{

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
		Node<T> previus;
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
			previus = current;
			current = getNextCurrent(current);
			flagNext = true;
			return res;
		}
		
		@Override
		public void remove() {
			if(!flagNext)  {
				throw new IllegalStateException();
			}
			flagNext = false;
			if(isJunction(previus)) {
				current = previus;
			}
			removeNode(previus);
		}


		public TreeSetIterator() {
			if (current != null) {
				current = getLeastNode(current);
			}
		}		
		
	}
	
	private static final String SYMBOL = " ";
	private static final int NUMBER_SYMBOLS_PER_LEVEL = 3;
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
		boolean res = false;
		Node<T> removedNode = getNode(pattern);
		if (removedNode != null && comparator.compare(pattern, removedNode.object) == 0) {
			res = true;
			removeNode(removedNode);
		}
		return res;
	}
	
	private void removeNode(Node<T> node) {
		if(isJunction(node)) {
			removeNodeJunction(node);
		} else {
			removeNodeNonJunction(node);
		}
		size--;
		
	}
	
	private boolean isJunction(Node<T> node) {
		return node.left != null && node.right != null;
	}
	
	private void removeNodeJunction(Node<T> node) {
		Node<T> substitution = getLeastNode(node.right);
		node.object = substitution.object;
		removeNodeNonJunction(substitution);
		
	}
		
	private void removeNodeNonJunction(Node<T> node) {
		Node<T> parent = node.parent;
		Node<T> child = node.left == null ? node.right : node.left;
		if (parent == null) {
			root = child;
		} else {
			if (parent.left == node) {
				parent.left = child;
			} else {
				parent.right = child;
			}
		}
		if (child != null) {
			child.parent = parent;
		}
		
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

	public T floor(T element) {
				 			
		return floorCelling(element, true);
	}

	public T celling(T element) {	
		
		
		return floorCelling(element, false);
	}
	
	private T floorCelling(T pattern, boolean isFloor) {
		T res = null;
		int compRes = 0;
		Node<T> current = root;
		while (current != null && (compRes = comparator.compare(pattern, current.object)) != 0) {
			if ((compRes < 0 && !isFloor) || (compRes > 0 && isFloor) ) {
				res = current.object;
			} 
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? res : current.object;
		
	}

	public T first() {
		
		return getLeastNode(root).object ;
	}

	public T last() {
		
		return getBigNode(root).object;
	}

	public void displayTreeRotated() {
		displayTreeRotated(root, 1);
	}

	private void displayTreeRotated(Node<T> root, int level) {
		if(root != null) {
			displayTreeRotated(root.right, level + 1);
			displayRoot(root,level);
			displayTreeRotated(root.left, level + 1);
		}
		
	}

	private void displayRoot(Node<T> root, int level) {
		System.out.printf("%s%s\n", SYMBOL.repeat(NUMBER_SYMBOLS_PER_LEVEL * level),root.object);
		
	}

	public Integer heightTree() {
		
		return heightTree(root);
	}

	private int heightTree(Node<T> root) {
		int res = 0;
		if (root != null ) {
			int hightLeft = heightTree(root.left); 
			int hightRight = heightTree(root.right); 
			res = Math.max(hightLeft, hightRight) + 1;
		}
		
		return res;
	}

	public Integer widthTree() {
		
		
		return widthTree(root, 0);
	}

	private Integer widthTree(Node<T> root , int level) {
		Integer res = 0;
		Integer heightTree = heightTree();
		Integer[] array = new Integer[heightTree];
			counter(root,level,array);
			for (Integer num : array) {
				if(res < num) {
					res = num;
				}
				
			}
		return res;
	}



	private void counter(Node<T> root, int level, Integer[] array) {
		if (root != null) {
			if (array[level] != null) {
				array[level]++;
			} else {
				array[level] = 1;
			}
			
			counter(root.left, level + 1, array);
			counter(root.right, level + 1, array);
		}
		
	}

	public void inversionTree() {
		inversionTree(root);
		comparator = comparator.reversed();
		
	}

	private void inversionTree(Node<T> root) {
		if (root != null) {
			swapNode(root);
			inversionTree(root.left);
			inversionTree(root.right);
		}
		
	}

	private void swapNode(Node<T> current) {
		Node<T> node = current.left;
		current.left = current.right;
		current.right = node;
		
	}
	
	public void balance() {
		Node<T>[] array = getNodesArray();
		root = balance(array, 0, array.length - 1, null);
		
	}
	private Node<T> balance(Node<T>[] array, int left, int right, Node<T>parent) {
		Node<T> root = null;
		if (left <= right) {
			final int rootIndex = (left + right) / 2;
			root = array[rootIndex];
			root.parent = parent;
			root.left = balance(array, left, rootIndex - 1, root);
			root.right = balance(array, rootIndex + 1, right, root);
		}
		return root;
	}
	@SuppressWarnings("unchecked")
	private Node<T>[] getNodesArray() {
		Node<T> res[] = new Node[size];
		int index = 0;
		if (root != null) {
			Node<T> current = getLeastNode(root);
			while (current != null) {
				res[index++] = current;
				current = getNextCurrent(current);
			} 
		}
		return res;
	}

	@Override
	public T get(T pattern) {
		T res = null;
		Iterator<T> iterator = iterator();
		T object = null;
		while (iterator.hasNext() && !isEquals(pattern, object = iterator.next())) {}
			if (isEquals(pattern, object)) {
				res = object;
			}
		return res;
	}


}
