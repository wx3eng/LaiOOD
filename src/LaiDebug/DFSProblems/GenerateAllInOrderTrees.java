package LaiDebug.DFSProblems;

import LaiTree.TreeFunctions;
import LaiTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class GenerateAllInOrderTrees {

    public static void main(String[] args) {

        int[] inputTest = new int[] {1, 2, 3, 4};
        List<TreeNode<Integer>> result = generateAllInOrderTrees(inputTest);

        for (TreeNode<Integer> node : result) {
            TreeFunctions.destructTreePrint(node);
        }

        System.out.println("done");
    }

    private static List<TreeNode<Integer>> generateAllInOrderTrees(int[] inOrder) {

        if (inOrder == null || inOrder.length == 0) {
            return null;
        }

        List<TreeNode<Integer>>[][] reference = (List<TreeNode<Integer>>[][]) new ArrayList[inOrder.length][inOrder.length];

        for (int i = 0; i < reference.length; i++) {
            reference[i][i] = new ArrayList<>();
            reference[i][i].add(new TreeNode<>(inOrder[i]));
        }

        for (int i = 1; i < reference.length; i++) {
            for (int j = 0; i + j < reference.length; j++) {

                reference[j][i + j] = new ArrayList<>();

                for (TreeNode<Integer> node : reference[j + 1][i + j]) {

                    TreeNode<Integer> newNode = new TreeNode<>(inOrder[j]);
                    newNode.appendRight(node);
                    reference[j][i + j].add(newNode);
                }

                for (int k = j + 1; k < i + j; k++) {

                    List<TreeNode<Integer>> leftSubTrees = reference[j][k - 1];
                    List<TreeNode<Integer>> rightSubTrees = reference[k + 1][i + j];

                    for (TreeNode<Integer> leftChild : leftSubTrees) {
                        for (TreeNode<Integer> rightChild : rightSubTrees) {
                            TreeNode<Integer> newNode = new TreeNode<>(inOrder[k]);
                            newNode.appendLeft(leftChild);
                            newNode.appendRight(rightChild);
                            reference[j][i + j].add(newNode);
                        }
                    }
                }

                for (TreeNode<Integer> node : reference[j][i + j - 1]) {

                    TreeNode<Integer> newNode = new TreeNode<>(inOrder[i + j]);
                    newNode.appendLeft(node);
                    reference[j][i + j].add(newNode);
                }
            }
        }

        return reference[0][inOrder.length - 1];
    }
}
