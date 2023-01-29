package telran.recursion;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MdArray<T> {
	
private MdArray<T>[] array;
private T value;

public MdArray(int dimensions[], T value) {
	this(dimensions, 0, value);
}
@SuppressWarnings("unchecked")
public MdArray(int[] dimensions, int firstDim, T value) {
	if (firstDim == dimensions.length) {
		this.value = value;
		array = null;
	} else {
		this.value = null;
		array = new MdArray[dimensions[firstDim]];
		for(int i = 0; i < array.length; i++) {
			array[i] = new MdArray<>(dimensions, firstDim + 1, value);
		}
	}
}

public void forEach(Consumer<T> consumer) {
	
	for (int i = 0; i < array.length; i++) {
		forEach(consumer, array[i].array);
	}
	
		
	}
private void forEach(Consumer<T> consumer, MdArray<T>[] mdArray) {
	
	for (int i = 0; i < mdArray.length; i++) {
		if (mdArray[i].value != null) {
			consumer.accept(mdArray[i].value);			
		} else {			 
			 forEach(consumer, mdArray[i].array);
		}	
	}	
}

public void setValue (int[] address, T value)	{
	MdArray<T> elementOfArray = searchElementByAddress(address);
		elementOfArray.value = value; 	
}

public T getValue (int[]address) {
	MdArray<T> elementOfArray = searchElementByAddress(address);
	return elementOfArray.value;
}

private MdArray<T> searchElementByAddress(int[] address) {
	MdArray<T> elementOfArray = this;
	int i = 0;
		while(elementOfArray.value == null) {
			elementOfArray = elementOfArray.array[address[i]];
			i++;
		}
	return elementOfArray;
}

public T[] toArray (T[] array) {
	ArrayList<T> arrayList = new ArrayList<>();
	forEach(value -> arrayList.add(value));
	array = arrayList.toArray(array);
	return array;
}


}
