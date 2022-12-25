package telran.util.tests;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.function.Predicate;
import org.junit.jupiter.api.*;
import telran.util.MyArrays;

class MyArraysTest {
	
	Integer numbers[] = {13, 2, -8, 47, 100, 10, 7};
	String[] strings = {"ab","abm","abmb","abmbc"};

	Comparator<Integer> evenOddComparator = this::evenOddCompare;	
	int evenOddCompare(Integer o1, Integer o2) {
		int res = 0; 
		
		if (o1 % 2 == 0 && o2 % 2 == 0) {			
			res = o1 < o2 ? -1 : 1; 
			} else if (o1 % 2 != 0 && o2 % 2 != 0) {			
			res = o1 < o2 ? 1 : -1;			
				} else if (o1 % 2 == 0 && o2 % 2 != 0) {			
			res = -1;			
		} else {			
			res = 1;
		}
		return res;
	}
	
	@Test
	@Disabled
	void sortTest() {
		String[] strings = {"abcd","lmn","zz"};
		String[] expected = {"zz", "lmn", "abcd"};			
		MyArrays.sort(strings, new StringLengthComparator());
		assertArrayEquals(expected, strings);
		
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
	assertEquals( 3, MyArrays.binarySearch(numbersArr, 10, new IntegerBSComparator()));
	assertEquals( 0, MyArrays.binarySearch(numbersArr, -8, new IntegerBSComparator()));
	assertEquals( 7, MyArrays.binarySearch(numbersArr, 23, new IntegerBSComparator()));
	assertEquals( -8, MyArrays.binarySearch(numbersArr, 17, new IntegerBSComparator()));
	assertEquals( -1, MyArrays.binarySearch(numbersArr, -9, new IntegerBSComparator()));
	assertEquals( -9, MyArrays.binarySearch(numbersArr, 25, new IntegerBSComparator()));
	}
	
	@Test
	@Disabled
	void binarySearchStrTest() {		
	assertEquals( 0, MyArrays.binarySearch(strings, "ab", new StringsComparator()));
	assertEquals( -1, MyArrays.binarySearch(strings, "a", new StringsComparator()));
	}
	
	@Test
	@Disabled
	void filterTest() {
		int dividor = 2;
		String subStr = "m";
		Predicate<Integer> predEven = t -> t % dividor == 0;
		Predicate<String> predSubStr = s -> s.contains(subStr);
		String[] expectedStr = {"abm","abmb","abmbc"};
		Integer[] expectedNum =  {2, -8, 100, 10};
		assertArrayEquals(expectedStr, MyArrays.filter(strings,predSubStr));
		assertArrayEquals(expectedNum, MyArrays.filter(numbers,predEven));
	}
	
	@Test	
	void removeIfTest() {
		int dividor = 2;
		String subStr = "m";
		Predicate<Integer> predEven = t -> t % dividor == 0;
		Predicate<String> predSubStr = s -> s.contains(subStr);
		String[] expectedStr = {"ab"};
		Integer[] expectedNum =  {13, 47, 7};
		assertArrayEquals(expectedStr, MyArrays.removeIf(strings,predSubStr));
		assertArrayEquals(expectedNum, MyArrays.removeIf(numbers,predEven));
	}
	@Test
	void removeRepeatTest() {
		String[] expectedStr = {"ab","abb","abd","abm"};
		String[] strings = {"ab","abb","abd","ab","abb","abm"};
		Integer[] numbers =  {1, 1, 2, 2, 1, 3, 5, 5, 4, 4, 4, 9};
		Integer[] expectedNum =  {1, 2, 3, 5, 4, 9};
		assertArrayEquals(expectedStr, MyArrays.removeRepeated(strings));
		assertArrayEquals(expectedNum, MyArrays.removeRepeated(numbers));
	}
	
	@Test
	void containsTest() {
		String[] strings = {"ab","abb","abd",null,"ab","abb","abm"};
		Integer[] numbers =  {1, 1, 2, 2, 1, 3,null,5, 5, 4, 4, 4, 9};
		assertTrue(MyArrays.contains(strings,"abm"));
		assertTrue(MyArrays.contains(strings,null));
		assertTrue(MyArrays.contains(numbers,2));
		assertTrue(MyArrays.contains(numbers,null));
		assertFalse(MyArrays.contains(numbers,10));
		assertFalse(MyArrays.contains(strings,"fff"));
	}
	

	

}
