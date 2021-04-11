package LaiGenericArrays;

import java.lang.reflect.Array;

public class GenericArray<T> {      // same as arrayList

    private T[] array;
    private int capacity;
    private int length;

    public GenericArray(int size) {
        array = (T[]) new Object[size];
        capacity = size;
        length = 0;
    }

    public GenericArray() {
      this(13);
    }

    public int capacity() {
        return capacity;
    }
    public int size() {
        return length;
    }

    public void modify(T element, int index) {
        if(index>=capacity) {
            return;
        }
        array[index] = element;
    }

    public void addLast(T element) {
        if(length>=capacity) {
            increaseCapacity();
        }
        array[length++] = element;
    }

    public T deleteLast() {
        if(length==0) return null;
        return array[--length];
    }

    public T get(int index) {
        if(index>=capacity) return null;
        return array[index];
    }

    private void increaseCapacity() {
        T[] newArray = (T[]) new Object[capacity * 5/2];
        for(int i=0; i<array.length; i++) newArray[i] = array[i];
        array = newArray;
        capacity = capacity *5/2;
    }

    public String returnPrintFormat() {
        if(length==0) {
            return null;
        }
        StringBuilder result = new StringBuilder("[");
        for(int i=0; i<length; i++) {
            result.append(array[i]);
            result.append((i==length-1) ? "]" : ", ");
        }
        return result.toString();
    }

    public T[] returnArray() {
        T[] returnArray = (T[]) new Object[length];
        for(int i=0; i<length; i++) {
            returnArray[i] = array[i];
        }
        return returnArray;
    }

    public T[] returnArrayReverse() {
        T[] returnArray = (T[]) new Object[length];
        for(int i=0; i<length; i++) {
            returnArray[i] = array[length-1-i];
        }
        return returnArray;
    }

    public void acceptArray(T[] list) {
        array = list;
        capacity = list.length;
        length = list.length;
    }
}
