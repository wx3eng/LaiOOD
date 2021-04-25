package LaiTree;

public class TreeNode<T> {

    private TreeNode<T> left;
    private TreeNode<T> right;
    private T value;
    private int height;
    private int count;

    public TreeNode(T element) {
        if(element==null) return;
        this.value = element;
        count = 1;
        height = 1;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCount() { return count; }

    public void setCount(int count) { this.count = count; }
}
