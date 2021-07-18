package LaiDebug.DP.SubArrayPath;

public class LongestMatrixPath {

    // not good

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {21, 18, 8, 18, 0, 0, 28},
                {25, 22, 16, 13, 19, 8, 11},
                {30, 13, 2, 7, 22, 29, 20},
                {6, 9, 25, 30, 8, 25, 29},
                {21, 19, 17, 6, 23, 1, 6},
                {0, 21, 0, 11, 20, 24, 13},
                {15, 0, 21, 7, 29, 14, 21},
                {19, 20, 30, 12, 4, 32, 23},
                {7, 31, 21, 23, 20, 12, 6},
                {28, 18, 24, 24, 27, 21, 26},
                {15, 0, 11, 10, 29, 20, 10},
                {3, 13, 28, 19, 13, 4, 22},
                {14, 10, 12, 19, 15, 32, 23}
        };

        System.out.println(longestAscendingPath(matrix));
    }

    public static int longestAscendingPath(int[][] matrix) {

        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return 0;

        int[][] leftMatrix = new int[matrix.length][matrix[0].length];
        int[][] rightMatrix = new int[matrix.length][matrix[0].length];
        int[][] upMatrix = new int[matrix.length][matrix[0].length];
        int[][] downMatrix = new int[matrix.length][matrix[0].length];

        for(int i=0; i<leftMatrix.length; i++)
            for(int j=1; j<leftMatrix[0].length; j++)
                if(matrix[i][j]>matrix[i][j-1])
                    leftMatrix[i][j] = leftMatrix[i][j-1] + 1;
        for(int i=0; i<rightMatrix.length; i++)
            for(int j=rightMatrix[0].length-2; j>=0; j--)
                if(matrix[i][j]>matrix[i][j+1])
                    rightMatrix[i][j] = leftMatrix[i][j+1] + 1;
        for(int i=0; i<upMatrix[0].length; i++)
            for(int j=1; j<upMatrix.length; j++)
                if(matrix[j][i]>matrix[j-1][i])
                    upMatrix[j][i] = upMatrix[j-1][i] + 1;
        for(int i=0; i<downMatrix[0].length; i++)
            for(int j=downMatrix.length-2; j>=0; j--)
                if(matrix[j][i]>matrix[j+1][i])
                    downMatrix[j][i] = downMatrix[j+1][i] + 1;
        System.out.println("done");

        int[] globalMax = new int[]{1};

        for(int i=0; i<matrix.length; i++)
            for(int j=0; j<matrix[0].length; j++)
                findMaxPath(leftMatrix, rightMatrix, upMatrix, downMatrix, i, j, 1, globalMax);

        return globalMax[0];
    }

    private static void findMaxPath(int[][] leftMatrix, int[][] rightMatrix, int[][] upMatrix, int[][] downMatrix, int m, int n, int current, int[] globalMax) {

        if(Math.max(Math.max(leftMatrix[m][n], rightMatrix[m][n]), Math.max(upMatrix[m][n], downMatrix[m][n]))==0) {
            globalMax[0] = Math.max(current, globalMax[0]);
            return;
        }

        for(int i=1; i<=leftMatrix[m][n]; i++)
            findMaxPath(leftMatrix, rightMatrix, upMatrix, downMatrix, m, n-i, current+i, globalMax);
        for(int i=1; i<=rightMatrix[m][n]; i++)
            findMaxPath(leftMatrix, rightMatrix, upMatrix, downMatrix, m, n+i, current+i, globalMax);
        for(int i=1; i<=upMatrix[m][n]; i++)
            findMaxPath(leftMatrix, rightMatrix, upMatrix, downMatrix, m-i, n, current+i, globalMax);
        for(int i=1; i<=downMatrix[m][n]; i++)
            findMaxPath(leftMatrix, rightMatrix, upMatrix, downMatrix, m+i, n, current+i, globalMax);
    }

}
