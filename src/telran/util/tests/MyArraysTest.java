package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;

class MyArraysTest {
	
	Integer numbers[] = {13, 2, -8, 47, 100, 10, 7};
	String[] strings = {"ab","abm","abmb","abmbc"};

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
	Integer expected[] = {-8, 2, 10, 100, 47, 13, 7};
	MyArrays.sort(numbers, new EvenOddComparator());
	assertArrayEquals(expected, numbers);
	}
	
	@Test
	void binarySearchTest() {
	Integer numbersArr[] = {-8,6,9,10,11,15,16,23};
	assertEquals( 1, MyArrays.binarySearch(numbersArr, 6, new IntegerBSComparator()));
	assertEquals( -7, MyArrays.binarySearch(numbersArr, 17, new IntegerBSComparator()));
	}
	
	@Test
	void binarySearchStrTest() {		
	assertEquals( 0, MyArrays.binarySearch(strings, "ab", new StringsComparator()));
	assertEquals( -1, MyArrays.binarySearch(strings, "a", new StringsComparator()));
	}
	
	@Test	
	void filterTest() {
		int devidor = 2;
		String subStr = "m";
		Predicate<Integer> predEven = new DividorPredicate(devidor);
		Predicate<String> predSubStr = new SubstrPredicate(subStr);
		String[] expectedStr = {"abm","abmb","abmbc"};
		Integer[] expectedNum =  {2, -8, 100, 10};
		assertArrayEquals(expectedStr, MyArrays.filter(strings,predSubStr));
		assertArrayEquals(expectedNum, MyArrays.filter(numbers,predEven));
	}
	
	

}
