package telran.util;

import java.util.Iterator;
import java.util.Map.Entry;

abstract class AbstractMap<K, V> implements Map<K, V> {
	
protected Set<Entry<K, V>> set;

@Override
public V put(K key, V value) {
	V res = null;
	Entry<K, V> entry = set.get(new Entry<>(key, null));
	if (entry != null) {
		res = entry.getValue();
		entry.setValue(value);
	} else {
		set.add(new Entry<>(key,value));
	}
	return res;
}




@Override
public V get(K key) {
	V res = null;
	Entry<K, V> entry = set.get(new Entry<>(key, null));
	if(entry != null) {
		res = entry.getValue();
	}
	return res;
}



@Override
public boolean containsKey(K key) {
	boolean res = false;
	Entry<K, V> entry = set.get(new Entry<>(key, null));
	if(entry != null) {
		res = true;
	}
	return res;
}

@Override
public boolean containsValue(V value) {

	return set.stream().anyMatch(e -> e.getValue().equals(value));
}

@Override
public Collection<V> values() {	
	Collection<V> collection = new LinkedList<>();
	Iterator<Entry<K,V>> iterator = set.iterator();
	while(iterator.hasNext()) {
		collection.add(iterator.next().getValue());
	}
	return collection;
}

@Override
public Set<K> keySet() {	
	try {
		@SuppressWarnings("unchecked")
		Set<K> res = set.getClass().getConstructor().newInstance();
		set.forEach(e -> res.add(e.getKey()));
		return res;
	} catch (Exception e) {
		throw new IllegalStateException();
	}
	
	
}

@Override
public Set<Entry<K, V>> entrySet() {
	try {
		@SuppressWarnings("unchecked")
		Set<Entry<K, V>> res = set.getClass().getConstructor().newInstance();
		set.forEach(res::add);
		return res;
	} catch (Exception e) {
		throw new IllegalStateException();
	} 
}

@Override
public V remove(K key) {
	V res = null;
	Entry<K, V> entry = set.get(new Entry<>(key, null));
	if (entry != null) {
		res = entry.getValue();
		set.remove(entry);
	}
	return res;
}

}
