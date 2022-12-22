package telran.util.tests;

import java.util.function.Predicate;

public class SubstrPredicate implements Predicate<String> {
	String subStr;
	@Override
	public boolean test(String t) {

		return t.contains(subStr);
	}
	public SubstrPredicate(String subStr) {
		this.subStr = subStr;
	}

}
