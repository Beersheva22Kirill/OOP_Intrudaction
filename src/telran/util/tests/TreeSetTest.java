package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.TreeSet;

class TreeSetTest extends SortedTest {
	TreeSet<Integer> tree;
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<Integer>();
		super.setUp();
		treeSetCollection = (TreeSet<Integer>) collection;
		tree = (TreeSet<Integer>) collection;
	}

	@Test
	void displayRotatedTest() {
		tree.displayTreeRotated();
	}
	
	@Test
	void heightTreeTest() {
		assertEquals(4, tree.heightTree());
	}
	
	@Test
	void widthTreeTest() {
		assertEquals(3, tree.widthTree());
	}
	
	@Test
	void inversionTreeTest() {
		// {10, 100, -5, 134, 280, 120, 15, 11};
		tree.inversionTree();
		Integer[] expected = {280, 134,120, 100, 15, 11, 10, -5};
		Integer [] actual = new Integer[expected.length];
		int index = 0;
		for (Integer num : tree) {
			actual[index++] = num;
		}
		assertArrayEquals(expected, actual);
		assertTrue(tree.contains(280));
	}
}
