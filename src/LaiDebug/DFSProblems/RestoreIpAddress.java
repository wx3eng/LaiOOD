package LaiDebug.DFSProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RestoreIpAddress {

    public static void main(String[] args) {
        String input = "10809010";
        System.out.println(restore(input));
    }

    public static List<String> restore(String input) {

        List<String> solution = new ArrayList<>();
        if(input==null || input.length()<4)
            return solution;
        restore(solution, new StringBuilder(), input, 0, 4, 0, input.length());
        return solution;
    }

    private static void restore(List<String> solution, StringBuilder current, String reference, int count, int total, int depth, int levels) {

        if(count==total) {
            if(depth==levels) {
                solution.add(current.toString());
            }
            return;
        }
        if(depth!=0)
            current.append('.');

        for(int i=depth; i<Math.min(depth+3, levels); i++) {
            if(i==depth+2 &&
                (reference.charAt(depth)>'2' ||
                reference.charAt(depth)=='2' && reference.charAt(depth+1)>'5' ||
                reference.charAt(depth)=='2' && reference.charAt(depth+1)=='5' && reference.charAt(depth+2)>'5'))
                break;
            current.append(reference.charAt(i));
            if(i!=depth && reference.charAt(depth)=='0' && reference.charAt(i)!='0')
                break;
            restore(solution, current, reference, count+1, total, i+1, levels);
        }
        while(current.length()>depth+count)
            current.deleteCharAt(current.length()-1);

        if(depth!=0)
            current.deleteCharAt(current.length()-1);
    }

}
