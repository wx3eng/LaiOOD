package LaiHeap;

public class IntMinHeap {

    // 1) heap must be a complete tree so that it can be stored in an array without a gap
    // 2) utility: easy to find a node's children (or parent) by a mathematical expression

    private int[] array;
    private int size;

    public IntMinHeap() {
        array = new int[13];
        size = 0;
    }

    private void increaseCapacity() {
        int[] newArray = new int[array.length * 5/2];
        for(int i=0; i<array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

}
