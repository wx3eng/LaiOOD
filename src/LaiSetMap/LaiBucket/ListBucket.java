package LaiSetMap.LaiBucket;

import LaiSetMap.LaiEntry.Entry;
import LaiSetMap.LaiEntry.ListEntry;

import java.util.Comparator;

public class ListBucket<K, V> extends Bucket<K, V> implements Comparator<K> {

    private ListEntry<K, V> head;
    private int nodes;

    public int count() { return nodes; }

    public V getValue(K key) {
        ListEntry<K, V> temp = head;
        while(temp!=null && !equalElements(temp.getKey(), key))
            temp = temp.getNext();
        return temp==null ? null : temp.getValue();
    }

    public boolean search(K key) {
        ListEntry<K, V> temp = head;
        while(temp!=null && !equalElements(temp.getKey(), key))
            temp = temp.getNext();
        return temp!=null;
    }

    public V insert(K key, V value) {
        if(head==null) {
            head = new ListEntry<>(key, value);
            nodes++;
            return null;
        }
        ListEntry<K, V> temp = head;
        while(temp.getNext()!=null && !equalElements(temp.getNext().getKey(), key))
            temp = temp.getNext();
        if(temp.getNext()==null) {
            temp.appendNext(new ListEntry<>(key, value));
            nodes++;
            return null;
        }
        return temp.getNext().getValue();
    }

    public V delete(K key) {
        if(head==null) return null;
        if(equalElements(head.getKey(), key)) {
            V returnValue = head.getValue();
            head = head.getNext();
            nodes--;
            return returnValue;
        }
        ListEntry<K, V> temp = head;
        while(temp.getNext()!=null && !equalElements(temp.getNext().getKey(), key))
            temp = temp.getNext();
        if(temp.getNext()==null) return null;
        V returnValue = temp.getNext().getValue();
        temp.appendNext(temp.getNext().getNext());
        nodes--;
        return returnValue;
    }

    public Entry<K, V> delete() {
        if(head==null) return null;
        ListEntry<K, V> returnEntry = head;
        head = head.getNext();
        returnEntry.appendNext(null);
        nodes--;
        return returnEntry;
    }

    public boolean isEmpty() { return head==null; }

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
    private boolean equalElements(K key1, K key2) { return (key1==null && key2==null) || (key1!=null && key1.equals(key2)); }

}
