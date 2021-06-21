package LaiDebug.DigitOperation;

public class PalindromeNumber {

    public static void main(String[] args) {

        System.out.println(isPalindrome(123321));

    }

    public static boolean isPalindrome(int x) {

        if(x<0)
            return false;

        if(x<10)
            return true;

        int copy = x;
        int count = 0;

        while(copy!=0) {
            copy /= 10;
            count++;
        }

        if(x%10==0)
            count++;

        for(int i=0; i<count/2; i++)
            if(x/((int) Math.pow(10, (count-1-i)))%10!=(x/(int) Math.pow(10, i))%10)
                return false;

        return true;
    }

}
