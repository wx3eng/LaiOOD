package LaiDebug.LargestRectangle;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangle {

    public static void main(String[] args) {
        int[] test = new int[]{4, 9, 6, 0, 8, 3, 2, 10};
        System.out.println(largestRectangle(test));
    }

    public static int largestRectangle(int[] array) {

        Deque<Pair> stack = new ArrayDeque<>();
        int max = 0;

        for(int i=0; i<array.length; i++) {
            Pair current = null;
            while(!stack.isEmpty() && array[i]<stack.peekFirst().value) {
                current = stack.pollFirst();
                max = Math.max(max, current.value*(i-current.index));
            }
            stack.offerFirst(new Pair(array[i], current==null ? i : current.index));
        }
        while(!stack.isEmpty()) {
            Pair current = stack.pollFirst();
            max = Math.max(max, current.value*(array.length-current.index));
        }

        return max;
    }

    private static class Pair {
        int value;
        int index;
        Pair(int value, int index){
            this.value = value;
            this.index = index;
        }
    }

}
