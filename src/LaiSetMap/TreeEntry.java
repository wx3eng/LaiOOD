package LaiSetMap;

public class TreeEntry<K, V> {

    private TreeEntry<K, V> left;
    private TreeEntry<K, V> right;
    private K key;
    private V value;
    private int height;

    public TreeEntry(K key, V value) {
        this.key = key;
        this.value = value;
        height = 1;
    }

    public void appendLeft(TreeEntry<K, V> node) {
        left = node;
    }

    public void appendRight(TreeEntry<K, V> node) {
        right = node;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

    public TreeEntry<K, V> getLeft() {
        return left;
    }

    public TreeEntry<K, V> getRight() {
        return right;
    }

    public void setValue(V value) { this.value = value; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

}
