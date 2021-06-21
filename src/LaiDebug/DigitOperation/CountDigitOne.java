package LaiDebug.DigitOperation;

public class CountDigitOne {

    public static void main(String[] args) {

        System.out.println(countDigitOne(999));

    }

    public static int countDigitOne(int n) {

        if(n<=0)
            return 0;

        int[] reference = new int[31];
        reference[0] = 1;

        for(int i=1; i<reference.length; i++)
            reference[i] = ((int) Math.pow(10, i)) + 10*reference[i-1];

        int copy = n;
        int count = n%10==0 ? 1 : 0;

        while(copy!=0) {
            copy /= 10;
            count++;
        }

        int sum = 0;

        int divider = 10;
        for(int i=1; i<count; i++) {
            int current = (n/divider)%10;
            if(current>1)
                sum += (int) Math.pow(10, i);
            else if(current==1)
                sum += n%((int) Math.pow(10, i))+1;
            sum += current*reference[i-1];
            divider *= 10;
        }

        return sum + (n%10>=1 ? 1 : 0);
    }

}
