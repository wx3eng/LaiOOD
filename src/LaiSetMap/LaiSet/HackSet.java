package LaiSetMap.LaiSet;

//Set implementation with extension from duplicate-allowed HashSet, is more efficient than the variant below.

public class HackSet<E> extends DuplicateAllowedHashSet<E> {

    public HackSet() { super(true); }

}

/*

//Set implementation with a direct extension from HashMap, is not as efficient as the variant above.

public class TreeBucketHashSet<E> {

    TreeBucketHashMap<E, E> hashSet;

    TreeBucketHashSet() {
        hashSet = new TreeBucketHashMap<>();
    }

    public int size() { return hashSet.size(); }
    public boolean isEmpty() { return hashSet.isEmpty(); }
    public boolean add(E element) { return hashSet.put(element, element); }
    public boolean contains(E element) { return hashSet.contains(element); }
    public boolean remove(E element) { return hashSet.remove(element)!=null; }
    public E removeRandom() { return hashSet.removeRandom(); }

}

 */



