package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.recursion.LinearRecursion.*;

import org.junit.jupiter.api.Test;

class LeanerRecursionTest {

	@Test
	void fTest() {
		f(5);
	}
	
	void f(int a) {
		if (a > 5) {
			f(a - 1);
		}
		
	}
	
	@Test
	void factorialTest() {
		assertEquals(24, factorial(4));
		assertEquals(24 * 5 * 6 , factorial(6));
	}
	
	@Test
	void powTest() {
		assertEquals(1 , power(3,0));
		assertEquals(3 , power(3,1));
		assertEquals(27 , power(3,3));
		assertEquals(-27 , power(-3,3));
		assertThrowsExactly(IllegalArgumentException.class, () -> power(5, -3));
	}
	
	@Test
	void powNewTest() {
		//assertEquals(1 , powerNew(3,0));
		//assertEquals(3 , powerNew(3,1));
		assertEquals(81 , powerNew(3,4));
		assertEquals(-27 , powerNew(-3,3));
		assertThrowsExactly(IllegalArgumentException.class, () -> powerNew(5, -3));
	}
	
	@Test 
	void summTest () {
		int[] array = {1,2,3,4,5,6};
		assertEquals(21, summ(array));
		assertEquals(0, summ(new int[0]) );
	}
	
	@Test
	void reverseTest() {
		int[] ar = {1,2,3,4,5,6};
		int[] expected = {6,5,4,3,2,1};
		
		int[] ar1 = {1,2,3,4,5,6,7};
		int[] expected1 = {7,6,5,4,3,2,1};
		reverse(ar);
		reverse(ar1);
		assertArrayEquals(expected, ar);
		assertArrayEquals(expected1, ar1);
	}

}
