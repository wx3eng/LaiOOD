package LaiDebug.TreeRecursionProblems;

public class MinimumMeanEdits {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(2);
        root.right.right.right = new TreeNode(1);
        root.right.right.right.right = new TreeNode(2);
        root.right.right.right.right.right = new TreeNode(1);

        System.out.println(minMeanEdits(root));
    }

    public static int minMeanEdits(TreeNode root) {

        int[] total = new int[]{0};
        minMeanEdits(root, total);
        return total[0];
    }

    private static Pair minMeanEdits(TreeNode node, int[] total) {
        if(node == null) {
            return new Pair(0, 0);
        }
        if(node.left == null && node.right == null) {
            return new Pair(node.value, 1);
        }
        Pair left = minMeanEdits(node.left, total);
        Pair right = minMeanEdits(node.right, total);
        double newMean = (left.mean * left.count + right.mean * right.count) / (left.count + right.count);
        if(node.value != newMean) {
            total[0]++;
        }
        return new Pair(newMean, left.count + right.count + 1);
    }

    private static class TreeNode {
        private double value;
        private TreeNode left;
        private TreeNode right;
        private TreeNode(double value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private static class Pair {
        private int count;
        private double mean;
        private Pair(double mean, int count) {
            this.count = count;
            this.mean = mean;
        }
    }

}
