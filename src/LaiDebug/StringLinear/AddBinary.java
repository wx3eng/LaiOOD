package LaiDebug.StringLinear;

public class AddBinary {

    public static void main(String[] args) {

        System.out.println(addBinary("1010101010","0010101010"));

    }

    public static String addBinary(String a, String b) {

        int one = 0;
        int two = 0;
        for(int i=0; i<a.length(); i++)
            one |= Character.getNumericValue(a.charAt(i)) << (a.length()-1-i);
        for(int i=0; i<b.length(); i++)
            two |= Character.getNumericValue(b.charAt(i)) << (b.length()-1-i);

        int carry = 0;
        StringBuilder solution = new StringBuilder();

        for(int i=0; i<=Math.max(a.length(), b.length()); i++) {
            int left = (one>>>i)&1;
            int right = (two>>>i)&1;
            solution.append(left^right^carry);
            carry = (left&right) | (left&carry) | (right&carry);
        }

        if(solution.charAt(solution.length()-1)=='0')
            solution.deleteCharAt(solution.length()-1);
        return solution.reverse().toString();
    }

}
