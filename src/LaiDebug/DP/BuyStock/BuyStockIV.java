package LaiDebug.DP.BuyStock;

public class BuyStockIV {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(maxProfit(array, 3));
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

        for(int i=1; i<array.length; i++)
            reference[i][0] = reference[0][i];

        int max = reference[0][array.length-1];

        for(int i=1; i<k && i<array.length/2; i++) {
            for(int j=array.length-1; reference[j-2][i-1]!=0; j--) {
                for(int n=j-2; reference[n][i-1]!=0; n--)
                    reference[j][i] = Math.max(reference[j][i], reference[n][i-1]+reference[n+1][j]);
            }
            max = Math.max(max, reference[array.length-1][i]);
        }

        return max;
    }

    /*
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

     */
}
