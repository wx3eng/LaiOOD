package LaiDebug.DP.BuyStock;

public class BuyStockIV {

    public static void main(String[] args) {
        int[] array = new int[]{1, 4, 6, 9, 5, 1, 4, 6, 6, 4, 3, 8, 7, 7, 6, 5, 3, 4, 5, 1};
        for(int i=0; i<array.length/2; i++)
            System.out.println(maxProfit(array, i));
    }

    public static int maxProfit(int[] array, int k) {

        if(array==null || array.length<2 || k<=0)
            return 0;

        int[][] reference = new int[array.length][array.length];

        for(int i=0; i<reference.length-1; i++) {
            int pivot = i;
            for(int j=i+1; j<reference.length; j++) {
                if(array[j]<=array[pivot])
                    pivot = j;
                reference[i][j] = Math.max(reference[i][j-1], array[j]-array[pivot]);
            }
        }

        int[] globalMax = new int[]{0};
        profitSearch(reference, 0, globalMax, 0, k, 0, array.length-1);
        return globalMax[0];
    }

    private static void profitSearch(int[][] reference, int current, int[] globalMax, int depth, int levels, int index, int end) {

        if(depth==levels) {
            globalMax[0] = Math.max(globalMax[0], current);
            return;
        }
        int begin = index+1;
        while(begin<=end && reference[index][begin]==0)
            begin++;
        if(begin>end) {
            globalMax[0] = Math.max(globalMax[0], current);
            return;
        }

        for(int i=begin; i<=end; i++)
            profitSearch(reference, current+reference[index][i], globalMax, depth+1, levels, i, end);
    }

}
