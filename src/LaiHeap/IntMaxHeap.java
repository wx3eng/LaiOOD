package LaiHeap;

public class IntMaxHeap {

    // 1) heap must be a complete tree so that it can be stored in an array without a gap
    // 2) utility: easy to find a node's children (or parent) by a closed-form expression
    // almost exactly the same as the minHeap.

    private int[] array;
    private int size;
    private boolean sizeFixed;

    //constructors, not algorithm-essential
    public IntMaxHeap(int length) {
        array = new int[length];
        size = 0;
        sizeFixed = false;
    }

    public IntMaxHeap() {
        this(13);
    }

    //priorityQueue add function: accepts an integer input. If the array doesn't allow expansion and is full, add will fail (returns false), otherwise add is successful (returns true at the very end).
    //when add is successful, the added element is percolated up with respect to its parents: swap the element and its parent when the element is larger than its parent
    //because the larger the element's value, the more it should be closer to the root (or top) relative to other nodes.
    public boolean add(int element) {
        //base case: if the array is defined to be not expandable and is full, cannot add element into the queue. otherwise, increase capacity and return true at last.
        if(size==array.length) {
            if(sizeFixed) return false;
            increaseCapacity();
        }
        //add the element to the end of the array/queue.
        array[size] = element;
        //update the queue size and set currentIndex as a starting index for percolating up.
        int currentIndex = size++;
        //when the currentIndex/index-of-interest is not 0 (meaning that the node at current index/current node can still be percolated up), find its parent node
        //and swap with the parent node when the parent node is smaller than the current node (so to put the maxheap in order).
        while(currentIndex!=0) {
            int parentIndex = (currentIndex-1)/2;
            //if the current node is not larger than its parent node, the maxheap is already in order, so no need to continue percolating up (so return true and be done).
            if(array[currentIndex] <= array[parentIndex]) return true;
            //otherwise swap(percolate) the current node and its parent node and update the current index to its parent to continue the next iteration of percolate.
            swap(array, currentIndex, parentIndex);
            currentIndex = parentIndex;
        }
        return true;
    }

    //priorityQueue remove function: removes the maximum value/root value/top value (array[0]) from the priorityQueue and returns that value.
    //if the queue is already empty, return null.
    //if the queue isn't empty, remove the top value/root value/maximum value (array[0]), then put the last/end/tail element at the top index/root index (==0),
    //then percolate down the now top node to make the maxheap in order.
    public Integer remove() {
        //base case: return null if there are no elements in the queue.
        if(size==0) return null;
        //otherwise get the top value/root value/max value and get ready to return it (named as returnValue).
        Integer returnValue = array[0];
        //move the end/tail element to the top/root index and update the queue's size accordingly.
        array[0] = array[--size];
        //define an index (at root) for percolating down the now top element.
        int parentIndex = 0;
        //when parentIndex/index-of-interest is less than the index of the last element in the queue (a.k.a have not finished percolating down),
        //find its child nodes and swap with the larger of the children (since in a maxheap, larger nodes should be placed higher/more in front).
        while(parentIndex<size-1) {
            //childIndex is the left child index of the parent node/node-of-interest; childIndex+1 is the respective right child index.
            int childIndex = 2*parentIndex + 1;
            //if the childIndex(a.k.a. left child) is non-existent(meaning that the right child is also nonexistent due to complete tree property),
            //the maxheap is already in order, so just return the returnValue.
            if(childIndex > size-1) return returnValue;
            //else (if the left child is existent): if the right child is nonexistent (meaning that the parent can only percolate to the left child) OR
            //the left child is greater than the right child, choose the left child to percolate to. Otherwise, percolate to the right child.
            int desirableChildIndex = ((childIndex+1 > size-1) || (array[childIndex] > array[childIndex+1])) ? childIndex : childIndex+1;
            //if the child node of choice to percolate to is not smaller than the parent node, the maxheap is already in order, so just return the returnValue.
            if(array[parentIndex] >= array[desirableChildIndex]) return returnValue;
            //otherwise, swap(percolate) the parent node and its child of choice and update the parentIndex to continue the next iteration of percolate.
            swap(array, parentIndex, desirableChildIndex);
            parentIndex = desirableChildIndex;
        }
        return returnValue;
    }

    //other functions that may be of interest
    public Integer peek() {
        return size==0 ? null : array[0];
    }

    public int size() {
        return size;
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


    //helper functions
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
