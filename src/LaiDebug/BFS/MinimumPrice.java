package LaiDebug.BFS;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

public class MinimumPrice {

    public static void main(String[] args) {

        System.out.println(findCheapestPrice(7, new int[][]{{3,4,17},{3,5,26},{4,5,8},{4,6,29},{5,6,6}}, 3, 6, 53));

    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        if(src==dst)
            return 0;

        PriorityQueue<Pair> reference = new PriorityQueue<>(n, new Comparator<Pair>() {

            @Override
            public int compare(Pair p1, Pair p2) {
                return p1.value<p2.value ? -1 : 1;
            }

        });
        Deque<Pair> buffer = new ArrayDeque<>();

        for(int[] flight : flights) {
            if(flight[0]==src)
                reference.offer(new Pair(flight[1], flight[2], 0));
        }
        int sum = Integer.MAX_VALUE;
        int stops = 0;

        while(stops<=k && !reference.isEmpty()) {
            int size = reference.size();
            for(int i=0; i<size; i++) {
                Pair current = reference.poll();
                if(current.destination==dst)
                    sum = Math.min(sum, current.sum+current.value);
                else if(stops+1<=k)
                    for(int[] flight : flights)
                        if(flight[0]==current.destination)
                            buffer.offerLast(new Pair(flight[1], flight[2], current.sum+current.value));
            }
            while(!buffer.isEmpty())
                reference.offer(buffer.pollFirst());
            stops++;
        }

        return sum==Integer.MAX_VALUE ? -1 : sum;
    }

    private static class Pair {
        int destination;
        int value;
        int sum;
        private Pair(int destination, int value, int sum) {
            this.destination = destination;
            this.value = value;
            this.sum = sum;
        }
    }

}
