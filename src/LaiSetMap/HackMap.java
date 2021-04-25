package LaiSetMap;

public class HackMap<K, V> {

    private final static double LOADING_FACTOR = 0.75;
    private HackEntry<K, V>[] array;
    private int size;

    public HackMap() {
        array = (HackEntry<K, V>[]) new HackEntry[16];
        size = 0;
    }

    public V get(K key) {
        HackEntry<K, V> head = array[index(key)];
        while(head!=null) {
            if(equalsValue(head.getKey(), key)) {
                return head.getValue();
            }
        }
        return null;
    }

    public void put(K key, V value) {
        HackEntry<K, V> head = array[index(key)];
        if(head==null) {
            array[index(key)] = new HackEntry<K, V>(key, value);
            size++;
            return;
        }
        if(head!=null && equalsValue(head.getKey(), key)) {
            head.setValue(value);
            return;
        }
        while(head.getNext()!=null) {
            if(equalsValue(head.getNext().getKey(), key)) {
                head.setValue(value);
                return;
            }
            head = head.getNext();
        }
        head.setNext(new HackEntry<K, V>(key, value));
        size++;
    }

    public V remove(K key) {
        HackEntry<K, V> node = array[index(key)];
        if(node==null) return null;
        if(node!=null && equalsValue(node.getKey(), key)) {
            V value = node.getValue();
            array[index(key)] = node.getNext();
            size--;
            return value;
        }
        while(node.getNext()!=null) {
            if(equalsValue(node.getNext().getKey(), key)) {
                V value = node.getValue();
                node.setNext(node.getNext().getNext());
                size--;
                return value;
            }
            node = node.getNext();
        }
        return null;
    }

    private int hash(K key) {
        return (key==null) ? 0 : (key.hashCode() & 0x7FFFFFFF);
    }

    private int index(K key) {
        return hash(key) % array.length;
    }

    private boolean equalsValue(K key1, K key2) { return (key1==null && key2==null) || (key1!=null && key1.equals(key2)); }

}
