package LaiSetMap;

import LaiTree.TreeAVL;

public class DuplicateAllowedHashSet<E> {

    private final static double LOADING_FACTOR = 0.75;
    private TreeAVL<E>[] array;
    private int size;
    private final boolean noDuplicates;

    public DuplicateAllowedHashSet(boolean noDuplicates) {
        array = (TreeAVL<E>[]) new TreeAVL[13];
        size = 0;
        this.noDuplicates = noDuplicates;
    }

    public DuplicateAllowedHashSet() {
        this(false);
    }

    public int get(E element) { return array[index(element)]==null ? 0 : array[index(element)].search(element); }

    public boolean isEmpty() { return size==0; }

    public int size() { return size; }

    public boolean add(E element) {
        if(element==null) return false;
        if(size > array.length * LOADING_FACTOR) increaseCapacity();
        if(array[index(element)]==null) array[index(element)] = new TreeAVL<>();
        if(noDuplicates && array[index(element)].search(element)==1) return false;
        if(noDuplicates || array[index(element)].search(element)==0) size++;
        return array[index(element)].insert(element);
    }

    public int remove(E element) {
        if(array[index(element)]==null) return 0;
        if(array[index(element)].isEmpty()) {
            array[index(element)] = null;
            return 0;
        }
        int result = array[index(element)].search(element);
        if(result==0) return 0;
        array[index(element)].delete(element);
        size--;
        return result-1;
    }

    public E removeRandom() {
        if(size==0) return null;
        for (int i=0; i<array.length; i++) {
            if (array[i]==null) continue;
            E element = array[i].delete();
            if(array[i].search(element)==0) size--;
            if(array[i].isEmpty()) array[i] = null;
            return element;
        }
        return null;
    }

    private void increaseCapacity() {
        TreeAVL<E>[] oldArray = array;
        array = (TreeAVL<E>[]) new TreeAVL[(int) (oldArray.length * 2.2360679775)];
        for (TreeAVL<E> treeNode : oldArray) {
            if (treeNode == null) continue;
            while (!treeNode.isEmpty()) {
                E element = treeNode.delete();
                if(array[index(element)]==null) array[index(element)] = new TreeAVL<>();
                array[index(element)].insert(element);
            }
        }
    }

    private int hash(E element) {
        return (element==null) ? 0 : (element.hashCode() & 0x7FFFFFFF);
    }

    private int index(E element) {
        return hash(element) % array.length;
    }

}
