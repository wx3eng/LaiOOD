package LaiSetMap.LaiMap;

import LaiSetMap.LaiBucket.Bucket;
import LaiSetMap.LaiBucket.ListBucket;
import LaiSetMap.LaiBucket.TreeBucket;
import LaiSetMap.LaiEntry.Entry;

public class HybridHashMap<K, V> {

    private final static double LOADING_FACTOR = 0.75;
    private final static int LIST_TO_TREE_THRESHOLD = 8;
    private final static int TREE_TO_LIST_THRESHOLD = 3;
    private Bucket<K, V>[] array;
    private int size;

    public HybridHashMap() {
        array = (Bucket<K, V>[]) new Bucket[13];
        size = 0;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size==0; }

    public V get(K key) { return array[index(key)]==null ? null : array[index(key)].getValue(key); }

    public boolean put(K key, V value) {
        if(key==null)
            return false;
        if(size > array.length * LOADING_FACTOR)
            increaseCapacity();
        if(array[index(key)]==null)
            array[index(key)] = new ListBucket<>();
        if(array[index(key)].count()==LIST_TO_TREE_THRESHOLD && array[index(key)] instanceof ListBucket)
            listToTree(index(key));
        if(array[index(key)].insert(key, value)==null)
            size++;
        return true;
    }

    public boolean contains(K key) {
        if(key==null || array[index(key)]==null)
            return false;
        return array[index(key)].search(key);
    }

    public V remove(K key) {
        if(array[index(key)]==null)
            return null;
        if(array[index(key)].isEmpty()) {
            array[index(key)] = null;
            return null;
        }
        V returnValue = array[index(key)].delete(key);
        if(returnValue!=null)
            size--;
        if(array[index(key)].count()==TREE_TO_LIST_THRESHOLD && array[index(key)] instanceof TreeBucket)
            treeToList(index(key));
        return returnValue;
    }

    public K removeRandom() {
        if(size==0) return null;
        for (int i=0; i<array.length; i++) {
            if (array[i]==null) continue;
            Entry<K, V> node = array[i].delete();
            if(array[i].count()==TREE_TO_LIST_THRESHOLD && array[i] instanceof TreeBucket)
                treeToList(i);
            if(node==null) {
                array[i] = null;
                continue;
            }
            size--;
            return node.getKey();
        }
        return null;
    }

    private void increaseCapacity() {
        Bucket<K, V>[] oldArray = array;
        array = (Bucket<K, V>[]) new Bucket[(int) (oldArray.length * 2.2360679775)];
        for (Bucket<K, V> node : oldArray) {
            if (node == null)
                continue;
            while (!node.isEmpty()) {
                Entry<K, V> element = node.delete();
                K key = element.getKey();
                if(array[index(key)]==null)
                    array[index(key)] = new ListBucket<>();
                if(array[index(key)].count()==LIST_TO_TREE_THRESHOLD)
                    listToTree(index(key));
                array[index(key)].insert(key, element.getValue());
            }
        }
    }

    private void listToTree(int index) {
        TreeBucket<K, V> root = new TreeBucket<>();
        while(array[index]!=null) {
            Entry<K, V> temp = array[index].delete();
            if(temp==null)
                break;
            root.insert(temp.getKey(), temp.getValue());
        }
        array[index] = root;
    }

    private void treeToList(int index) {
        ListBucket<K, V> head = new ListBucket<>();
        while(array[index]!=null) {
            Entry<K, V> temp = array[index].delete();
            if(temp==null)
                break;
            head.insert(temp.getKey(), temp.getValue());
        }
        array[index] = head;
    }

    private int hash(K key) {
        return (key==null) ? 0 : (key.hashCode() & 0x7FFFFFFF);
    }

    private int index(K key) {
        return hash(key) % array.length;
    }

}
