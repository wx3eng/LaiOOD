package LaiDebug.DFSProblems;

import LaiTree.TreeFunctions;
import LaiTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class GenerateAllPostOrderTrees {

    public static void main(String[] args) {

        int[] inputTest = new int[] {1, 2, 3, 4};
        List<TreeNode<Integer>> result = generateAllPostOrderTrees(inputTest);

        for (TreeNode<Integer> node : result) {
            TreeFunctions.destructTreePrint(node);
        }

        System.out.println("done");
    }

    private static List<TreeNode<Integer>> generateAllPostOrderTrees(int[] postOrder) {

        if (postOrder == null || postOrder.length == 0) {
            return null;
        }

        List<TreeNode<Integer>>[][] reference = (List<TreeNode<Integer>>[][]) new ArrayList[postOrder.length][postOrder.length];

        for (int i = 0; i < reference.length; i++) {
            reference[i][i] = new ArrayList<>();
            reference[i][i].add(new TreeNode<>(postOrder[i]));
        }

        for (int i = 1; i < reference.length; i++) {
            for (int j = 0; i + j < reference.length; j++) {

                reference[j][i + j] = new ArrayList<>();

                for (TreeNode<Integer> node : reference[j][i + j - 1]) {

                    TreeNode<Integer> newNode = new TreeNode<>(postOrder[i + j]);
                    newNode.appendRight(node);
                    reference[j][i + j].add(newNode);
                }

                for (int k = j; k < i + j - 1; k++) {

                    List<TreeNode<Integer>> leftSubTrees = reference[j][k];
                    List<TreeNode<Integer>> rightSubTrees = reference[k + 1][i + j - 1];

                    for (TreeNode<Integer> leftChild : leftSubTrees) {
                        for (TreeNode<Integer> rightChild : rightSubTrees) {
                            TreeNode<Integer> newNode = new TreeNode<>(postOrder[i + j]);
                            newNode.appendLeft(leftChild);
                            newNode.appendRight(rightChild);
                            reference[j][i + j].add(newNode);
                        }
                    }
                }

                for (TreeNode<Integer> node : reference[j][i + j - 1]) {

                    TreeNode<Integer> newNode = new TreeNode<>(postOrder[i + j]);
                    newNode.appendLeft(node);
                    reference[j][i + j].add(newNode);
                }
            }
        }

        return reference[0][postOrder.length - 1];
    }
}
