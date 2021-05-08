package LaiDebug.DP.LargestRectangle;

public class MatrixLargestRectangle {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 1, 1, 0, 0, 1, 1},
                {0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 1, 1, 0},
                {0, 1, 1, 0, 1, 1, 1, 0}
        };
        System.out.println(largestRectangleMatrix(matrix));
    }

    public static int largestRectangleMatrix(int[][] matrix) {

        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return 0;

        int max = 0;
        int [][] reference = new int[matrix.length][matrix[0].length];

        for(int i=0; i<matrix[0].length; i++)
            reference[0][i] = matrix[0][i];
        for(int i=0; i<matrix[0].length; i++)
            for(int j=1; j<matrix.length; j++)
                reference[j][i] = matrix[j][i]==0 ? 0 : reference[j-1][i]+matrix[j][i];

        for(int i=0; i<reference.length; i++)
            max = Math.max(max, LargestRectangle.largestRectangle(reference[i]));

        return max;
    }

}
