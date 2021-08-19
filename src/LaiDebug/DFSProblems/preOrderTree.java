package LaiDebug.DFSProblems;

import LaiTree.TreeNode;
import LaiTree.TreeFunctions;

public class preOrderTree {

    public static void main(String[] args) {
        Integer[] testInput = new  Integer[]{9, 3, 4, null, null, 1, null, null, 2, null, 6, null, null};
        TreeFunctions.destructTreePrint(preOrderTree(testInput));
    }

    private static TreeNode<Integer> preOrderTree(Integer[] preOrder) {
        return preOrder.length == 1 ? null : preOrderTree(new int[]{0}, preOrder);
    }

    private static TreeNode<Integer> preOrderTree(int[] index, Integer[] preOrder) {

        // construct the node itself
        TreeNode<Integer> newNode = new TreeNode<>(preOrder[index[0]]);

        // construct left subtree
        if (preOrder[++index[0]] != null) {
            newNode.appendLeft(preOrderTree(index, preOrder));
        }

        // construct right subtree
        if (preOrder[++index[0]] != null) {
            newNode.appendRight(preOrderTree(index, preOrder));
        }

        return newNode;
    }

}
