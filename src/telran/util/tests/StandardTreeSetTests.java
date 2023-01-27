
package telran.util.tests;

import org.junit.jupiter.api.BeforeEach;

import telran.util.Sorted;
import telran.util.StandardTreeSet;

public class StandardTreeSetTests extends SortedTest {
@BeforeEach
	@Override
void setUp() throws Exception {
	collection = new StandardTreeSet<Integer>();
	super.setUp();
}

@Override
protected Sorted<Integer> getSortedCollection() {
	
	return new  StandardTreeSet<Integer>();
}
}
