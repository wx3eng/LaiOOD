package LaiDebug.ArrayQuadratic;

import java.util.Arrays;

public class CountTriangles {

    public static void main(String[] args) {
        int[] test = new int[]{2,43,94,33,80,75,63,79,90,49,83,100,18,54,6,31,81,92,67,8,100,36,28,71,65,93,11};
        System.out.println(countTriangles(test));
    }

    public static int countTriangles(int[] array) {

        if(array==null || array.length<3)
            return 0;

        Arrays.sort(array);
        int count = 0;

        int right = binarySearchEquals(array, 2, array.length-1, array[0]+array[1]);
        if(right==-1) {
            return array.length*(array.length-1)*(array.length-2)/6;
        }
        count += right*(right-1)*(right-2)/6;

        for(int k=array.length-1; k>=right; k--) {
            for(int left=0; left<k-1; left++) {
                int pivot = binarySearch(array, left+1, k-1, array[k]-array[left]);
                count += pivot==-1 ? 0 : k-pivot;
            }
        }

        return count;
    }

    private static int binarySearch(int[] array, int left, int right, int target) {
        while(left<right-1) {
            int middle = left+(right-left)/2;
            if(array[middle]<=target) {
                left = middle+1;
            } else {
                right = middle;
            }
        }
        return array[left]>target ? left : (array[right]>target ? right : -1);
    }

    private static int binarySearchEquals(int[] array, int left, int right, int target) {
        while(left<right-1) {
            int middle = left+(right-left)/2;
            if(array[middle]<target) {
                left = middle+1;
            } else {
                right = middle;
            }
        }
        return array[left]>=target ? left : (array[right]>=target ? right : -1);
    }
}
