package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class MyArrays {
	
	public static <T> void sort(T[] objects, Comparator<T> comparator) {
		int length = objects.length;
		do {
			length--; 
		} while (moveMaxAtEnd(objects,length,comparator));
	}
	
	public static <T> int binarySearch (T[] objects, T key, Comparator<T> comparator) {
		int left = 0;
		int right = objects.length - 1;
		int middle = right/2;
		while (left <= right && !objects[middle].equals(key)) {
			if (comparator.compare(objects[middle],key) > 0) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}				
			middle = (left + right) /2;
		}		
		return left > right ? -left - 1 : middle;
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

	public static <T> T[] filter(T[] objects, Predicate<T> predicate) {
		int countPredicate = getCountPredicate(objects, predicate);
		
		T[] res = Arrays.copyOf(objects, countPredicate);
		int index = 0;
		for(T element: objects) {
			if(predicate.test(element)) {
				res[index++] = element;
			}
		}			
		return res;
	}


	private static <T> int getCountPredicate(T[] objects, Predicate<T> predicate) {
		int res = 0;
		
		for(T element: objects) {
			if(predicate.test(element)) {
				res++;
			}
		}
		return res;
	}
	
	public static <T> T[] removeIf(T[] objects, Predicate<T> predicate) {	
		T[] res = filter(objects, predicate.negate());
		return res;
	}
	
	public static <T> T[] removeRepeated(T[] objects) {
		int countUniqElement = countUniqElement(objects);
		T[] workArray = Arrays.copyOf(objects, objects.length);
		T[] res = Arrays.copyOf(objects, countUniqElement);
		int index = 0;
		while(workArray.length > 0) {
			T element = workArray[0];
			res[index++] = workArray[0];
			Predicate<T> predicate = t -> t == element;
			workArray = removeIf(workArray, predicate);	
		}
		return res;
	}

	private static <T> int countUniqElement(T[] objects) {
		int res = 0;
		T[] temp = Arrays.copyOf(objects, objects.length);
		while(temp.length > 0) {
			T element = temp[0];
			Predicate<T> predicate = t -> t == element;
			temp = removeIf(temp, predicate);
			res++;
		}
		return res;
	}
	
	public static <T> boolean  contains(T[] objects, T pattern) {
		int index = 0;
		while (index < objects.length && !isEquals(objects[index],pattern)) {
				index++;
			}
		return index < objects.length;
	}

	private static boolean isEquals(Object element, Object pattern) {
		
		return element == null ? element == pattern : element.equals(pattern);
	}
	
	public static <T> String  join(T[] objects, String delimetr) {
		String res = "";
		if (objects.length > 0) {
			StringBuilder builder = new StringBuilder(objects[0].toString());
			for (int i = 1; i < objects.length; i++) {
				builder.append(delimetr).append(objects[i]);
			}
			res = builder.toString();
		}
		return res;
	}	

}
