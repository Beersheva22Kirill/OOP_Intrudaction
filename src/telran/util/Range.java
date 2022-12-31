package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {

	 private static final String EXCEPTION_MASSAGE = "max value maust be greater min value";
	 Integer min;
	 Integer max;
	 
	 private class RangeIterator implements Iterator<Integer>{
		Integer current = min;
		@Override
		public boolean hasNext() {
			
			return current < max;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return current++;
		}
		 
	 }
	 
	@Override
	public Iterator<Integer> iterator() {
		
		return new RangeIterator();
	}

	public Range(Integer min, Integer max) {
		if (max <= min) {
			throw new IllegalArgumentException(EXCEPTION_MASSAGE);
		}
		this.min = min;
		this.max = max;
	}
	
	public boolean chekNumber(Integer number) {
		return number >= min && number < max;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		if (min >= max) {
			throw new IllegalArgumentException(EXCEPTION_MASSAGE);
		}
		this.min = min;
	}

	public Integer getMax() {
		if (max < min) {
			throw new IllegalArgumentException(EXCEPTION_MASSAGE);
		}
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
	
	

}
