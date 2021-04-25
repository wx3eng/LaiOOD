package LaiSetMap;

public class HackEntry<K, V> {
    private K key;
    private V value;
    private HackEntry<K, V> next;

    public HackEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setNext(HackEntry<K, V> next) {
        this.next = next;
    }

    public K getKey() {
        return key;
    }

    public HackEntry<K, V> getNext() {
        return next;
    }

}
