package LaiSetMap;

public class TreeBucketHashMap<K, V> {

    private final static double LOADING_FACTOR = 0.75;
    private TreeBucket<K, V>[] array;
    private int size;

    public TreeBucketHashMap() {
        array = (TreeBucket<K, V>[]) new TreeBucket[13];
        size = 0;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size==0; }

    public V get(K key) { return array[index(key)]==null ? null : array[index(key)].getValue(key); }

    public boolean put(K key, V value) {
        if(key==null) return false;
        if(size > array.length * LOADING_FACTOR) increaseCapacity();
        if(array[index(key)]==null) array[index(key)] = new TreeBucket<>();
        if(array[index(key)].insert(key, value)==null) size++;
        return true;
    }

    public boolean contains(K key) {
        if(key==null || array[index(key)]==null) return false;
        return array[index(key)].search(key);
    }

    public V remove(K key) {
        if(array[index(key)]==null) return null;
        if(array[index(key)].isEmpty()) {
            array[index(key)] = null;
            return null;
        }
        V returnValue = array[index(key)].delete(key);
        if(returnValue!=null) size--;
        return returnValue;
    }

    public K removeRandom() {
        if(size==0) return null;
        for (int i=0; i<array.length; i++) {
            if (array[i]==null) continue;
            TreeEntry<K, V> node = array[i].delete();
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
        TreeBucket<K, V>[] oldArray = array;
        array = (TreeBucket<K, V>[]) new TreeBucket[(int) (oldArray.length * 2.2360679775)];
        for (TreeBucket<K, V> treeNode : oldArray) {
            if (treeNode == null) continue;
            while (!treeNode.isEmpty()) {
                TreeEntry<K, V> element = treeNode.delete();
                K key = element.getKey();
                if(array[index(key)]==null) array[index(key)] = new TreeBucket<>();
                array[index(key)].insert(key, element.getValue());
            }
        }
    }

    private int hash(K key) {
        return (key==null) ? 0 : (key.hashCode() & 0x7FFFFFFF);
    }

    private int index(K key) {
        return hash(key) % array.length;
    }

}
