package LaiDebug.DP.MatrixDP;

public class MatrixProduct {

    public static void main(String[] args) {

        double[][] matrix = new double[][]{{0,-0.2,0,-1},{-4,0,-1,0}};
        System.out.println(largest(matrix));

    }

    public static double largest(double[][] matrix) {

        double[][] reference = new double[matrix.length+1][matrix[0].length+1];
        double[][] zeros = new double[matrix.length+1][matrix[0].length+1];

        double max = matrix[0][0];

        for(int i=0; i<=matrix.length; i++) {
            reference[i][0] = 1;
        }
        for(int i=1; i<=matrix[0].length; i++) {
            reference[0][i] = 1;
        }

        for(int i=1; i<=matrix.length; i++) {
            for(int j=1; j<=matrix[0].length; j++) {
                reference[i][j] = (reference[i-1][j]==0 ? 1 : reference[i-1][j]) * matrix[i-1][j-1];
                zeros[i][j] = zeros[i-1][j] + (matrix[i-1][j-1]==0 ? 1 : 0);
            }
        }

        for(int i=1; i<=matrix.length; i++) {
            for(int j=2; j<=matrix[0].length; j++) {
                reference[i][j] *= (reference[i][j-1]==0 ? 1 : reference[i][j-1]);
                zeros[i][j] += zeros[i][j-1];
            }
        }

        for(int i=1; i<=matrix.length; i++) {
            for(int j=1; j<=matrix[0].length; j++) {
                for(int m=1; m<=i; m++) {
                    for(int n=1; n<=j; n++) {
                        double downLeft = reference[i][n-1]==0 ? 1 : reference[i][n-1];
                        double topRight = reference[m-1][j]==0 ? 1 : reference[m-1][j];
                        double topLeft = reference[i][n-1]==0||reference[m-1][j]==0||reference[m-1][n-1]==0 ? 1 : reference[m-1][n-1];
                        double zeroCount = zeros[i][j]-zeros[i][n-1]-zeros[m-1][j]+zeros[m-1][n-1];
                        max = Math.max(max, zeroCount>0 ? 0 : reference[i][j]*topLeft/topRight/downLeft);
                    }
                }
            }
        }

        return max;
    }

}
