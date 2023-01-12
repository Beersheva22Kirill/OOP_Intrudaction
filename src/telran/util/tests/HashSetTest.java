package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.HashSet;

class HashSetTest extends SetTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new HashSet<>(4, 0.75f);
		super.setUp();
	}
	
}
