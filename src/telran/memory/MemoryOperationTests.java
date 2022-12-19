package telran.memory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class MemoryOperationTests {

	private static final long MGB = 1024 * 1024;
	byte[] array;
	
	@Test
	@Disabled
	void maxMemoryTest() {
		int maxMemory = MemoryOperations.getMaxAvaliableMemory();
		array = new byte[maxMemory];
		array = null;
		boolean flException = false;
		try {
				array = new byte [maxMemory + 1];			
					
		} catch (Throwable e) {
			flException = true;
		}
		assertTrue(flException);
	}
	
	@Test
	void standardMemoryMethods() {
			Runtime runtime = Runtime.getRuntime();
			System.out.printf("Max memory JVM may require from OS: %d," 
					+ "current total JVM memory: %d, " + "free memory JVM: %d", 
					runtime.maxMemory()/MGB, runtime.totalMemory()/MGB, runtime.freeMemory()/MGB);
	}

}
