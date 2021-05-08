package LaiDebug.General;

public class TestClass {

    public static void main(String[] args) {

        int a = 3;
        int b = 1;
        b += a += 2;
        System.out.println(a);
        System.out.println(b);
    }

}
