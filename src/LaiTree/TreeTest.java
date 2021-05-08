package LaiTree;

import LaiHeap.HeapFunctions;

public class TreeTest {

    public static void main(String[] args) {
/*
        TreeAVL<Integer> tree = new TreeAVL<>();
        for(int i=0; i<20; i++) {
            tree.insert(3+i*13);
        }
        System.out.println(Arrays.toString(TreeFunctions.preOrderTraversal(tree.root)));
        TreeFunctions.destructTreePrint(tree.root);

        System.out.println("DebugTreeStarts>>>>>>>>>>>>>>>>>>>>>>>");
        int i=0;
        while(!tree.isEmpty()) {
            System.out.println("-----------------BEGIN OF NEXT------------------------");
            System.out.println(tree.delete());
            TreeFunctions.destructTreePrint(tree.root);
            System.out.println(Arrays.toString(TreeFunctions.preOrderTraversal(tree.root)));
            System.out.println("-----------------NEXT OF CURRENT------------------------");
            i++;
        }
        System.out.println(i);
*/

                                                     //insert order: 4, 3, 2, 1, 0, -1, -2, 8, 9;
                                                     //level-order traversal should be [1, -1, 3, -2, 0, 2, 8, 4, 9];
                                                     //in-order traversal should be [-2, -1, 0, 1, 2, 3, 4, 8, 9];
                                                     //pre-order traversal should be [1, -1, -2, 0, 3, 2, 8, 4, 9];
                                                     //post-order traversal should be [-2, 0, -1, 2, 4, 9, 8, 3, 1];

        //System.out.println(Arrays.toString(TreeFunctions.levelOrderTraversal(tree.root)));
        //System.out.println(Arrays.toString(TreeFunctions.inOrderTraversal(tree.root)));
        //System.out.println(Arrays.toString(TreeFunctions.preOrderTraversal(tree.root)));
        //System.out.println(Arrays.toString(TreeFunctions.postOrderTraversal(tree.root)));

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
        System.out.println(HeapFunctions.isMinHeap(tree.getRoot()));
        //TreeFunctions.destructTreePrint(tree.getRoot());
    }
}
