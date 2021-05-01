package LaiSetMap.LaiEntry;

public class ListEntry<K, V> extends Entry<K, V> {

    private ListEntry<K, V> next;
    private final K key;
    private V value;

    public ListEntry(K key, V value) {
        this.key = key;
        this.value = value;
        next = null;
    }

    public void appendNext(ListEntry<K, V> node) {
        next = node;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

    public ListEntry<K, V> getNext() { return next; }

    public void setValue(V value) { this.value = value; }

}
