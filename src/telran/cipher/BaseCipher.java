package telran.cipher;

import java.util.Arrays;

public class BaseCipher {
	
	private String key;
	
	public BaseCipher(int lenght) {
		char generetedSymbol = getRandomChar(33, 126);
		String key = Character.toString(generetedSymbol);
		boolean[] arrayRepeat = new boolean[94];
		arrayRepeat[(int)generetedSymbol - 33] = true;
		
		if (lenght < 2) {
			lenght = 2;
		} 	else if (lenght > 93) {
			lenght = 93;
			}
		int i = 1;
		while(i < lenght) {
			generetedSymbol = getRandomChar(33, 126);
			if (!arrayRepeat[(int)generetedSymbol - 33]) {
				key += Character.toString(generetedSymbol);
				arrayRepeat[(int)generetedSymbol - 33] = true;
				i++;
			}
		}
		this.key = key;
	}
	
	private char getRandomChar(long min, long max) {
		int res =  (int) (min + Math.random() * (max - min + 1));
	return (char)res;
}
	
	public String presentation() {
		String res = this.key;
		return res;
	}
	
	public String cipher (int number) {
		String key = this.key;
		char[] keyArr = key.toCharArray();
		int systemCount = keyArr.length;
		String res = Character.toString(keyArr[number%systemCount]) ; 
		number = number/systemCount;
		int indexSymbol = 0;
		while (number > 0) { 
			if (number > systemCount) {
				indexSymbol = number%systemCount;	
			} else {
				indexSymbol = number;		
			}
			res = keyArr[indexSymbol] + res;
			number = number/systemCount;
		}
		
	
		return res;
	}
	
	
	public int decipher (String cipherNumber) {
		int res = 0;
		String key = this.key;
		char[] keyArr = key.toCharArray();
		char[] decipherArr = cipherNumber.toCharArray();
		int pow = decipherArr.length - 1;
			
		for (int i = 0; i < decipherArr.length; i++) {
			int index = -1, j = 0;
			while(index == -1 && j < keyArr.length) {
				if (keyArr[j] == decipherArr[i]) {
					index = j;
				} else {
					j++;
				}
				
			}
			res += (int) ((index) * Math.pow(keyArr.length, pow));
			pow--;
			}
		
		
		return res;
	}

}
