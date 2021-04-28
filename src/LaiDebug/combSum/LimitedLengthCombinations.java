package LaiDebug.combSum;

import java.util.ArrayList;
import java.util.List;

public class LimitedLengthCombinations {

    public static void main(String[] args) {
        List<List<Integer>> solution = combine(6, 3);
        for(List<Integer> i : solution) System.out.println(i.toString());
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> solution = new ArrayList<>();
        combine(solution, new ArrayList<>(), 1, n, 0, k);
        return solution;
    }

    // in general, many DFS problems requires 5 parameters:
    // 1) solution space (solution): where you put all solutions
    // 2) solution builder (current): on which you add or delete elements to check for all possibilities
    // 3) input sequence/set (reference): by which you create your solutions
    // 4) current recursive call level (current depth): by which you compare to the maximum depth you have have and make decisions
    // 5) recursion depth (levels): defines the max depth of recursive calls possible for a particular problem
    // if any of these parameters have trivial relations within others, then less of these parameters are required as function input.

    // algorithm:
    // define solution range as from reference to limit: if reference is 1 and limit is n,
    //      then any solution builder would be consisted of elements from 1 to n.
    // each level of the recursion tree represents all the possibilities of adding appropriate one element to the solution builder.
    // the recursion tree depth ends at k (levels), where each solution must be of k length.
    // "depth" keeps track of the current recursive level.
    private static void combine(List<List<Integer>> solution, List<Integer> current, int reference, int limit, int depth, int levels) {

        // Note: here, depth is always equals to current.size(), so it is not necessary to use this parameter.

        // once the solution builder size is as long as defined (levels), return that solution.
        if(depth==levels) {
            solution.add(new ArrayList<>(current));
            return;
        }
        // Note: if there are duplicates elements in the input, use a hashSet here to prevent such possibility.

        // recursive call: try out all possibilities starting with any element from [reference --> limit]:
            // (1) for all elements in the selection range [reference --> limit],
            // (2) try i in [reference --> range] to be an element in the solution builder, and
            // (3) leave the possibilities of rest of the choosing ([reference+1 --> limit]) to the proceeding recursive calls.
            // (4) for the next try i+1, remove the currently tried element i so the solution builder length remains the same after each try.

        for(int i=reference; i<=limit; i++) {                                                // (1)
            current.add(i);                                                                  // (2)
            combine(solution, current, i+1, limit, depth+1, levels);         // (3)
            current.remove(current.size()-1);                                         // (4)
        }
    }
}
