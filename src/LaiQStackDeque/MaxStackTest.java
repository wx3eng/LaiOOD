package LaiQStackDeque;

public class MaxStackTest {

    public static void main(String[] args) {

        MaxStack maxStack = new MaxStack();

        maxStack.push(1);
        maxStack.push(3);
        maxStack.push(5);
        maxStack.push(6);
        maxStack.push(2);
        maxStack.push(-1);
        maxStack.push(100);
        maxStack.push(100);
        maxStack.push(100);
        maxStack.push(0);
        maxStack.push(-39);
        maxStack.push(64);
        maxStack.push(64);
        maxStack.push(64);

        System.out.println(maxStack.peekMax());
        System.out.println(maxStack.peek());
        System.out.println(maxStack.pop());
        System.out.println(maxStack.pop());
        System.out.println(maxStack.pop());
        System.out.println(maxStack.peekMax());
        System.out.println(maxStack.popMax());
        System.out.println(maxStack.popMax());
        System.out.println(maxStack.popMax());
        System.out.println(maxStack.peekMax());
        System.out.println(maxStack.pop());
        System.out.println(maxStack.popMax());
        System.out.println(maxStack.peekMax());
        System.out.println(maxStack.peek());
    }
}
