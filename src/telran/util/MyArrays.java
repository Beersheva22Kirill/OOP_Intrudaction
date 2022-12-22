package telran.util;

import java.util.Comparator;

public class MyArrays {
	
	public static <T> void sort(T[] objects, Comparator<T> comparator) {
		int length = objects.length;
		do {
			length--;
		} while (moveMaxAtEnd(objects,length,comparator));
	}
	
	public static <T> int binarySearch (T[] objects, T searchedNumber, Comparator<T> comparator) {
		int left = 0;
		int right = objects.length - 1;
		int middle = right/2;	
		while (left <= right && objects[middle] != searchedNumber) {
			if (comparator.compare(objects[middle], searchedNumber) > 0) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}				
			middle = (left + right) /2;
		}		
		return left > right ? (middle * (-1)) - 1 : middle;
	}


	private static <T> boolean moveMaxAtEnd(T[] objects, int length, Comparator<T> comparator) {
		boolean flag = false; 
		for (int i = 0; i < length; i++) {
			 if (comparator.compare(objects[i], objects[i+1]) > 0) {
				 swap(objects, i, i + 1);
				 flag = true;
			 }
		 }
		return flag;
	}

	private static <T> void swap(T[] objects, int i, int j) {
		T temp = objects[i];
		objects[i] = objects[j];
		objects[j] = temp;	
	}
	
	

}
