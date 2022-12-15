package telran.memory;

public class MemoryOperations {
	public static int getMaxAvaliableMemory() {
		int res = Integer.MAX_VALUE;
		boolean running = true;
		byte[] array = null;
		while(running) {
			try {
				array = new byte[res];
				running = false;
			} catch (Throwable e) {
				res /= 2;
			}
		}
		return res;
	}

}
