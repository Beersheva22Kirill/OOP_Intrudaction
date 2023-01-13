package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Set;

class SetTest extends CollectionsTest{
	Set<Integer> set;
	
	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		set = (Set<Integer>) collection;
	}
	@Test
	@Override
	void testAdd() {
		assertTrue(set.add(876));
		assertFalse(set.add(876));
	}
	
	@Test
	@Override
	void testIterator() {	
		Integer[] array  = new Integer[set.size()];
		Iterator<Integer> iterator = set.iterator();
		int i = 0;
			while(iterator.hasNext()){       
				array[i++] = iterator.next();
			}
			Arrays.sort(numbers);
			Arrays.sort(array);
		assertArrayEquals(numbers, array);
		assertThrowsExactly(NoSuchElementException.class, () -> iterator.next());
	}
	
	@Test
	@Override
	void testToArray() {
		Integer[] actual = set.toArray(empty);
		Arrays.sort(actual);
		Arrays.sort(numbers);
		assertArrayEquals(numbers, actual);
		
	}

}
