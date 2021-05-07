package LaiDebug.DP.BuyStockIII;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BuyStockIII {

    public static void main(String[] args) {

        int[] array = new int[]{3,4,1,2,6,2,3,5,1,7,3,8};
        System.out.println(maxProfit(array));

    }

    public static int maxProfit(int[] array) {

        PriorityQueue<Integer> reference = new PriorityQueue<>(array.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if(a==b) return 0;
                return a>b ? -1 : 1;
            }
        });

        int index = 0;
        for(int i=1; i<array.length; i++) {
            if(array[i]<array[i-1]){
                if(array[i-1]>array[index])
                    reference.add(array[i-1]-array[index]);
                index = i;
            }
        }
        if(array[array.length-1]>array[index])
            reference.add(array[array.length-1]-array[index]);

        int sum = 0;
        int size = Math.min(reference.size(), 2);
        for(int i=0; i<size; i++)
            sum += reference.poll();

        return sum;
    }
}
