package LaiDebug.DP.AddParenthases;

import java.util.*;

public class AddParentheses {

    public static void main(String[] args) {

        String input = new String("2*3*2-1*2-2*3-1");
        System.out.println(waysToAdd(input).toString());
    }

    public static List<Integer> waysToAdd(String input) {

        if(input==null || input.length()==0)
            return new ArrayList<>();

        List<Integer>[][] reference = (List<Integer>[][]) new ArrayList[input.length()/2+1][input.length()/2+1];
        for(int i=0; i<reference.length; i++) {
            reference[i][i] = new ArrayList<>();
            reference[i][i].add(Character.getNumericValue(input.charAt(2*i)));
        }
        for(int i=1; i<reference.length; i++) {
            for(int j=0; j+i<reference.length; j++) {
                reference[j][i+j] = new ArrayList<>();
                for(int k=j; k<i+j; k++) {
                    operateGroup(input.charAt(2*k+1), reference[j][k], reference[k+1][i+j], reference[j][i+j]);
                }
            }
        }

        Collections.sort(reference[0][reference.length-1]);
        return reference[0][reference.length-1];
    }

    private static void operateGroup(char operator, List<Integer> numbers1, List<Integer> numbers2, List<Integer> result) {
        for (int number1 : numbers1) {
            for (int number2 : numbers2) {
                result.add(operate(operator, number1, number2));
            }
        }
    }

    private static int operate(char operator, int a, int b) {
        if(operator=='+')
            return a+b;
        if(operator=='-')
            return a-b;
        if(operator=='*')
            return a*b;
        return 0;
    }
}
