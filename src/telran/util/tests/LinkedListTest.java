package telran.util.tests;

import org.junit.jupiter.api.BeforeEach;

import telran.util.*;

class LinkedListTest extends ListTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
	}

}