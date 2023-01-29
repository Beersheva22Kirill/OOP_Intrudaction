package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.recursion.MdArray;

class MdArrayTest {
	
	MdArray<Integer> mdArray;
	
	@BeforeEach
	void setUp() {
		mdArray = new MdArray<>(new int[]{10,5,7}, 50); 
	}
	
	@Test
	void forEachTest() {		
		Integer[] sum = {0}; 
		mdArray.forEach(num-> sum[0]+=num);
		assertEquals(17500, sum[0]);
	}
	
	@Test
	void forEachTestString () {
		String string = "hello";
		MdArray<String> mdArrayString = new MdArray<>(new int[]{3, 15,7, 2, 10}, string); 
		Integer[] length = {0}; 
		mdArrayString.forEach(str -> length[0]+=str.length());
		assertEquals(6300, length[0]/string.length());
	}
	
	@Test
	void toArrayTest() {
		Integer[] array = new Integer[1];
		Integer[]expected = new Integer[350];
		for (int i = 0; i < expected.length; i++) {
			expected[i] = 50;
		}
		assertArrayEquals(expected, mdArray.toArray(array));
	}
	
	@Test
	void setAndGetValueTest() {
		int[]address = new int[] {3,2,3};
		assertEquals(50, mdArray.getValue(new int[] {0,0,0}));
		assertEquals(50, mdArray.getValue(new int[] {3,2,3}));
		mdArray.setValue(address, 51);
		assertEquals(51, mdArray.getValue(address));	
		assertEquals(50, mdArray.getValue(new int[] {0,0,0}));
	}

}
