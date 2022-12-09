package telran.cipher.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.cipher.BaseCipher;

class CipherTest {

	@Test
	void BaseCipherTest() {
		BaseCipher key = new BaseCipher(5);
		displayString(key.presentation());
	}
	
	@Test
	private void displayString(String string) {
		char[] res = string.toCharArray();	
		for(Character ch:res) {
			System.out.print(ch);
		}
	}
	


}
