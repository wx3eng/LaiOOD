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

        for (int i = 0; i < n; i++) {
            int value = 1 << i;
            for (int j = value - 1; j >= 0; j--) {
                solution.add(value + solution.get(j));
            }
        }

        return solution;
    }


}
