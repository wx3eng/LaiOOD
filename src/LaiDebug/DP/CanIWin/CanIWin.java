package LaiDebug.DP.CanIWin;

public class CanIWin {

    public static void main(String[] args) {

        int[] array = new int[]{66,89,20,71,73,16,5,10,88,57,17,21,76,81,10,45,18};
        System.out.println(canWin(array));
    }

    public static boolean canWin(int[] nums) {

        int[][] reference = new int[nums.length][nums.length];

        for(int i=0; i<nums.length; i++)
            reference[i][i] = nums[i];
        for(int i=1; i<nums.length; i++)
            reference[i-1][i] = Math.max(nums[i-1], nums[i]);

        for(int i=2; i<nums.length; i++)
            for(int j=0; j+i<nums.length; j++) {
                int nextLeft = (nums[j+1]-reference[j+2][i+j]>nums[i+j]-reference[j+1][i+j-1]) ? reference[j+2][i+j] : reference[j+1][i+j-1];
                if(nums[j+1]-reference[j+2][i+j]==nums[i+j]-reference[j+1][i+j-1])
                    nextLeft = Math.max(reference[j+2][i+j], reference[j+1][i+j-1]);
                int left = nums[j]+nextLeft;
                int nextRight = (nums[j]-reference[j+1][i+j-1]>nums[i+j-1]-reference[j][i+j-2]) ? reference[j+1][i+j-1] : reference[j][i+j-2];
                if(nums[j]-reference[j+1][i+j-1]==nums[i+j-1]-reference[j][i+j-2])
                    nextRight = Math.max(reference[j+1][i+j-1], reference[j][i+j-2]);
                int right = nums[i+j]+nextRight;
                reference[j][i+j] = Math.max(left, right);
            }

        int total = 0;
        for(int num : nums)
            total += num;

        return total<=2*reference[0][nums.length-1];
    }
}
