package LaiDebug.BFS;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CoinChange {

    public static void main(String[] args) {
        System.out.println(coinChange(112, new int[]{6,20,12,10,21,2,19,17,11,14,22,9,23,4}));
    }

    public static int coinChange(int amount, int[] coins) {

        Arrays.sort(coins);
        int boundary = removeInvalid(coins);

        PriorityQueue<Pair> heap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                if(p1.sum<p2.sum) return -1;
                if(p1.sum>p2.sum) return 1;
                if(p1.previous<p2.previous) return -1;
                if(p1.previous>p2.previous) return 1;
                return 0;
            }
        });

        int index = 0;
        while(index<boundary && coins[index]<=amount) {
            heap.offer(new Pair(coins[index], index++));
        }

        int count = 0;

        while(heap.size()>0) {
            Pair current = heap.poll();
            if(current.sum>amount) {
                return count;
            }
            if(current.sum==amount) {
                count++;
            } else {
                for(int i=current.previous; i<index; i++) {
                    if(current.sum+coins[i]>amount) {
                        break;
                    }
                    heap.offer(new Pair(current.sum+coins[i], i));
                }
            }
        }

        return count;
    }

    private static int removeInvalid(int[] coins) {

        int temp = 0;
        for(int i=0; i<coins.length; i++) {
            if(coins[i]>0 && (i==0 || coins[i]!=coins[temp-1]))
                coins[temp++] = coins[i];
        }

        return temp;
    }

    private static class Pair {
        private int sum;
        private int previous;
        Pair(int sum, int previous) {
            this.sum = sum;
            this.previous = previous;
        }
    }
}
