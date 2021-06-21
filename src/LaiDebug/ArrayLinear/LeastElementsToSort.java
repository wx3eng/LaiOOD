package LaiDebug.ArrayLinear;

public class LeastElementsToSort {

    public static void main(String[] args) {

    }

    public static int shortestLengthToSortArray(int[] nums) {

        int max = 0;
        int min = nums.length-1;

        int errorMax = -1;
        int errorMin = nums.length;

        for(int i=1; i<nums.length; i++) {
            if(nums[i]>nums[max])
                max = i;
            else if((nums[i]<nums[max]))
                errorMax = i;
            if(nums[nums.length-1-i]<nums[min])
                min = nums.length-1-i;
            else if((nums[nums.length-1-i]>nums[min]))
                errorMin = nums.length-1-i;
        }

        return errorMax==-1 ? 0 : errorMax-errorMin+1;
    }

}
