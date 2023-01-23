package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;
import telran.util.Collection;
import telran.util.List;

public abstract class  CollectionsTest {

	protected Integer [] numbers = {10, 100, -5, 134, 280, 120, 15, 11};
	protected Integer ar[] = new Integer[numbers.length + 100];
	protected Collection<Integer> collection;
	protected Integer [] empty = {};
	
	@BeforeEach
	void setUp() throws Exception {
		for(Integer num: numbers) {
			collection.add(num);
		}
	}

	abstract void testAdd();
	abstract void testIterator();

	
	@Test
	void testRemove() {
		Integer [] expected = {10, 100, -5, 134, 120, 15, 11};
		assertTrue(collection.remove((Integer)(280)));
		Arrays.sort(expected);
		Integer[] actual = collection.toArray(empty);
		Arrays.sort(actual);
		assertArrayEquals(expected, actual);
		assertFalse(collection.remove((Integer)280));
	}

	@Test
	void testRemoveIf() {
		Integer []expected = {-5, 15, 11};
		assertTrue(collection.removeIf(n -> n % 2 == 0));
		Integer[] actual = collection.toArray(empty);
		Arrays.sort(actual);
		Arrays.sort(expected);
		assertArrayEquals(expected, actual);
		assertFalse(collection.removeIf(n -> n % 2 == 0));
		assertTrue(collection.removeIf(n -> true));
		assertTrue(collection.isEmpty());
		
	}

	@Test
	void testIsEmpty() {
		assertFalse(collection.isEmpty());
		collection.removeIf(n -> true);
		assertTrue(collection.isEmpty());
	}

	@Test
	void testSize() {
		assertEquals(numbers.length, collection.size());
	}

	@Test
	void testContains() {
		assertTrue(collection.contains(numbers[0]));
		assertFalse(collection.contains(Integer.MIN_VALUE));
	}

	@Test
	void testToArray() {
		
		Arrays.fill(ar, 10);
		collection.toArray(ar);
		for(int i = 0; i < numbers.length; i++) {
			assertEquals(ar[i], numbers[i]);
		}
		for(int i = numbers.length; i < ar.length; i++) {
			assertNull(ar[i]);
		}
		
	}
	@Test
	void removeIteratorTest() {
		 final Iterator<Integer> iterator = collection.iterator();
		 assertThrowsExactly(IllegalStateException.class, () -> iterator.remove());
		 Integer num = iterator.next();
		 assertTrue(collection.contains(num));
		 iterator.remove();
		 assertFalse(collection.contains(num));
		 assertThrowsExactly(IllegalStateException.class, () -> iterator.remove());
		 Iterator<Integer> iterator1 = collection.iterator();
		 while(iterator1.hasNext()) {
			 num = iterator1.next();
		 }
		 assertTrue(collection.contains(num));
		 iterator1.remove();
		 assertFalse(collection.contains(num));
	}

	
}
