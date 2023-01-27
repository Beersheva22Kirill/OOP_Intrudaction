package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Sorted;
import telran.util.TreeSet;

class TreeSetTest extends SortedTest {
	TreeSet<Integer> treeCollection;
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<Integer>();
		super.setUp();
		treeCollection = (TreeSet<Integer>) collection;
	}

	@Test
	void displayRotatedTest() {
		treeCollection.displayTreeRotated();
	}
	
	@Test
	void heightTreeTest() {
		assertEquals(4, treeCollection.heightTree());
	}
	
	@Test
	void widthTreeTest() {
		assertEquals(3, treeCollection.widthTree());
	}
	
	@Test
	void inversionTreeTest() {
		// {10, 100, -5, 134, 280, 120, 15, 11};
		treeCollection.inversionTree();
		Integer[] expected = {280, 134,120, 100, 15, 11, 10, -5};
		Integer [] actual = new Integer[expected.length];
		int index = 0;
		for (Integer num : treeCollection) {
			actual[index++] = num;
		}
		assertArrayEquals(expected, actual);
		assertTrue(treeCollection.contains(280));
	}
	
	@Override
	protected Sorted<Integer> getSortedCollection() {
		
		return new TreeSet<>();
	}
	
	@Test
	void balanceTest() {
		treeCollection.balance();
		assertEquals(4, treeCollection.heightTree());
		assertEquals(4, treeCollection.widthTree());
		System.out.println("************************ balanced tree *****************");
		treeCollection.displayTreeRotated();
	}
}
