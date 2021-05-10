package LaiDebug.DP.MergeStones;

public class MergeStones {

    public static void main(String[] args) {
        int[] array = new int[]{2,1,1,3,5,7,10};
        System.out.println(minCost(array));
    }

    public static int minCost(int[] stones) {

        if(stones==null || stones.length<2)
            return 0;

        int[][] reference = new int[stones.length][stones.length];

        for(int j=0; j<stones.length; j++)
            reference[j][j] = stones[j];

        for(int i=1; i<stones.length; i++)
            for(int j=0; j+i<stones.length; j++)
                reference[j][j+i] = reference[j][j+i-1]+reference[j+i][j+i];

        return minCost(stones, reference, 0, stones.length-1);
    }

    private static int minCost(int[] stones, int[][] reference, int left, int right) {

        if(right-left==0)
            return 0;
        if(right-left==1)
            return reference[left][right];

        int min = Integer.MAX_VALUE;
        int leftPivot = left;
        int rightPivot = left+1;

        for(int i=left; i<right; i++) {
            int leftSum = minCost(stones, reference, left, i);
            int rightSum = minCost(stones, reference, i+1, right);
            if(leftSum+rightSum<min) {
                min = leftSum + rightSum;
                leftPivot = i;
                rightPivot = i+1;
            }
        }

        return reference[left][leftPivot] + reference[rightPivot][right] + min;
    }
}
