package telran.util.tests;

import java.util.Comparator;

public class StringLengthComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		int res = 0; 
		if (o1.length() > o2.length()) {
		res = 1;
	} else if (o1.length() < o2.length()){
		res = -1;
	} 	
		return res;
	}
}
