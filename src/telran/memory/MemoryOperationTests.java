package telran.memory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemoryOperationTests {

	byte[] array;
	
	@Test
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

}
