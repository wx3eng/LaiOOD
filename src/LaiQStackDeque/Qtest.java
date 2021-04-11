package LaiQStackDeque;

public class Qtest {

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for(int i=0; i<20; i++) {
            stack.push(i);
        }
        for(int i=0; i<20; i++) {
            stack.pop();
        }
    }

}
