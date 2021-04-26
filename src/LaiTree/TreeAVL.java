package LaiTree;

import java.util.Comparator;

public class TreeAVL<E> implements Comparator<E> {

    protected TreeNode<E> root;

    public int height() {
        return height(root);
    }

    public boolean insert(E element) {
        if(element==null) return false;
        root = insert(root, new TreeNode<>(element));
        return true;
    }

    public int search(E element) {
        TreeNode<E> temp = root;
        while(temp!=null && compare(element, temp.getValue())!=0) {
            temp = compare(element, temp.getValue())<0 ? temp.getLeft() : temp.getRight();
        }
        return temp==null ? 0 : temp.getCount();
    }

    private int height(TreeNode<E> node) { return node==null ? 0 : node.getHeight(); }

    private TreeNode<E> insert(TreeNode<E> node, TreeNode<E> element) {
        if(node==null) return element;
        if(compare(element.getValue(), node.getValue())==0) {
            node.setCount(node.getCount()+1);
            return node;
        }
        if (compare(element.getValue(), node.getValue())<0) node.appendLeft(insert(node.getLeft(), element));
        if (compare(element.getValue(), node.getValue())>0) node.appendRight(insert(node.getRight(), element));

        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());

        if(leftHeight-rightHeight > 1) {
            TreeNode<E> leftNode = node.getLeft();
            if(height(leftNode.getLeft()) < height(leftNode.getRight())) {
                node.appendLeft(rotateLeft(leftNode));
                node.getLeft().setHeight(leftNode.getHeight()+1);
            }
            return rotateRight(node);
        }
        if(rightHeight-leftHeight > 1) {
            TreeNode<E> rightNode = node.getRight();
            if(height(rightNode.getRight()) < height(rightNode.getLeft())) {
                node.appendRight(rotateRight(rightNode));
                node.getRight().setHeight(rightNode.getHeight()+1);
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
        node.setHeight(node.getHeight()-1);
        return temp;
    }

    private TreeNode<E> rotateRight(TreeNode<E> node) {
        TreeNode<E> temp = node.getLeft();
        node.appendLeft(temp.getRight());
        temp.appendRight(node);
        node.setHeight(node.getHeight()-1);
        return temp;
    }

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
}
