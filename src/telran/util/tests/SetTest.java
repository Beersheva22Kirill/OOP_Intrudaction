package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Set;

class SetTest extends CollectionsTest{
	Set<Integer> set;
	
	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		set = (Set<Integer>) collection;
	}
	@Test
	@Override
	void testAdd() {
		assertTrue(set.add(876));
		assertFalse(set.add(876));
	}
	


}
