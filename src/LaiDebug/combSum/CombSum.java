package LaiDebug.combSum;

import java.util.*;

public class CombSum {

    public static void main(String[] args) {
        int[] array = {1,5,1,7,2,4,5,9,5,6,7,7,8,11,14};
        mergeSort(array, 0, array.length-1, new int[array.length]);
        List<List<Integer>> solution = combinationSum2(array, 25);
        for(List<Integer> i : solution) {
            System.out.println(i.toString());
        }
    }

    public static List<List<Integer>> combinationSum2(int[] num, int target) {
        mergeSort(num, 0, num.length-1, new int[num.length]);
        List<List<Integer>> solution = new ArrayList<>();
        com(solution, new ArrayList<>(), num, 0, num.length, target);
        return solution;
    }

    private static void com(List<List<Integer>> solution, List<Integer> current, int[] reference, int depth, int levels, int remainder) {
        if(remainder==0) {
            solution.add(new ArrayList<>(current));
            return;
        }
        if(depth==levels) return;

        Set<Integer> temp = new HashSet<>();

        for(int i=depth; i<levels; i++) {
            if(temp.contains(reference[i])) continue;
            temp.add(reference[i]);
            current.add(reference[i]);
            com(solution, current, reference, i+1, levels, remainder-reference[i]);
            current.remove(current.size()-1);
        }
    }

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
