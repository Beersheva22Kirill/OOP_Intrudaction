package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.HashMap;
import telran.util.TreeMap;

class TreeMapTest extends MapTest{

	@BeforeEach
	@Override
	void setUp() throws Exception {
		map = new TreeMap<>();
		super.setUp();
	}

}
