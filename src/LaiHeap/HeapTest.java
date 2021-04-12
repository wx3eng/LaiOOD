package LaiHeap;

import java.util.Arrays;

public class HeapTest {

    public static void main(String[] args) {

        IntMaxHeap maxHeap = new IntMaxHeap(5);

        maxHeap.lockSize();

        System.out.println(maxHeap.add(-99));               // operations with locked heap
        System.out.println(maxHeap.add(99));
        System.out.println(maxHeap.add(16));
        System.out.println(maxHeap.add(19));
        System.out.println(maxHeap.add(0));
        System.out.println(maxHeap.add(8));
        System.out.println(maxHeap.add(7));
        int[] output = new int[maxHeap.size()];
        int size = maxHeap.size();
        for(int i=0; i<size; i++) {
            output[i] = maxHeap.remove();
        }
        System.out.println(Arrays.toString(output));

        maxHeap.unlockSize();

        System.out.println(maxHeap.add(-99));               // operations with unlocked heap
        System.out.println(maxHeap.add(99));
        System.out.println(maxHeap.add(16));
        System.out.println(maxHeap.add(19));
        System.out.println(maxHeap.add(0));
        System.out.println(maxHeap.add(8));
        System.out.println(maxHeap.add(7));

        output = new int[maxHeap.size()];
        size = maxHeap.size();
        for(int i=0; i<size; i++) {
            output[i] = maxHeap.remove();
        }
        System.out.println(Arrays.toString(output));
    }

}
