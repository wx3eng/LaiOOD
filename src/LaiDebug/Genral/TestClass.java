package LaiDebug.Genral;

import java.util.Random;

public class TestClass {

    public static void main(String[] args) {

        int max = 10;
        int min = 2;
        int loops = 300;

        for(int i=0; i<Math.sqrt(24); i++) {
            System.out.println(new Random().nextInt(max-min+1) + min);
        }



        System.out.println(Math.sqrt(24));
    }

}
