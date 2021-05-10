package LaiDebug.DP.Sorting;

import java.util.ArrayDeque;
import java.util.Deque;

public class ThreeStackMergesort {

    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerFirst(6);
        stack.offerFirst(1);
        stack.offerFirst(10);
        stack.offerFirst(-1);
        stack.offerFirst(0);
        stack.offerFirst(999);
        stack.offerFirst(-4);
        stack.offerFirst(57);
        stack.offerFirst(80);
        stackMergesort(stack);
        System.out.println(stack);
    }

    public static void stackMergesort(Deque<Integer> stack) {
        Deque<Integer> s2 = new ArrayDeque<>();
        Deque<Integer> s3 = new ArrayDeque<>();
        mergeSort(new Deque[]{stack, s2, s3}, stack.size(), 0);
    }

    private static void mergeSort(Deque<Integer>[] stacks, int size, int which) {

        if (size < 2)
            return;

        int left = size / 2;
        int right = size - left;

        for (int i = 0; i < right; i++)
            stacks[(which + 1) % 3].offerFirst(stacks[which].pollFirst());

        mergeSort(stacks, left, which);
        mergeSort(stacks, right, (which + 1) % 3);
        merge(stacks, size, left, right, which);
    }

    private static void merge(Deque<Integer>[] stacks, int size, int left, int right, int which) {

        while (left > 0 && right > 0)
            if (stacks[which].peekFirst() < stacks[(which + 1) % 3].peekFirst()) {
                stacks[(which + 2) % 3].offerFirst(stacks[which].pollFirst());
                left--;
            } else {
                stacks[(which + 2) % 3].offerFirst(stacks[(which + 1) % 3].pollFirst());
                right--;
            }

        while (left > 0) {
            stacks[(which + 2) % 3].offerFirst(stacks[which].pollFirst());
            left--;
        }
        while (right > 0) {
            stacks[(which + 2) % 3].offerFirst(stacks[(which + 1) % 3].pollFirst());
            right--;
        }

        while (size > 0) {
            stacks[which].offerFirst(stacks[(which + 2) % 3].pollFirst());
            size--;
        }
    }
}