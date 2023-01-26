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

	public T floor(T element) {
		Node<T> current = root;
		Node<T> node;
			while (current != null && comparator.compare(element, current.object) > 0 && current.right != null) {
				current = current.right;
			} 
				if(comparator.compare(element, current.object) != 0 ) {	
						if (current.left != null) {
							node = getBigNode(current.left);
							if (comparator.compare(element, node.object) < 0) {
								current = current.parent;
							} else {
								current = node;
							}
						}		
				}
			
			 			
		return current == null ? null : current.object;
	}

	public T celling(T element) {
		Node<T> current = root;
		Node<T> node;		
		
		while (current != null && comparator.compare(element, current.object) > 0) {
			current = current.right;
		} 
		if(current != null) {
			if(comparator.compare(element, current.object) != 0 ) {	
				if (current.left != null) {
					node = getBigNode(current.left);
					if (comparator.compare(element, node.object) > 0) {
						if(current.parent != null) {
							current = current.parent;
						}
					} else {
						current = node;
					}
				}
			}
		}
			
		
		
		return current == null ? null : current.object;
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
		inversionTree(root,0);
		comparator = (Comparator<T>) Comparator.reverseOrder();
		
	}

	private void inversionTree(Node<T> root, int level) {
		if (root != null) {
			swapNode(root);
			inversionTree(root.left, level + 1);
			inversionTree(root.right, level + 1);
		}
		
	}

	private void swapNode(Node<T> current) {
		Node<T> node = current.left;
		current.left = current.right;
		current.right = node;
		
	}
}
