package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

class StreamTest {
	static ArrayList<Integer> arrayList;
	static Integer[] numbers = {10, 20, 3, 8, 100, 200, -10, -5};
	
	@BeforeAll
	static void setUp() throws Exception {
		arrayList = new ArrayList<>();
		Arrays.stream(numbers).forEach(arrayList::add);
	}

	@Test
	void sortTest() {
		Integer[] expected = {-10, -5, 3, 8, 10, 20, 100, 200};
		assertArrayEquals(expected, arrayList.stream().sorted().toArray(Integer[]::new));
	}
	
	@Test
	void sumNegativeTest() {
		assertEquals(-15, arrayList.stream().filter(n -> n < 0).mapToInt(n -> n).sum());
	}
	
	@Test
	@Disabled
	void minValue() {
		assertEquals(-10, arrayList.stream().mapToInt(n -> n).min());
	}
	
	@Test
	void stringArrayTest() {
		String[] expected = {"10", "20", "3", "8", "100", "200", "-10", "-5"};
		assertArrayEquals(expected, arrayList.stream().map(n -> "" + n).toArray(String[]::new));
	}
	
	@Test
	void ArrayToStringTest() {
		String expected = "10 20 3 8 100 200 -10 -5";
		assertEquals(expected, arrayList.stream().map(n -> "" + n).collect(Collectors.joining(" ")));
	}
	
	@Test
	void sportLototest() {
		new Random().ints(1,50).distinct().limit(7)
		.forEach(num -> System.out.print(num + "; "));
	}

}
