package LaiSetMap.LaiBucket;

import LaiSetMap.LaiEntry.Entry;

import java.util.Comparator;

public abstract class Bucket<K, V> implements Comparator<K> {

    public abstract int count();
    public abstract V getValue(K key);
    public abstract boolean search(K key);
    public abstract V insert(K key, V value);
    public abstract V delete(K key);
    public abstract Entry<K, V> delete();
    public abstract boolean isEmpty();

    @Override
    public int compare(K key1, K key2) {
        if(key1 instanceof Integer) { return (Integer) key1-(Integer) key2; }
        if(key1 instanceof Character) { return (Character) key1-(Character) key2; }
        if(key1 instanceof String) {
            String s1 = (String) key1;
            String s2 = (String) key2;
            for(int i=0; i<s1.length(); i++) {
                if(i>=s2.length() || s1.charAt(i)>s2.charAt(i)) return 1;
                if (s1.charAt(i)<s2.charAt(i)) return -1;
            }
            return s1.length()==s2.length() ? 0 : -1;
        }
        return 0;
    }

    // Equals function
    protected boolean equalElements(K key1, K key2) { return (key1==null && key2==null) || (key1!=null && key1.equals(key2)); }

}
