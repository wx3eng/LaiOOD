package LaiDebug.BFS;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class SevenPuzzle {

    public static void main(String[] args) {

        int[] input = new int[]{2,6,7,5,3,1,0,4};
        System.out.println(numOfSteps(input));

    }

    public static int numOfSteps(int[] values) {

        int defualt = 1234567;
        int input = 0;
        for(int i=0; i<values.length; i++)
            input = input*10 + values[i];

        Set<Integer> reference = new HashSet<>();
        Deque<Pair> queue = new ArrayDeque<>();

        queue.offerLast(new Pair(defualt, 0, 0));
        int count = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                Pair current = queue.pollFirst();
                if(current.number==input)
                    return count;
                reference.add(current.number);
                int currentPosition = 7-(4*current.row+current.column);
                if(current.row-1>=0) {
                    int swapPosition = 7-(4*(current.row-1)+current.column);
                    int swapTarget = (current.number/((int) Math.pow(10, swapPosition)))%10;
                    int next = current.number - swapTarget*((int) Math.pow(10, swapPosition)) + swapTarget*((int) Math.pow(10, currentPosition));
                    if(!reference.contains(next))
                        queue.offerLast(new Pair(next, current.row-1, current.column));
                }
                if(current.column-1>=0) {
                    int swapPosition = 7-(4*current.row+(current.column-1));
                    int swapTarget = (current.number/((int) Math.pow(10, swapPosition)))%10;
                    int next = current.number - swapTarget*((int) Math.pow(10, swapPosition)) + swapTarget*((int) Math.pow(10, currentPosition));
                    if(!reference.contains(next))
                        queue.offerLast(new Pair(next, current.row, current.column-1));
                }
                if(current.row+1<2) {
                    int swapPosition = 7-(4*(current.row+1)+current.column);
                    int swapTarget = (current.number/((int) Math.pow(10, swapPosition)))%10;
                    int next = current.number - swapTarget*((int) Math.pow(10, swapPosition)) + swapTarget*((int) Math.pow(10, currentPosition));
                    if(!reference.contains(next))
                        queue.offerLast(new Pair(next, current.row+1, current.column));
                }
                if(current.column+1<4) {
                    int swapPosition = 7-(4*current.row+(current.column+1));
                    int swapTarget = (current.number/((int) Math.pow(10, swapPosition)))%10;
                    int next = current.number - swapTarget*((int) Math.pow(10, swapPosition)) + swapTarget*((int) Math.pow(10, currentPosition));
                    if(!reference.contains(next))
                        queue.offerLast(new Pair(next, current.row, current.column+1));
                }
            }
            count++;
        }

        return -1;
    }

    private static class Pair {
        int number;
        int row;
        int column;

        Pair(int number, int row, int column) {
            this.number = number;
            this.row = row;
            this.column = column;
        }
    }

}
