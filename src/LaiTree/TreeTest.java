package LaiTree;

import java.util.Arrays;

public class TreeTest {

    public static void main(String[] args) {

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


    /*
        //self-balancing tree (看看 AVL tree 是咋回事):

        //initial tree has no elements
        TreeAVL<Integer> tree = new TreeAVL<>();    //initialize AVL tree

        tree.insert(4);                             //After inserting 4:          root-->  4

        tree.insert(3);                             //After inserting 3:          root-->  4
                                                    //                                    /
                                                    //                                   3

        tree.insert(2);                             //After inserting 2:          root-->  4
                                                    //                                    /
                                                    //                                   3
                                                    //                                  /
                                                    //                                 2

                                                    //UNBALANCED NOW at 4: Fixed to balanced:
                                                    //                            root-->  3
                                                    //                                    / \
                                                    //                                   2   4

        tree.insert(1);                             //After inserting 1:          root-->  3
                                                    //                                    / \
                                                    //                                   2   4
                                                    //                                  /
                                                    //                                 1

        tree.insert(0);                             //After inserting 0:          root-->  3
                                                    //                                    / \
                                                    //                                   2   4
                                                    //                                  /
                                                    //                                 1
                                                    //                                /
                                                    //                               0

                                                    //UNBALANCED NOW at 2: Fixed to balanced:
                                                    //                            root-->  3
                                                    //                                    / \
                                                    //                                   1   4
                                                    //                                  / \
                                                    //                                 0   2

        tree.insert(-1);                             //After inserting -1:          root-->  3
                                                     //                                    / \
                                                     //                                   1   4
                                                     //                                  / \
                                                     //                                 0   2
                                                     //                                /
                                                     //                               -1

                                                     //UNBALANCED NOW at 4: Fixed to balanced:
                                                     //                            root-->   1
                                                     //                                    /   \
                                                     //                                   0     3
                                                     //                                  /     / \
                                                     //                                -1     2   4

        tree.insert(-2);                             //After inserting -2:         root-->   1
                                                     //                                    /   \
                                                     //                                   0     3
                                                     //                                  /     / \
                                                     //                                -1     2   4
                                                     //                                /
                                                     //                              -2

                                                     //UNBALANCED NOW at -1: Fixed to balanced:
                                                     //                            root-->   1
                                                     //                                    /   \
                                                     //                                  -1     3
                                                     //                                  / \   / \
                                                     //                                -2   0 2   4

        tree.insert(8);                              //After inserting 8:          root-->   1
                                                     //                                    /   \
                                                     //                                  -1     3
                                                     //                                  / \   / \
                                                     //                                -2   0 2   4
                                                     //                                            \
                                                     //                                             8

        tree.insert(9);                              //After inserting 9:          root-->   1
                                                     //                                    /   \
                                                     //                                  -1     3
                                                     //                                  / \   / \
                                                     //                                -2   0 2   4
                                                     //                                            \
                                                     //                                             8
                                                     //                                              \
                                                     //                                               9

                                                     //UNBALANCED NOW at 4: Fixed to balanced:
                                                     //                            root-->   1
                                                     //                                    /   \
                                                     //                                  -1     3
                                                     //                                  / \   / \
                                                     //                                -2   0 2   8
                                                     //                                          / \
                                                     //                                         4   9


                                                     //level-order traversal should be [1, -1, 3, -2, 0, 2, 8, 4, 9];
                                                     //in-order traversal should be [-2, -1, 0, 1, 2, 3, 4, 8, 9];
                                                     //pre-order traversal should be [1, -1, -2, 0, 3, 2, 8, 4, 9];
                                                     //post-order traversal should be [-2, 0, -1, 2, 4, 9, 8, 3, 1];

        System.out.println(Arrays.toString(TreeFunctions.levelOrderTraversal(tree.root)));
        System.out.println(Arrays.toString(TreeFunctions.inOrderTraversal(tree.root)));
        System.out.println(Arrays.toString(TreeFunctions.preOrderTraversal(tree.root)));
        System.out.println(Arrays.toString(TreeFunctions.postOrderTraversal(tree.root)));
        */

    }
}
