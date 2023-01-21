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
	
	public static long powerNew(int num, int pow) {
		//TODO
		
		if (pow < 0) {
			throw new IllegalArgumentException();
		}
		powerNew (num,num,pow,pow);
		//code cannot cycles and operation * and /
		return powerNew (num,num,pow,pow);
	}
	
	private static int powerNew(int num,int num2, int pow, int pow2) {
		int res = 0;
		
		if(pow2 > 0) {
			res = sumNum(num, num2);
			powerNew(res, num2, pow, pow2 - 1);
		}
		return res;
	}

	private static int sumNum(int num, int pow) {
		int res = 0;
		if (pow > 0) {
			res = num + sumNum(num, pow - 1);
		}
		return res;
	}

	public static long square (int x) {
		//TODO
		//not cycles and not operators *, / and not additional methods
		//no static fields
		return 0;
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
	

}
