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

        int[][] solution = new int[reference.length][reference.length];

        for(int j=0; j+1<stones.length; j++)
            solution[j][j+1] = reference[j][j+1];

        for(int i=2; i<stones.length; i++)
            for(int j=0; j+i<stones.length; j++) {
                solution[j][j+i] = Integer.MAX_VALUE;
                for(int k=j; k<j+i; k++)
                    solution[j][j+i] = Math.min(solution[j][j+i], reference[j][j+i]+solution[j][k]+solution[k+1][j+i]);
            }

        return solution[0][stones.length-1];
    }
}
