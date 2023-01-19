package telran.util.tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.LinkedList;
import telran.util.TreeSet;

public class SortedTest extends SetTest {
	TreeSet<Integer> treeSetCollection;

	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<>();
		super.setUp();
		treeSetCollection = (TreeSet<Integer>) collection;
	}
	
	@Override
	@Test
	void testIterator() {
		Integer[] array  = new Integer[set.size()];
		Iterator<Integer> iterator = set.iterator();
		int i = 0;
			while(iterator.hasNext()){       
				array[i++] = iterator.next();
			}
			Arrays.sort(numbers);
		assertArrayEquals(numbers, array);
		assertThrowsExactly(NoSuchElementException.class, () -> iterator.next());
	}
	//{-5, 4, 5, 10, 11, 15, 100, 120, 134, 280}
	@Test
	void floorTest() {
		//FEXME
		assertEquals((Integer)100, treeSetCollection.floor(100));
		asserrtNull(treeSetCollection.floor(-10));
		assertEquals((Integer)15, treeSetCollection.floor(20));	
		assertEquals((Integer)280, treeSetCollection.floor(300));
		assertEquals((Integer)100, treeSetCollection.floor(115));
	}
	@Test
	void cellingTest() {
		//FEXME
		assertEquals((Integer)100, treeSetCollection.celling(100));
		asserrtNull(treeSetCollection.celling(281));
		assertEquals((Integer)10, treeSetCollection.celling(0));	
		assertEquals((Integer)(-5), treeSetCollection.celling(-10));
		assertEquals((Integer)280, treeSetCollection.celling(150));
	}
	@Test
	void firstTest() {
		//FEXME
		assertEquals((Integer)(-5), treeSetCollection.first());
	}
	@Test
	void lastTest() {
		//FEXME
		assertEquals((Integer)280, treeSetCollection.last());
	}
	
	

}
