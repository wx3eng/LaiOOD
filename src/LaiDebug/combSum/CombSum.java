package LaiDebug.combSum;

import java.util.*;

public class CombSum {

    public static void main(String[] args) {
        int[] array = {1,5,1,7,2,4,5,9,5,6,7,7,8,11,14};
        // Obtain all solutions in correspondence to this problem's demand.
        List<List<Integer>> solution = combinationSum2(array, 25);
        // See what these solutions look like:
        for(List<Integer> i : solution) {
            System.out.println(i.toString());
        }
        // End.
    }

    public static List<List<Integer>> combinationSum2(int[] num, int target) {
        // solving this problem requires elements in the array to be sorted so that duplicated answers are avoided.
        mergeSort(num, 0, num.length-1, new int[num.length]);
        // call algorithm for solutions.
        List<List<Integer>> solution = new ArrayList<>();
        com(solution, new ArrayList<>(), num, 0, num.length, target);
        // return these solutions.
        return solution;
    }

    private static void com(List<List<Integer>> solution, List<Integer> current, int[] reference, int depth, int levels, int remainder) {
        // remainder==0 means that the sum of numbers chosen down the recursion tree path adds to the desired target value.
        // in this case, return the list of these numbers.
        if(remainder==0) {
            solution.add(new ArrayList<>(current));
            return;
        }
        // if recursion has considered iterated through all possible numbers but still had not found
        // a sequence of desirable numbers (as should be returned above), then stop recursion.
        if(depth==levels) return;

        // HashSet is here to avoid duplicated recursion with respect to the same element that has already appeared in the current recursion call.
        Set<Integer> temp = new HashSet<>();

        // recursive call function, (1) consider reference[i] in the current sequence and
        // (2) explore all the possibilities of combination for elements after reference[i].
        for(int i=depth; i<levels; i++) {
            // if the same reference[i] has been considered already in the current sequence, don't consider it again.
            if(temp.contains(reference[i])) continue;
            // if not, record this element to avoid duplication in proceeding iterations.
            temp.add(reference[i]);
            // (1)
            current.add(reference[i]);
            // (2)
            com(solution, current, reference, i+1, levels, remainder-reference[i]);
            // remove the last element in the current sequence so that the sequence after
            // the recursive call looks exactly the same as that before the recursive call.
            current.remove(current.size()-1);
        }
    }


    // solving this problem requires the input sequence be sorted.
    // the rest of the code consists of the algorithm for sorting an array.

    private static void mergeSort(int[] array, int a, int b, int[] helper) {
        if(b-a<1) return;
        int lowLeft = a;
        int lowRight = a +(b-a)/2;
        int upLeft = lowRight+1;
        int upRight = b;
        mergeSort(array, lowLeft, lowRight, helper);
        mergeSort(array, upLeft, upRight, helper);
        merge(array, lowLeft, lowRight, upLeft, upRight, helper);
    }

    private static void merge(int[] array, int lowLeft, int lowRight, int upLeft, int upRight, int[] helper) {
        if (upRight - lowLeft < 1) return;
        int left = lowLeft;
        int right = upLeft;
        int helpidx = lowLeft;
        while (left <= lowRight && right <= upRight) {
            helper[helpidx++] = (array[left] < array[right]) ? array[left++] : array[right++];
        }
        while (left <= lowRight) {
            helper[helpidx++] = array[left++];
        }
        while (helpidx != lowLeft) {
            array[--helpidx] = helper[helpidx];
        }
    }
}
