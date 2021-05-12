package LaiDebug.DP.Palindrome;

public class LargestPalindromeSubstring {

    public static void main(String[] args) {

        System.out.println(longestPalindrome("dad"));

    }

    public static String longestPalindrome(String input) {

        if(input==null || input.length()<2)
            return input;

        int[][] reference = new int[input.length()+1][input.length()+1];

        for(int i=0; i<reference.length; i++)
            reference[i][i] = 1;
        for(int i=0; i+1<reference.length; i++)
            reference[i][i+1] = 1;

        int globalMax = 1;
        int left = 0;
        int right = 0;

        for(int j=2; j<reference.length; j++)
            for(int i=0; i+j<reference.length; i++) {
                reference[i][i+j] = reference[i+1][i+j-1]!=0 && input.charAt(i)==input.charAt(i+j-1) ? j : 0;
                if(reference[i][i+j]>globalMax) {
                    globalMax = reference[i][i+j];
                    left = i;
                    right = i+j-1;
                }
            }

        return input.substring(left, right+1);
    }

}
