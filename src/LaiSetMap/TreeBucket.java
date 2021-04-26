package LaiSetMap;

import java.util.Comparator;

public class TreeBucket<K, V> implements Comparator<K> {

    protected TreeEntry<K, V> root;

    public int height() {
        return height(root);
    }

    public V getValue(K key) {
        if(key==null) return null;
        TreeEntry<K, V> temp = root;
        while(temp!=null && !equalElements(key, temp.getKey())) {
            temp = compare(key, temp.getKey())<0 ? temp.getLeft() : temp.getRight();
        }
        return temp==null ? null : temp.getValue();
    }

    public boolean search(K key) {
        if(key==null) return false;
        TreeEntry<K, V> temp = root;
        while(temp!=null && !equalElements(key, temp.getKey())) {
            temp = compare(key, temp.getKey())<0 ? temp.getLeft() : temp.getRight();
        }
        return temp!=null;
    }

    public V insert(K key, V value) {
        TreeEntry<K, V>[] result = (TreeEntry<K, V>[]) new TreeEntry[] {new TreeEntry<>(key, value)};
        root = insert(root, result);
        return result[0]==null ? null : result[0].getValue();
    }

    public V delete(K key) {
        if(key==null) return null;
        TreeEntry<K, V>[] result = (TreeEntry<K, V>[]) new TreeEntry[] {new TreeEntry<>(key, null)};
        root = delete(root, result);
        return result[0].getValue();
    }

    public TreeEntry<K, V> delete() {
        if(root==null) return null;
        K returnKey = root.getKey();
        V returnValue = delete(returnKey);
        return new TreeEntry<>(returnKey, returnValue);
    }

    public boolean isEmpty() { return root==null; };

    private int height(TreeEntry<K, V> node) { return node==null ? 0 : node.getHeight(); }

    private TreeEntry<K, V> insert(TreeEntry<K, V> node, TreeEntry<K, V>[] element) {

        if(node==null) {
            TreeEntry<K, V> temp = element[0];
            element[0] = null;
            return temp;
        }
        if(equalElements(element[0].getKey(), node.getKey())) {
            V temp = node.getValue();
            node.setValue(element[0].getValue());
            element[0].setValue(temp);
            return node;
        }
        if (compare(element[0].getKey(), node.getKey())<0) node.appendLeft(insert(node.getLeft(), element));
        else if (compare(element[0].getKey(), node.getKey())>0) node.appendRight(insert(node.getRight(), element));
        return balance(node);
    }

    private TreeEntry<K, V> delete(TreeEntry<K, V> node, TreeEntry<K, V>[] element) {

        if(node==null) return null;
        if(element[0].getKey()==null) {
            if(node.getRight()!=null) {
                node.appendRight(delete(node.getRight(), element));
            }
            else {
                element[0] = node;
                return node.getLeft();
            }
        }
        else if(equalElements(element[0].getKey(), node.getKey())) {
            element[0] = new TreeEntry<>(null, node.getValue());
            if(node.getLeft()==null && node.getRight()==null) return null;
            if(node.getLeft()==null) return node.getRight();
            if(node.getRight()==null) return node.getLeft();
            node.appendLeft(delete(node.getLeft(), element));
            element[0].appendLeft(node.getLeft());
            element[0].appendRight(node.getRight());
            TreeEntry<K, V> temp = node;
            node = element[0];
            element[0] = temp;
        }
        else {
            if (compare(element[0].getKey(), node.getKey())<0) node.appendLeft(delete(node.getLeft(), element));
            else if (compare(element[0].getKey(), node.getKey())>0) node.appendRight(delete(node.getRight(), element));
        }

        return balance(node);
    }

    private TreeEntry<K, V> balance(TreeEntry<K, V> node) {

        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());

        if(leftHeight-rightHeight > 1) {
            TreeEntry<K, V> leftNode = node.getLeft();
            if(height(leftNode.getLeft()) < height(leftNode.getRight())) {
                node.appendLeft(rotateLeft(leftNode));
            }
            return rotateRight(node);
        }
        if(rightHeight-leftHeight > 1) {
            TreeEntry<K, V> rightNode = node.getRight();
            if(height(rightNode.getRight()) < height(rightNode.getLeft())) {
                node.appendRight(rotateRight(rightNode));
            }
            return rotateLeft(node);
        }

        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
        return node;
    }

    private TreeEntry<K, V> rotateLeft(TreeEntry<K, V> node) {
        TreeEntry<K, V> temp = node.getRight();
        node.appendRight(temp.getLeft());
        temp.appendLeft(node);
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight()) + 1));
        temp.setHeight(Math.max(height(temp.getLeft()), height(temp.getRight()) + 1));
        return temp;
    }

    private TreeEntry<K, V> rotateRight(TreeEntry<K, V> node) {
        TreeEntry<K, V> temp = node.getLeft();
        node.appendLeft(temp.getRight());
        temp.appendRight(node);
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight()) + 1));
        temp.setHeight(Math.max(height(temp.getLeft()), height(temp.getRight()) + 1));
        return temp;
    }

    // Lazy implementation for Integer and Character here
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
