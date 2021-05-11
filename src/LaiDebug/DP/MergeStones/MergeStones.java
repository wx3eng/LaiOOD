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

        for(int j=0; j<stones.length; j++)
            reference[j][j] = 0;

        for(int j=0; j+1<stones.length; j++)
            reference[j+1][j] = reference[j][j+1];

        for(int i=2; i<stones.length; i++)
            for(int j=0; j+i<stones.length; j++) {
                reference[j+i][j] = Integer.MAX_VALUE;
                for(int k=j; k<j+i; k++)
                    reference[j+i][j] = Math.min(reference[j+i][j], reference[j][j+i]+reference[k][j]+reference[j+i][k+1]);
            }

        return reference[stones.length-1][0];
    }
}
