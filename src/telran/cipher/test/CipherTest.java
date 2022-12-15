package telran.cipher.test;

import org.junit.jupiter.api.Test;

import telran.cipher.BaseCipher;

class CipherTest {

	@Test
	void BaseCipherTest() {
		BaseCipher key = new BaseCipher(10);
		displayString("Key: " + key.presentation());
		System.out.println();
		displayString("Cipher number: " + key.cipher(54));
		System.out.println();
		System.out.println("Decipher number: " + key.decipher(key.cipher(54)));
	}
	
	@Test
	private void displayString(String string) {
		char[] res = string.toCharArray();	
		for(Character ch:res) {
			System.out.print(ch);
		}
	}
	
	@Test
	private void displayNumber(String string) {
		char[] res = string.toCharArray();
		System.out.println();
		for(Character ch:res) {			
			System.out.print(Integer.toBinaryString((int)ch) + " ");
		}
	}
	


}
