package LaiTree;

import LaiGenericArrays.GenericArray;
import LaiQStackDeque.ArrayQueue;
import LaiQStackDeque.ArrayStack;

public interface TreeFunctions {    // Any "GenericArray" below is the same as an arrayList

    // Assume that the input array is a linearized level-order binary tree (including null) in a complete-tree format.
    //
    // For example, array [6, 4, 2, 1, null, null, -5, 0, null, null, null, null, null, 9] is converted to tree
    //
    //                                  6
    //                               4     2
    //                             1         -5             as the output.
    //                           0          9

    static <E> TreeNode<E> constructTreeBlind(E[] inputArray) {
        if(inputArray==null || inputArray.length==0) return null;   //base case

        GenericArray<TreeNode<E>> result = new GenericArray<>(inputArray.length);   // replicates an input array of elements into an array of treeNodes of elements in the same order,
                                                                                    // called result, and for all treeNodes in result, attach left and right children together
        for(int i=inputArray.length-1; i>=0; i--) {                                 // with the property of the complete tree.
            E element = inputArray[i];
            if(element==null) continue;
            result.modify(new TreeNode<>(element), i);
            if(2*i+2 < inputArray.length) result.get(i).appendRight(result.get(2*i+2));     // appending children starts from the leaf nodes to the root.
            if(2*i+1 < inputArray.length) result.get(i).appendLeft(result.get(2*i+1));
        }
        return result.get(0);                                                               // Time and space complexity: O(n), where n is the size of the array.
    }


    // Assume that the input treeNode is the root of a binary tree.
    //
    // For example, tree                 6
    //                                4     2
    //                              1         -5
    //                            0          9
    // is converted to [6, 4, 2, 1, null, null, -5, 0, null, null, null, null, null, 9] (any null after the last non-null element in the tree is ignored).

    static <E> E[] destructTreeBlind(TreeNode<E> root) {
        if(root==null) return null;     //base case

        ArrayQueue<TreeNode<E>> temp = new ArrayQueue<>();      // similar to level-order traversal, use a queue to keep treeNodes.
        GenericArray<E> result = new GenericArray<>();          // result stores an array of linearized binary tree.
        temp.offer(root);

        while(!temp.allNull()) {
            TreeNode<E> current = temp.poll();
            if(current==null) {                                 // unlike the typical level-order traversal, adding null to the queue is now possible.
                result.addLast(null);                   // the level-order traversal will keep adding every node to the queue until the queue
                temp.offer(null);                       // realizes that there is no non-null element left (a.k.a. no more useful nodes to add).
                temp.offer(null);
            }
            else {
                result.addLast(current.getValue());
                temp.offer(current.getLeft());
                temp.offer(current.getRight());
            }
        }
        return result.returnArray();                                     // Time and space complexity: O(n), where n is the total number of nodes in the input tree.
    }

    static <E> void destructTreePrint(TreeNode<E> root) {

        ArrayQueue<TreeNode<E>> temp = new ArrayQueue<>();
        ArrayQueue<ArrayQueue<String>> result = new ArrayQueue<>();
        temp.offer(root);

        while(!temp.allNull()) {
            int size = temp.size();
            ArrayQueue<String> temp2 = new ArrayQueue<>();
            for(int i=0; i<size; i++){
                TreeNode<E> current = temp.poll();
                if(current==null) {
                    temp2.offer("X");
                    temp.offer(null);
                    temp.offer(null);
                }
                else {
                    temp2.offer(current.getValue().toString());
                    temp.offer(current.getLeft());
                    temp.offer(current.getRight());
                }
            }
            result.offer(temp2);
        }

        int edgeSize = (int) Math.pow(2, result.size() - 1);
        int spacingSize = 2 * edgeSize - 1;
        int spacingOccurrence = 0;
        while(!result.isEmpty()) {
            ArrayQueue<String> current = result.poll();
            System.out.print(returnEdge(edgeSize));
            for(int i=0; i<spacingOccurrence; i++) {
                System.out.print(current.poll());
                System.out.print(returnEdge(spacingSize));
            }
            System.out.print(current.poll());
            System.out.println(returnEdge(edgeSize));
            edgeSize /= 2;
            spacingSize /= 2;
            spacingOccurrence = 2*spacingOccurrence + 1;
        }
    }

    static String returnEdge(int edgeSize) {
        StringBuilder temp = new StringBuilder();
        for(int i=0; i<edgeSize; i++) temp.append(" ");
        return temp.toString();
    }

