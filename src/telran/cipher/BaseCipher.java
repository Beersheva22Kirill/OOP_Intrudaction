package telran.cipher;

public class BaseCipher {
	
	private String key;

	public BaseCipher(int lenght) {
		String key = null;
			for (int i = 0; i < lenght; i++) {
				key += (char)getRandomChar(33, 126); 
			}
		this.key = key;
	}
	
	public String presentation() {
		String res = this.key;
		return res;
	}
	
	private char getRandomChar(long min, long max) {
			int res =  (int) (min + Math.random() * (max - min + 1));
		return (char)res;
	}

}
