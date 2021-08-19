package LaiDebug.DFSProblems;

import java.util.ArrayList;
import java.util.List;
import LaiTree.TreeNode;
import LaiTree.TreeFunctions;

public class GenerateAllPreOrderTrees {

    public static void main(String[] args) {

        int[] inputTest = new int[] {1, 2, 3, 4};
        List<TreeNode<Integer>> result = generateAllPreOrderTrees(inputTest);

        for (TreeNode<Integer> node : result) {
            TreeFunctions.destructTreePrint(node);
        }

        System.out.println("done");
    }

    private static List<TreeNode<Integer>> generateAllPreOrderTrees(int[] preOrder) {

        if (preOrder == null || preOrder.length == 0) {
            return null;
        }

        List<TreeNode<Integer>>[][] reference = (List<TreeNode<Integer>>[][]) new ArrayList[preOrder.length][preOrder.length];

        for (int i = 0; i < reference.length; i++) {
            reference[i][i] = new ArrayList<>();
            reference[i][i].add(new TreeNode<>(preOrder[i]));
        }

        for (int i = 1; i < reference.length; i++) {
            for (int j = 0; i + j < reference.length; j++) {

                reference[j][i + j] = new ArrayList<>();

                for (TreeNode<Integer> node : reference[j + 1][i + j]) {

                    TreeNode<Integer> newNode = new TreeNode<>(preOrder[j]);
                    newNode.appendRight(node);
                    reference[j][i + j].add(newNode);
                }

                for (int k = j + 1; k < i + j; k++) {

                    List<TreeNode<Integer>> leftSubTrees = reference[j + 1][k];
                    List<TreeNode<Integer>> rightSubTrees = reference[k + 1][i + j];

                    for (TreeNode<Integer> leftChild : leftSubTrees) {
                        for (TreeNode<Integer> rightChild : rightSubTrees) {
                            TreeNode<Integer> newNode = new TreeNode<>(preOrder[j]);
                            newNode.appendLeft(leftChild);
                            newNode.appendRight(rightChild);
                            reference[j][i + j].add(newNode);
                        }
                    }
                }

                for (TreeNode<Integer> node : reference[j + 1][i + j]) {

                    TreeNode<Integer> newNode = new TreeNode<>(preOrder[j]);
                    newNode.appendLeft(node);
                    reference[j][i + j].add(newNode);
                }
            }
        }

        return reference[0][preOrder.length - 1];
    }
}
