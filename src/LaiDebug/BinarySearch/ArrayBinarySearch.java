package LaiDebug.BinarySearch;

import java.util.Arrays;

public class ArrayBinarySearch {

    public static void main(String[] args) {

    }

    // logarithmic method:
    public static int[] binarySurroundSearch(int[][] matrix, int target) {
        return binarySurround(matrix, target, 0, matrix.length-1, 0, matrix[0].length-1);
    }

    private static int[] binarySurround(int[][] matrix, int target, int up, int down, int left, int right) {
        while(true) {
            down = largestSmallVertical(matrix, target, up, down, left);
            if (down == -1) return new int[]{-1, -1};
            if (matrix[down][left] == target) return new int[]{down, left};
            up = smallestLargeVertical(matrix, target, up, down, right);
            if (up == -1) return new int[]{-1, -1};
            if (matrix[up][right] == target) return new int[]{up, right};
            right = largestSmallHorizontal(matrix, target, up, left, right);
            if (right == -1) return new int[]{-1, -1};
            if (matrix[up][right] == target) return new int[]{up, right};
            left = smallestLargeHorizontal(matrix, target, down, left, right);
            if (left == -1) return new int[]{-1, -1};
            if (matrix[down][left] == target) return new int[]{down, left};
        }
    }

    private static int largestSmallVertical(int[][] matrix, int target, int up, int down, int left) {
        if(down<up) return -1;
        if(down==up) return up;
        while(up<down-1) {
            int middle = up + (down-up)/2;
            if(target<matrix[middle][left]) down = middle-1;
            else if(target>matrix[middle][left]) up = middle;
            else return middle;
        }
        if(target<matrix[up][left]) return -1;
        if(target>=matrix[down][left]) return down;
        return up;
    }

    private static int smallestLargeVertical(int[][] matrix, int target, int up, int down, int right) {
        if(down<up) return -1;
        if(down==up) return down;
        while(up<down-1) {
            int middle = up + (down-up)/2;
            if(target>matrix[middle][right]) up = middle+1;
            else if(target<matrix[middle][right]) down = middle;
            else return middle;
        }
        if(target>matrix[down][right]) return -1;
        if(target<=matrix[up][right]) return up;
        return down;
    }

    private static int largestSmallHorizontal(int[][] matrix, int target, int up, int left, int right) {
        if(right<left) return -1;
        if(right==left) return left;
        while(left<right-1) {
            int middle = left + (right-left)/2;
            if(target<matrix[up][middle]) right = middle-1;
            else if(target>matrix[up][middle]) left = middle;
            else return middle;
        }
        if(target<matrix[up][left]) return -1;
        if(target>=matrix[up][right]) return right;
        return left;
    }

    private static int smallestLargeHorizontal(int[][] matrix, int target, int down, int left, int right) {
        if(right<left) return -1;
        if(right==left) return right;
        while(left<right-1) {
            int middle = left + (right-left)/2;
            if(target>matrix[down][middle]) left = middle+1;
            else if(target<matrix[down][middle]) right = middle;
            else return middle;
        }
        if(target>matrix[down][right]) return -1;
        if(target<=matrix[down][left]) return left;
        return right;
    }


    // linear method:
    private static int[] arraySearch(int[][] matrix, int target) {
        if(matrix.length==0 || matrix[0].length==0) return new int[] {-1, -1};
        int row = matrix.length-1;
        int column = 0;
        while(row>=0 && column<matrix[0].length) {
            if(target > matrix[row][column]) column++;
            else if(target < matrix[row][column]) row--;
            else return new int[] {row, column};
        }
        return new int[] {-1, -1};
    }

}
