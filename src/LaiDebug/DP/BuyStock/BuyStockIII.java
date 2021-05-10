package LaiDebug.DP.BuyStock;

public class BuyStockIII {

    public static void main(String[] args) {

        int[] array = new int[]{1, 4, 3, 4};
        System.out.println(maxProfit(array));

    }

    public static int maxProfit(int[] array) {

        if(array==null || array.length<2)
            return 0;

        int[] reference = new int[array.length];

        int leftMax = 0;
        int rightMax = 0;
        int leftPivot = 0;
        int rightPivot = array.length-1;

        for(int i=1; i<array.length; i++) {
            if(array[i]<=array[leftPivot])
                leftPivot = i;
            else
                reference[i] += leftMax = Math.max(leftMax, array[i]-array[leftPivot]);

            if(array[array.length-1-i]>=array[rightPivot])
                rightPivot = array.length-1-i;
            else
                reference[array.length-1-i] += rightMax = Math.max(rightMax, array[rightPivot]-array[array.length-1-i]);
        }

        int globalMax = 0;
        for(int i=0; i<reference.length; i++)
            globalMax = Math.max(globalMax, reference[i]);

        return globalMax;
    }


}
