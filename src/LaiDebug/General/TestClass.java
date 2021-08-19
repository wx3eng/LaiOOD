package LaiDebug.General;

import java.util.*;

public class TestClass {

    public static void main(String[] args) {

        int[][] matrix = new int[][] {{1, 2}, {3, 4}};
        matrix[0] = matrix[1];
        for (int[] array : matrix) {
            System.out.println(Arrays.toString(array));
        }
        matrix[0][0] = -1;
        for (int[] array : matrix) {
            System.out.println(Arrays.toString(array));
        }
    }


}
