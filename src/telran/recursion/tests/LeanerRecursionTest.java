package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;

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

}
