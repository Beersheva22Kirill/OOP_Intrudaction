package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import telran.util.*;

class RangeIteratorTest {

	Integer[] numbers = {1,2,3,4,5};
	
	@Test
	void test() {
		Range range = new Range(1, 6);
		ArrayList<Integer> list = new ArrayList();
//		Iterator<Integer> it = range.iterator();
//		
//		while(it.hasNext()) {
//			list.add(it.next());
//		}
		
		for (Integer num: range) {
			list.add(num);
		}
		
		assertArrayEquals(numbers, list.toArray(new Integer[0]));
		
	}
	

}
