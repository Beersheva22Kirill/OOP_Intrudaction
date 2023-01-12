package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import telran.util.HashSet;

class HashSetTests extends SetTest {
	Random random = new Random();
	private static final int N_RUNS = 10;

	private static final int N_NUMBERS = 100000;

	@Test
	@Override
	void testIterator() {
		Predicate<Integer> allPredicate = n -> true;
		HashSet<Integer> set = new HashSet<>();
		fillSet(set,new Integer[] {0, 16, 32, 48, 512, 128});
		set.removeIf(allPredicate);
		assertTrue(set.isEmpty());
		for (int i = 0; i < N_RUNS; i++) {
			Integer[] bigArray = getRandomArray();
			fillSet(set,bigArray);
			set.removeIf(allPredicate);
			assertTrue(set.isEmpty());
		}
		
	}


	private Integer[] getRandomArray() {
		Integer[] result = new Integer[N_NUMBERS];
		for (int i = 0; i < N_NUMBERS; i++) {
			result[i] = random.nextInt();
		}
		return result;
	}

	private void fillSet(HashSet<Integer> set, Integer[] numbers) {
		for (Integer num : numbers) {
			set.add(num);
		}
		
	}

}