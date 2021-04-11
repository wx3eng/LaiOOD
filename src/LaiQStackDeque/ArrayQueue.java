package LaiQStackDeque;

public class ArrayQueue<T> {

    private static final int INITIAL_CAPACITY = 13;
    private T[] queue;
    private int start;
    private int end;
    private int nonNullCount;

    public ArrayQueue(){
        queue = (T[]) new Object[INITIAL_CAPACITY];
        start = 0;
        end = 0;
        nonNullCount = 0;
    }

    public int size() {
        return (end+queue.length-start)%queue.length;
    }

    public void offer(T element) {
        if(start==(end+1)%queue.length) {
            increaseCapacity();
        }
        if(element!=null) {
            nonNullCount++;
        }
        queue[end] = element;
        end = (end+1)%queue.length;
    }

    public T peek() {
        return start==end ? null : queue[start];
    }

    public T poll() {
        if(start==end) {
            return null;
        }
        T returnInt = queue[start];
        start = (start+1)%queue.length;
        if(returnInt!=null) {
            nonNullCount--;
        }
        return returnInt;
    }

    public boolean isEmpty() {
        return start==end;
    }

    private void increaseCapacity() {
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[queue.length * 5/2];
        for(int i=0; i<queue.length-1; i++) {
            newArray[i] = queue[(i+start)%queue.length];
        }
        start = 0;
        end = queue.length-1;
        queue = newArray;
    }

    public boolean allNull() {
        return nonNullCount==0;
    }

}
