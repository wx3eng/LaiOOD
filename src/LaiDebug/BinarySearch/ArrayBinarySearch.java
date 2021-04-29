package LaiDebug.BinarySearch;

import java.util.Arrays;

public class ArrayBinarySearch {

    public static void main(String[] args) {

        int[] array1 = new int[]{ 0,  1,  2,  3,  4,  5,  6,  7,  7,  9};
        int[] array2 = new int[]{ 1,  2,  3,  4,  5,  6,  7,  7,  9, 10};
        int[] array3 = new int[]{ 2,  3,  4,  5,  6,  7,  7,  9, 10, 11};
        int[] array4 = new int[]{ 3,  4,  5,  6,  7,  8,  9, 10, 11, 12};
        int[] array5 = new int[]{ 4,  5,  6,  7,  7,  9, 10, 11, 12, 13};
        int[] array6 = new int[]{ 5,  6,  7,  7,  9, 10, 11, 12, 13, 14};
        int[] array7 = new int[]{ 6,  7,  7,  9, 10, 11, 12, 13, 14, 15};
        int[][] matrix = new int[][]{array1, array2, array3, array4, array5, array6, array7};

        for (int[] i : matrix) System.out.println(Arrays.toString(i));

        int target = 6;
        // logarithmic method:
        System.out.println(Arrays.toString(binarySurroundSearch(matrix, target)));
        // linear method:
        System.out.println(Arrays.toString(arraySearch(matrix, target)));
    }

    // logarithmic method:
    public static int[] binarySurroundSearch(int[][] matrix, int target) {
        return binarySurround(matrix, target, 0, matrix.length-1, 0, matrix[0].length-1);
    }

    private static int[] binarySurround(int[][] matrix, int target, int up, int down, int left, int right) {
        while(true) {
            int upperBound = largestSmallVertical(matrix, target, up, down, left);
            if (upperBound == -1) return new int[]{-1, -1};
            if (matrix[upperBound][left] == target) return new int[]{upperBound, left};
            int lowerBound = smallestLargeVertical(matrix, target, up, down, right);
            if (lowerBound == -1) return new int[]{-1, -1};
            if (matrix[lowerBound][right] == target) return new int[]{lowerBound, right};
            down = upperBound;
            up = lowerBound;
            int leftBound = largestSmallHorizontal(matrix, target, up, left, right);
            if (leftBound == -1) return new int[]{-1, -1};
            if (matrix[up][leftBound] == target) return new int[]{up, leftBound};
            int rightBound = smallestLargeHorizontal(matrix, target, down, left, right);
            if (rightBound == -1) return new int[]{-1, -1};
            if (matrix[down][rightBound] == target) return new int[]{down, rightBound};
            right = leftBound;
            left = rightBound;
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
