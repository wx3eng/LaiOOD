package LaiDebug.DigitOperation;

import java.util.ArrayList;
import java.util.List;

public class GreyCode {

    public static void main(String[] args) {
        System.out.println(graycode(5));
    }

    public static List<Integer> graycode(int n) {

        List<Integer> solution = new ArrayList<>();

        solution.add(0);
        if (n == 0) {
            return solution;
        }

        for (int i = 1; i <= n; i++) {
            int value = (int) Math.pow(2, i);
            for(int j = value/2 - 1; j >= 0; j--) {
                solution.add(value/2 + solution.get(j));
            }
        }

        return solution;
    }


}
