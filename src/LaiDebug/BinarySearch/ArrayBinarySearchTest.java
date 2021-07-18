package LaiDebug.BinarySearch;

import org.junit.Before;
import org.junit.Test;


import static LaiDebug.BinarySearch.ArrayBinarySearch.binarySurroundSearch;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayBinarySearchTest {

    private int[][] matrix;

    @Before
    public void setUp() {
        int[] array1 = new int[]{ 0,  1,  2,  3,  4,  5,  6,  7,  7,  9};
        int[] array2 = new int[]{ 1,  2,  3,  4,  5,  6,  7,  7,  9, 10};
        int[] array3 = new int[]{ 2,  3,  4,  5,  6,  7,  7,  9, 10, 11};
        int[] array4 = new int[]{ 3,  4,  5,  6,  7,  8,  9, 10, 11, 12};
        int[] array5 = new int[]{ 4,  5,  6,  7,  7,  9, 10, 11, 12, 13};
        int[] array6 = new int[]{ 5,  6,  7,  7,  9, 10, 11, 12, 13, 14};
        int[] array7 = new int[]{ 6,  7,  7,  9, 10, 11, 12, 13, 14, 15};
        matrix = new int[][]{array1, array2, array3, array4, array5, array6, array7};
    }

    @Test
    public void binarySurroundSearchTest() {
        assertArrayEquals(new int[]{6, 9}, binarySurroundSearch(matrix, 10));
    }
}
