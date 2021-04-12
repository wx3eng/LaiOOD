package LaiHeap;

public class IntMinHeap {

    // 1) heap must be a complete tree so that it can be stored in an array without a gap
    // 2) utility: easy to find a node's children (or parent) by a closed-form expression
    // almost exactly the same as the maxHeap.

    private int[] array;
    private int size;
    private boolean sizeFixed;

    public IntMinHeap(int length) {
        array = new int[length];
        size = 0;
        sizeFixed = false;
    }

    public IntMinHeap() {
        this(13);
    }

    public boolean add(int element) {
        if(size==array.length) {
            if(sizeFixed) return false;
            increaseCapacity();
        }
        array[size] = element;
        int currentIndex = size++;
        while(currentIndex!=0) {
            int parentIndex = (currentIndex-1)/2;
            if(array[currentIndex] >= array[parentIndex]) break;      // key conditional that defines the minHeap character. The only difference from maxHeap.
            swap(array, currentIndex, parentIndex);
            currentIndex = parentIndex;
        }
        return true;
    }

    public Integer remove() {
        if(size==0) return null;
        Integer returnValue = array[0];
        array[0] = array[--size];
        int parentIndex = 0;
        while(parentIndex<size-1) {
            int childIndex = 2*parentIndex + 1;
            if(childIndex > size-1) return returnValue;
            int desirableChildIndex = ((childIndex+1 > size-1) || (array[childIndex] < array[childIndex+1])) ? childIndex : childIndex+1; // key conditional that defines the minHeap character. The only difference from maxHeap.
            if(array[parentIndex] <= array[desirableChildIndex]) return returnValue;    // key conditional that defines the minHeap character. The only difference from maxHeap.
            swap(array, parentIndex, desirableChildIndex);
            parentIndex = desirableChildIndex;
        }
        return returnValue;
    }

    public int size() {
        return size;
    }

    public Integer peek() {
        return size==0 ? null : array[0];
    }

    public void lockSize() {
        sizeFixed = true;
    }

    public void unlockSize() {
        sizeFixed = false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return sizeFixed && size==array.length;
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private void increaseCapacity() {
        int[] newArray = new int[array.length * 5/2];
        for(int i=0; i<array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

}
