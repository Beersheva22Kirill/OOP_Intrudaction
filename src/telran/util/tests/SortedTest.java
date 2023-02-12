package telran.util.tests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.LinkedList;
import telran.util.Sorted;
import telran.util.TreeSet;

public abstract class SortedTest extends SetTest {
	
	protected static final int N_ELEMNTS = 100000;
	protected static final int N_RUNS = 10000;
	private Random gen = new Random();
	Sorted<Integer> sorted;

	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<>();
		super.setUp();
		sorted = (TreeSet<Integer>) collection;
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
		assertEquals((Integer)100, sorted.floor(100));
		assertNull(sorted.floor(-10));
		assertEquals((Integer)15, sorted.floor(20));	
		assertEquals((Integer)280, sorted.floor(300));
		assertEquals((Integer)100, sorted.floor(115));
	}
	@Test
	void cellingTest() {
		
		assertEquals((Integer)100, sorted.celling(100));
		assertEquals((Integer)15, sorted.celling(13));
		assertNull(sorted.celling(281));
		assertEquals((Integer)10, sorted.celling(0));	
		assertEquals((Integer)(-5), sorted.celling(-10));
		assertEquals((Integer)280, sorted.celling(150));
	}
	@Test
	void firstTest() {
		
		assertEquals((Integer)(-5), sorted.first());
	}
	@Test
	void lastTest() {
		
		assertEquals((Integer)280, sorted.last());
	}
	
	@Test
	@Disabled
	void performanceTestSortedAdding() {
		Sorted<Integer> sorted = getSortedCollection();
		IntStream.range(0, N_ELEMNTS).forEach(i -> sorted.add(i));
		runPerformanceTest(sorted);
	}
	protected void runPerformanceTest(Sorted<Integer> sorted) {
		
		for (int i = 0; i < N_RUNS; i++) {
			sorted.floor(gen.nextInt());
		}
	}
	
	@Test
	@Override
	void testToArray() {
		Integer[] actual = set.toArray(empty);
		Arrays.sort(actual);
		Arrays.sort(numbers);
		assertArrayEquals(numbers, actual);
		
	}

	protected abstract Sorted<Integer> getSortedCollection();

	@Test
	void performanceTestRandomAdding() {
		Sorted<Integer> sorted = getSortedCollection();
		IntStream.range(0, N_ELEMNTS).forEach(i -> sorted.add(gen.nextInt()));
		runPerformanceTest(sorted);
	}
	
	

}
