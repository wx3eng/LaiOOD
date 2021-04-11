package LaiQStackDeque;

import java.util.stream.IntStream;

public class ArrayStack<T> {

    private static final int INITIAL_CAPACITY = 13;
    private T[] stack;
    private int size;

    public ArrayStack(){
        stack = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public void push(T element) {
        if(size==stack.length) {
            T[] newArray = (T[]) new Object[size * 5/2];
            IntStream.range(0, stack.length).forEach(i -> newArray[i] = stack[i]);
            stack = newArray;
        }
        stack[size++] = element;
    }

    public T peek() {
        return size==0 ? null : stack[size-1];
    }

    public T pop() {
        return size==0 ? null : stack[--size];
    }

    public boolean isEmpty() {
        return size==0;
    }

    public T[] returnArrayReverse() {
        T[] returnArray = (T[]) new Object[size];
        for(int i=0; i<size; i++) {
            returnArray[i] = stack[size-1-i];
        }
        return returnArray;
    }

}
