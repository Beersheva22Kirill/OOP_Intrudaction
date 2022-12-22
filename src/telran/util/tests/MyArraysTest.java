package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;

class MyArraysTest {

	@Test
	@Disabled
	void sortTest() {
		String[] strings = {"abcd","lmn","zz"};
		String[] expected = {"zz", "lmn", "abcd"};
		
		Integer[] array = {3,2,1};
		
		MyArrays.sort(strings, new StringLengthComparator());
		assertArrayEquals(expected, strings);
		MyArrays.sort(array, (a, b) -> a - b);
		
	}
	
	@Test
	void evenOddTest() {
	Integer numbers[] = {13,2,	-8,47, 100, 10, 7};
	Integer expected[] = {-8, 2, 10, 100, 47, 13, 7};
	MyArrays.sort(numbers, new EvenOddComparator());
	assertArrayEquals(expected, numbers);
	}
	
	@Test
	void binarySearchTest() {
	Integer numbers[] = {-8,6,9,10,11,15,16,23};
	assertEquals( 1, MyArrays.binarySearch(numbers, 6, new BinarySearchComparator()));
	assertEquals( -7, MyArrays.binarySearch(numbers, 17, new BinarySearchComparator()));
	}
	
	@Test
	void binarySearchStrTest() {
		
 		String[] strings = {"ab","abm","abmb","abmbc"};
 		
 		Comparator<String> comp = new StringsComparator();
	assertEquals( 0, MyArrays.binarySearch(strings, "ab", comp));
	assertEquals( -1, MyArrays.binarySearch(strings, "a", comp));
	}

}
