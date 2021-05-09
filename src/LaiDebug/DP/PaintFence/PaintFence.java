package LaiDebug.DP.PaintFence;

public class PaintFence {

    public static void main(String[] args) {
        System.out.println(waysToPaintFence(3, 4));
    }

    public static int waysToPaintFence(int n, int k) {

        if(n<=0)
            return 0;
        if(n==1)
            return k;

        int[] reference = new int[n+1];

        reference[1] = k-1;
        reference[2] = k;

        for(int i=3; i<=n; i++)
            reference[i] = reference[i-2] + (reference[i-1]*=(k-1));

        return k*reference[n];
    }

}
