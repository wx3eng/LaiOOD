package LaiDebug.MatrixLinear;

public class SetMatrixZeros {

    public static void main(String[] args) {

    }

    public static void setZero(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        boolean[] rowHasZero = new boolean[matrix.length];
        boolean[] columnHasZero = new boolean[matrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    rowHasZero[i] = true;
                    columnHasZero[j] = true;
                }
            }
        }

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(rowHasZero[i] || columnHasZero[j]) {
                    matrix[i][j] = 0;
                }
            }
        }

        return;
    }
}
