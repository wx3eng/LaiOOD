package LaiDebug.TreeRecursionProblems;

import LaiTree.TreeAVL;
import LaiTree.TreeFunctions;
import LaiTree.TreeNode;

public class MaxHeightPath {

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
        System.out.println(diameter(tree.getRoot()));
        TreeFunctions.destructTreePrint(tree.getRoot());
    }

    public static int diameter(TreeNode<Integer> root) {
        int[] globalMax = new int[] {0};
        diameter(root, globalMax);
        return globalMax[0];
    }

    private static int diameter(TreeNode<Integer> node, int[] globalMax) {
        if(node==null) return 0;
        int left = diameter(node.getLeft(), globalMax);
        int right = diameter(node.getRight(), globalMax);
        if(left==0 && right==0) return 1;
        if(left==0) return right + 1;
        if(right==0) return left + 1;
        globalMax[0] = Math.max(globalMax[0], left + right + 1);
        return Math.max(left, right) + 1;
    }

}
