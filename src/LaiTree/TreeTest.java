package LaiTree;

import java.util.Arrays;

public class TreeTest {

    public static void main(String[] args) {

        Integer[] testTreeInput = new Integer[] {6, 4, 2, 1, null, null, -5, 0, null, null, null, null, null, 9};
        System.out.println(Arrays.toString(testTreeInput));

        TreeNode<Integer> testTree = TreeFunctions.constructTreeBlind(testTreeInput);   // get constructed tree from the TreeBuild function.
        System.out.println(Arrays.toString(TreeFunctions.destructTreeBlind(testTree))); // print out the constructed tree from input tree list.
        System.out.println();
        //Integer[] deconstructedTestTree = TreeFunctions.destructTreeBlind(testTree);            // get deconstructed tree from the TreeBuild function. (this function will cause error)
        //System.out.println(TreeFunctions.arrayEqual(testTreeInput, deconstructedTestTree));     // return whether the original input tree array and the reconstructed array are equal.

        System.out.println(Arrays.toString(TreeFunctions.preOrderTraversal(testTree)));     // print pre-order traversal.
        System.out.println(Arrays.toString(TreeFunctions.inOrderTraversal(testTree)));      // print in-order traversal.
        System.out.println(Arrays.toString(TreeFunctions.postOrderTraversal(testTree)));    // print post-order traversal.
        System.out.println(Arrays.toString(TreeFunctions.levelOrderTraversal(testTree)));   // print level-order traversal.
    }

}
