package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.HashMap;

class HashMapTest extends MapTest {

	@BeforeEach
	@Override
	void setUp() throws Exception {
		map = new HashMap<>();
		super.setUp();
	}

}
