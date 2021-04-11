package LaiQStackDeque;

public class ArrayDeck<T> {

    private static final int DEFAULT_CAPACITY = 13;
    private T[] deque;
    private int first;
    private int last;

    public ArrayDeck(){
        deque = (T[]) new Object[DEFAULT_CAPACITY];
        first = 0;
        last = 0;
    }

    public int size() {
        return (first+deque.length-last)%deque.length;
    }

    public void offerFirst(T element) {
        if (last==(first+1)%deque.length) {
            increaseCapacity();
        }
        deque[first] = element;
        first = (first+1)%deque.length;
    }

    public void offerLast(T element) {
        if (last==(first+1)%deque.length) {
            increaseCapacity();
        }
        last = (last-1+deque.length)%deque.length;
        deque[last] = element;
    }

    public T peekFirst() {
        return first==last ? null : deque[(first-1+deque.length)%deque.length];
    }

    public T peekLast() {
        return first==last ? null : deque[last];
    }

    public T pollFirst() {
        if (first==last) {
            return null;
        }
        first = (first-1+deque.length)%deque.length;
        return deque[first];
    }

    public T pollLast() {
        if (first==last) {
            return null;
        }
        T returnElement = deque[last];
        last = (last+1)%deque.length;
        return returnElement;
    }

    public boolean isEmpty() {
        return first==last;
    }

    private void increaseCapacity() {
        T[] newDeque = (T[]) new Object[deque.length * 5/2];
        for(int i=0; i<deque.length-1; i++) {
            newDeque[i] = deque[(i+last)%deque.length];
        }
        last = 0;
        first = deque.length-1;
        deque = newDeque;
    }

}
