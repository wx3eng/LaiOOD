package LaiDebug.TreeRecursionProblems;

import LaiTree.TreeAVL;
import LaiTree.TreeFunctions;
import LaiTree.TreeNode;

public class MaxSumPathI {

    public static void main(String[] args) {
        TreeAVL<Integer> tree = new TreeAVL<>();
        tree.insert(4);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(0);
        tree.insert(-1);
        tree.insert(-2);
        tree.insert(8);
        tree.insert(9);
        System.out.println(maxPathSum(tree.getRoot()));
        TreeFunctions.destructTreePrint(tree.getRoot());
    }

    public static int maxPathSum(TreeNode<Integer> root) {
        int[] globalMax = new int[] {Integer.MIN_VALUE};
        maxPathSum(root, globalMax);
        return globalMax[0];
    }

    private static Integer maxPathSum(TreeNode<Integer> node, int[] globalMax) {
        if(node==null) return null;
        Integer left = maxPathSum(node.getLeft(), globalMax);
        Integer right = maxPathSum(node.getRight(), globalMax);
        if(left==null && right==null) return node.getValue();
        if(left==null) return right + node.getValue();
        if(right==null) return left + node.getValue();
        globalMax[0] = Math.max(globalMax[0], left + right + node.getValue());
        return Math.max(left, right) + node.getValue();
    }

}