    // same to the class solution.
    static <E> E[] preOrderTraversal(TreeNode<E> root) {

        if(root==null) return (E[]) new Object[0];
        GenericArray<E> result = new GenericArray<>();
        ArrayStack<TreeNode<E>> stack = new ArrayStack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode<E> current = stack.pop();
            result.addLast(current.getValue());
            if(current.getRight()!=null) stack.push(current.getRight());
            if(current.getLeft()!=null) stack.push(current.getLeft());
        }

        return result.returnArray();
    }

    //same to the class solution.
    static <E> E[] inOrderTraversal(TreeNode<E> root) {

        if(root==null) return (E[]) new Object[0];;
        GenericArray<E> result = new GenericArray<>();
        ArrayStack<TreeNode<E>> stack = new ArrayStack<>();
        TreeNode<E> helperNode = root;

        while(!stack.isEmpty() || helperNode!=null) {
            if(helperNode!=null) {
                stack.push(helperNode);
                helperNode = helperNode.getLeft();
            }
            else {
                helperNode = stack.pop();
                result.addLast(helperNode.getValue());
                helperNode = helperNode.getRight();
            }
        }
        return result.returnArray();
    }

    //different from the class solution.
    static <E> E[] postOrderTraversal(TreeNode<E> root) {

        if(root==null) return (E[]) new Object[0];
        GenericArray<E> result = new GenericArray<>();
        ArrayStack<TreeNode<E>> stack = new ArrayStack<>();
        TreeNode<E> helperNode = null;                                                              // Note: root is the same as current, and helperNode is the same as prev.
        stack.push(root);

        while(!stack.isEmpty()){                                                                    // For any node of interest (pin-pointed by root):
            if(root.getLeft()!=null && root.getLeft()!=helperNode && root.getRight()!=helperNode){  // if there is a left path for the node (node.left!=null) and
                helperNode = root;                                                                  // both the left path and the right path to a subtree have not been traversed,
                root = root.getLeft();                                                              // keep track of the previous node (helper) add into queue all nodes on the left path.
                stack.push(root);
            }
            else if(root.getRight()!=null && root.getRight()!=helperNode){                          // else, if there is a right path (node.right!=null) and
                helperNode = root;                                                                  // the right path of a subtree has not been traversed,
                root = root.getRight();                                                             // add into queue all nodes on the right path similarly.
                stack.push(root);
            }
            else{                                                                                   // otherwise (either that the current node has no children or
                helperNode = stack.pop();                                                           // that both paths have been traversed),
                result.addLast(helperNode.getValue());                                              // add the current node to the result and backtrack to the current node's parent.
                root = stack.peek();
            }
        }
        return result.returnArray();
    }

    //same to the class solution.
    static <E> E[] levelOrderTraversal(TreeNode<E> root) {

        GenericArray<E> result = new GenericArray<>();
        if(root==null) return result.returnArray();
        ArrayQueue<TreeNode<E>> queue = new ArrayQueue<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode<E> current = queue.poll();
            result.addLast(current.getValue());
            if(current.getLeft()!=null) queue.offer(current.getLeft());
            if(current.getRight()!=null) queue.offer(current.getRight());
        }

        return result.returnArray();
    }

    //works.
    static TreeNode<Integer> searchNode(TreeNode<Integer> node, int target) {
        while(node!=null && node.getValue()!=target) {
            node = target>node.getValue() ? node.getRight() : node.getLeft();
        }
        return node;
    }

    //works.
    static boolean TreeEqual(TreeNode<Integer> tree1, TreeNode<Integer> tree2) {
        if(tree1==null && tree2==null) return true;
        if(tree1==null || tree2==null) return false;
        if(tree1.getValue() != tree2.getValue()) return false;
        return TreeEqual(tree1.getLeft(), tree2.getLeft()) && TreeEqual(tree1.getRight(), tree2.getRight());
    }

    //arrayEqual currently has issues computing due to constraint with passing proper generic arrays as an argument.
    static boolean arrayEqual(Integer[] treeArray1, Integer[] treeArray2) {
        if(treeArray1==null && treeArray2==null) return true;
        if(treeArray1==null || treeArray2==null || treeArray1.length!=treeArray2.length) return false;
        for(int i=0; i<treeArray1.length; i++) {
            if(treeArray1[i]==null && treeArray2[i]!=null || treeArray1[i]!=null && treeArray2[i]==null || treeArray1[i]!=treeArray2[i]) return false;
        }
        return true;
    }

}
