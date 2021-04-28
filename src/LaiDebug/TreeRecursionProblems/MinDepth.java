package LaiDebug.TreeRecursionProblems;

import LaiTree.TreeAVL;
import LaiTree.TreeFunctions;
import LaiTree.TreeNode;

public class MinDepth {

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
        System.out.println(minDepth(tree.getRoot()));
        TreeFunctions.destructTreePrint(tree.getRoot());
    }

    public static int minDepth(TreeNode<Integer> node) {
        if(node==null) return 0;
        int left = minDepth(node.getLeft());
        int right = minDepth(node.getRight());
        if(left==0 && right==0) return 1;
        if(left==0) return right + 1;
        if(right==0) return left + 1;
        return Math.min(left, right) + 1;
    }
}
