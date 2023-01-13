package telran.util.tests;

import org.junit.jupiter.api.BeforeEach;

import telran.util.LinkedList;
import telran.util.TreeSet;

public class TreeSetTest extends SetTest {
	TreeSet<Integer> treeSetCollection;

	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<>();
		super.setUp();
		treeSetCollection = (TreeSet<Integer>) collection;
	}

}
