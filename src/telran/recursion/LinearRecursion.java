package telran.recursion;

public class LinearRecursion {
	
	public static long factorial(int num) {
		long res = 0;
		if (num < 0) {
			res = factorial(-num);
		} else if ( num < 2) {
			res = 1;
		} else {
			res = num * factorial(num - 1);
		}
		return res;
	}
	
	public static long power(int num, int pow) {
		if (pow < 0) {
			throw new IllegalArgumentException();
		}
		
		long res = 0;
		if (pow < 1) {
			res = 1;
		} else {
			res = num * power(num, pow - 1);
		}
		return res;
	}
	
	public static int powerNew(int num, int pow) {
		
		if (pow < 0) {
			throw new IllegalArgumentException();
		}
		int res = 0;
		
		if(pow == 0) {
			res = 1;
		} else {
			res = multiply(num, powerNew(num, pow - 1));	
		}
		return res;
	}
	
	private static int multiply(int num, int pow) {
		int res;
		if (pow < 0) {
			res = multiply(num, -pow);
		} else if (pow == 0){
			res = 0;
		} else {
			res = num + multiply(num, pow - 1);
		}
		
		return res;
	}

	public static long square (int x) {
		long res = 0;
		
		if (x < 0) {
			res = square(-x);
		} else if (x > 0) {
			res = x + x - 1 + square(x - 1) ;
		}

		return res;
	}
	
	public static long summ ( int[] array) {
		
		return summ(0, array);
	}
	
	private static long summ(int firstIndex, int[] array) {
		long res = 0;
		if (firstIndex != array.length) {
			res = array[firstIndex] + summ(firstIndex + 1, array);
		} 
		return res;
	}
	
	public static void reverse(int[] array) {
		
		reverse(0,array.length - 1,array);
			
	}

	private static void reverse(int firsIndex, int lastIndex, int[] array) {		
		if(firsIndex < lastIndex) {
			int a = array[firsIndex];
			array[firsIndex] = array[lastIndex];
			array[lastIndex] = a;
			reverse(firsIndex + 1, lastIndex - 1, array);
		}
	}
	
	public static boolean isSubstring(String string, String subString) {
		boolean res = false;
		
		if (string.length() > 0) {
			if (subString.charAt(0) == string.charAt(0)) {
				String str = subString;
				if (str.length() > 0) {
					if(isSub(string, str) == 0) {
						res = res | isSubstring(string.substring(1), subString);
					} else res = true;
				}	
			} else {
				res = res |isSubstring(string.substring(1), subString);
			}
				
		}
		return res;
	}

	private static int isSub(String string, String str) {
		int res = 0;
		if (str.length() > 0) {
			if (string.charAt(0) == str.charAt(0)) {
				res = res | isSub(string.substring(1), str.substring(1));
			}  
		} else {
			res = 1;
		}
		return res;
	
	}

}
