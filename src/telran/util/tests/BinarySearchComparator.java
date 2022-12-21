package telran.util.tests;

import java.util.Comparator;

public class BinarySearchComparator implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
		int res = 0;
		if (o1 < o2) {
			res = 1;
		}else if (o1 > o2) {
			res = -1;
		}
		return res;
	}

}
