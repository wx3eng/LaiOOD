package LaiSetMap.LaiBucket;

import LaiSetMap.LaiEntry.Entry;

public abstract class Bucket<K, V> {

    public abstract int count();
    public abstract V getValue(K key);
    public abstract boolean search(K key);
    public abstract V insert(K key, V value);
    public abstract V delete(K key);
    public abstract Entry<K, V> delete();
    public abstract boolean isEmpty();

}
