package telran.memory;

public class MemoryOperations {
	public static int getMaxAvaliableMemory() {
		int left = 0;
		int right = Integer.MAX_VALUE;
		int middle = (left + right)/2;
		boolean running = true;
		byte[] array = null;
			while(running) {
				try {
					array = null;
					array = new byte[middle];
					try {
						array = null;
						array = new byte[middle + 1];	
						left = middle;
					} catch (Throwable x) {
						running = false;
					}	
					if ((left + right)/2 < 0) {
						middle = left + (right - left) /2;
					} else  {
						middle = (left + right)/2;
					}		
				} catch (Throwable x) {				
					right = middle;
					middle = (left + right)/2;
				}
			}
		return middle;
	}
	
}	


