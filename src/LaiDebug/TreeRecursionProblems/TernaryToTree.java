package LaiDebug.TreeRecursionProblems;

import java.util.ArrayDeque;
import java.util.Deque;

public class TernaryToTree {

    public static void main(String[] args) {

        String string = new String("a?b?c?d:e:f?g:h:i?j:k");
        ExpNode root = tree(string);
        System.out.println("done");

    }

    public static ExpNode tree(String exp) {

        if(exp==null || exp.length()==0)
            return null;

        Deque<ExpNode> stack = new ArrayDeque<>();
        ExpNode root = new ExpNode(exp.charAt(0));
        ExpNode temp = root;

        for(int i=1; i<exp.length(); i++) {
            if(exp.charAt(i)=='?')
                stack.offerFirst(temp);
            else if(exp.charAt(i)==':') {
                while(stack.peekFirst().right!=null)
                    stack.pollFirst();
            }
            else if(stack.peekFirst().left==null)
                stack.peekFirst().left = (temp = new ExpNode(exp.charAt(i)));
            else
                stack.peekFirst().right = (temp = new ExpNode(exp.charAt(i)));
        }

        return root;
    }

    private static class ExpNode {
        public char symbol;
        public ExpNode left;
        public ExpNode right;
        public ExpNode(char symbol) {
            this.symbol = symbol;
        }
    }

}
