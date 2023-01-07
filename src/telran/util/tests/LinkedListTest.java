package telran.util.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class LinkedListTest extends ListTest {
	
	LinkedList<Integer> linkedListCollection;
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
		linkedListCollection = (LinkedList<Integer>) collection;
	}
	
	@Test
	void IsLoopTest() {
		assertFalse(linkedListCollection.hasLoop());
		linkedListCollection.setNext(4, 2);
		assertTrue(linkedListCollection.hasLoop());
		
	}

}