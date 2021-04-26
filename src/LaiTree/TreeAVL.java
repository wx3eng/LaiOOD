package LaiTree;

import java.util.Comparator;

public class TreeAVL<E> implements Comparator<E> {

    protected TreeNode<E> root;

    public int height() {
        return height(root);
    }

    public int search(E element) {
        if(element==null) return 0;
        TreeNode<E> temp = root;
        while(temp!=null && !equalElements(element, temp.getValue())) {
            temp = compare(element, temp.getValue())<0 ? temp.getLeft() : temp.getRight();
        }
        return temp==null ? 0 : temp.getCount();
    }

    public boolean insert(E element) {
        if(element==null) return false;
        root = insert(root, new TreeNode<>(element));
        return true;
    }

    public boolean delete(E element) {
        if(element==null) return false;
        TreeNode<E>[] result = (TreeNode<E>[]) new TreeNode[] {new TreeNode<>(element)};
        root = delete(root, result);
        return result[0]==null;
    }

    public E delete() {
        if(root==null) return null;
        E returnValue = root.getValue();
        delete(returnValue);
        return returnValue;
    }

    public boolean isEmpty() { return root==null; };

    private int height(TreeNode<E> node) { return node==null ? 0 : node.getHeight(); }

    private TreeNode<E> insert(TreeNode<E> node, TreeNode<E> element) {

        if(node==null) return element;

        if(equalElements(element.getValue(), node.getValue())) {
            node.setCount(node.getCount()+1);
            return node;
        }
        if (compare(element.getValue(), node.getValue())<0) node.appendLeft(insert(node.getLeft(), element));
        if (compare(element.getValue(), node.getValue())>0) node.appendRight(insert(node.getRight(), element));

        return balance(node);
    }

    private TreeNode<E> delete(TreeNode<E> node, TreeNode<E>[] element) {

        if(node==null) return null;
        if(element[0]==null) {
            if(node.getRight()!=null) {
                node.appendRight(delete(node.getRight(), element));
            }
            else {
                element[0] = node;
                return node.getLeft();
            }
        }
        else if(equalElements(element[0].getValue(), node.getValue())) {
            element[0] = null;
            if(node.getCount()>1) {
                node.setCount(node.getCount()-1);
                return node;
            }
            if(node.getLeft()==null && node.getRight()==null) return null;
            if(node.getLeft()==null) return node.getRight();
            if(node.getRight()==null) return node.getLeft();
            node.appendLeft(delete(node.getLeft(), element));
            element[0].appendLeft(node.getLeft());
            element[0].appendRight(node.getRight());
            node = element[0];
            element[0] = null;
        }
        else {
            if (compare(element[0].getValue(), node.getValue())<0) node.appendLeft(delete(node.getLeft(), element));
            else if (compare(element[0].getValue(), node.getValue())>0) node.appendRight(delete(node.getRight(), element));
        }

        return balance(node);
    }

    private TreeNode<E> balance(TreeNode<E> node) {

        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());

        if(leftHeight-rightHeight > 1) {
            TreeNode<E> leftNode = node.getLeft();
            if(height(leftNode.getLeft()) < height(leftNode.getRight())) {
                node.appendLeft(rotateLeft(leftNode));
            }
            return rotateRight(node);
        }
        if(rightHeight-leftHeight > 1) {
            TreeNode<E> rightNode = node.getRight();
            if(height(rightNode.getRight()) < height(rightNode.getLeft())) {
                node.appendRight(rotateRight(rightNode));
            }
            return rotateLeft(node);
        }

        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
        return node;
    }

    private TreeNode<E> rotateLeft(TreeNode<E> node) {
        TreeNode<E> temp = node.getRight();
        node.appendRight(temp.getLeft());
        temp.appendLeft(node);
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight()) + 1));
        temp.setHeight(Math.max(height(temp.getLeft()), height(temp.getRight()) + 1));
        return temp;
    }

    private TreeNode<E> rotateRight(TreeNode<E> node) {
        TreeNode<E> temp = node.getLeft();
        node.appendLeft(temp.getRight());
        temp.appendRight(node);
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight()) + 1));
        temp.setHeight(Math.max(height(temp.getLeft()), height(temp.getRight()) + 1));
        return temp;
    }

    // Lazy implementation for Integer and Character here
    @Override
    public int compare(E o1, E o2) {
        if(o1 instanceof Integer) { return (Integer) o1-(Integer) o2; }
        if(o1 instanceof Character) { return (Character) o1-(Character) o2; }
        if(o1 instanceof String) {
            String s1 = (String) o1;
            String s2 = (String) o2;
            for(int i=0; i<s1.length(); i++) {
                if(i>=s2.length() || s1.charAt(i)>s2.charAt(i)) return 1;
                if (s1.charAt(i)<s2.charAt(i)) return -1;
            }
            return s1.length()==s2.length() ? 0 : -1;
        }
        return 0;
    }

    // Equals for
    private boolean equalElements(E element1, E element2) { return (element1==null && element2==null) || (element1!=null && element1.equals(element2)); }
}
