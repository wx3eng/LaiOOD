package LaiDebug.DP.BSTCombinations;

public class TotalBSTCombination {

    public static void main(String[] args) {
        for(int i=0; i<10; i++)
            System.out.println(generateCombinations(i));
    }

    public static int generateCombinations(int n) {

        if(n<2)
            return 1;

        int[] reference = new int[n+1];
        reference[0] = 1;
        reference[1] = 1;

        for(int i=2; i<=n; i++)
            for(int j=0; j<i; j++)
                reference[i] += reference[j]*reference[i-1-j];

        return reference[n];
    }

}
