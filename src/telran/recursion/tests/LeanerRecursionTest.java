package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LeanerRecursionTest {

	@Test
	void fTest() {
		f();
	}
	
	void f() {
		f();
	}

}
