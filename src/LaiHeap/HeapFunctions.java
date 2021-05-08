package LaiHeap;

import LaiTree.TreeNode;

public class HeapFunctions {

    public static boolean isMinHeap(TreeNode<Integer> root) {
        return isMinheap(root)!=-1;
    }

    private static int isMinheap(TreeNode<Integer> node) {
        if(node.getLeft()==null && node.getRight()==null) return 1;
        if(node.getLeft()==null) return -1;
        int left = isMinheap(node.getLeft());
        if(node.getRight()==null) return node.getValue()<node.getLeft().getValue() && left!=-1 ? 0 : -1;
        int right = isMinheap(node.getRight());
        return (left<1 || right==-1 || node.getValue()>=node.getLeft().getValue() || node.getValue()>=node.getRight().getValue()) ? -1 : right;
    }

    public static int[] heapSort(int[] array) {

        if(array==null || array.length<2)
            return array;

        heapify(array);

        for(int i=array.length-1; i>=0; i--) {
            swap(array, i, 0);
            int j = 0;
            while(j<i/2) {
                int leftChild = 2*j+1;
                int rightChild = 2*j+2;
                int maxChild = rightChild<i && array[rightChild]>=array[leftChild] ? rightChild : leftChild;
                if(array[maxChild]>array[j])
                    swap(array, maxChild, j);
                j = maxChild;
            }
        }

        return array;
    }

    public static void heapify(int[] array) {

        for(int i=array.length/2-1; i>=0; i--) {
            int j = i;
            while(j<array.length/2) {
                int leftChild = 2*j+1;
                int rightChild = 2*j+2;
                int maxChild = rightChild<array.length && array[rightChild]>=array[leftChild] ? rightChild : leftChild;
                if(array[maxChild]>array[j])
                    swap(array, maxChild, j);
                j = maxChild;
            }
        }
    }

    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}
