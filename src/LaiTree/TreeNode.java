package LaiTree;

public class TreeNode<T> {

    private TreeNode<T> left;
    private TreeNode<T> right;
    private T value;

    public TreeNode(T element) {
        this.value = element;
    }

    public void appendLeft(TreeNode<T> node) {
        left = node;
    }

    public void appendRight(TreeNode<T> node) {
        right = node;
    }

    public T getValue() {
        return value;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

}
