package LaiDebug.ArrayLinear;

import java.util.*;

public class KSlidingWindows {

    public static void main(String[] args) {

        int[] test = new int[]{1,2,3,4,5,6,7,8,9,1,1};
        System.out.println(maxWindows(test, 2));
    }

    public static List<Integer> maxWindows(int[] array, int k) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, (i1, i2) -> i1<i2 ? 1 : -1);
        Map<Integer, Integer> reference = new HashMap<>();
        List<Integer> solution = new ArrayList<>();

        for(int i=0; i<array.length; i++) {
            if(i>=k) {
                Integer current = reference.get(array[i-k]);
                reference.put(array[i-k], current-1);
            }
            Integer current = reference.get(array[i]);
            if(current==null) {
                priorityQueue.offer(array[i]);
                reference.put(array[i], 1);
            }
            else {
                reference.put(array[i], current+1);
            }
            if(i>=k-1) {
                while(!priorityQueue.isEmpty() && reference.get(priorityQueue.peek())==0) {
                    priorityQueue.poll();
                }
                solution.add(priorityQueue.peek());
            }
        }

        return solution;
    }

}
