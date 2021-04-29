package LaiDebug.DFSProblems;

import java.util.ArrayList;
import java.util.List;

public class MultiplicationFactors {

    public static void main(String[] args) {
        List<List<Integer>> solution = combinations(160);
        for(List<Integer> i : solution) System.out.println(i.toString());
    }

    public static List<List<Integer>> combinations(int target) {
        List<List<Integer>> solution = new ArrayList<>();
        comb(solution, new ArrayList<>(), 2, target);
        return solution;
    }

    private static void comb(List<List<Integer>> solution, List<Integer> reference, int factor, int remainder) {

        if(reference.size()!=0) {
            reference.add(remainder);
            solution.add(new ArrayList<>(reference));
            reference.remove(reference.size()-1);
        }

        for(int i=factor; i<=(int)Math.sqrt(remainder); i++) {
            if(remainder%i != 0) continue;
            if(remainder/i < 2) break;
            reference.add(i);
            comb(solution, reference, i, remainder/i);
            reference.remove(reference.size()-1);
        }

    }

}
