package LaiDebug.DP.ArrayHopper;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class ArrayHopperIV {

    public static void main(String[] args) {
        int[] array = new int[]{6,0,2,0,1,0,4};
        for(int i=0; i<array.length; i++)
            System.out.println(arrayHopperIV(array, i));
    }

    public static int arrayHopperIV(int[] array, int index) {

        if(array==null || array.length==0)
            return -1;
        if(index==array.length-1)
            return 0;

        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> reference = new HashSet<>();

        queue.offerLast(array.length-1);
        reference.add(array.length-1);
        int count = 1;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int k=0; k<size; k++) {
                int current = queue.pollFirst();
                for (int i=0; i<array.length-1; i++)
                    if (!reference.contains(i) && i-array[i]<=current && i+array[i]>=current) {
                        if (i==index)
                            return count;
                        queue.offerLast(i);
                        reference.add(current);
                    }
            }
            count++;
        }

        return -1;
    }

}
