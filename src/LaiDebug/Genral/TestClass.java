package LaiDebug.Genral;

import java.util.Random;

public class TestClass {

    public static void main(String[] args) {

        int max = 10;
        int min = 2;
        int loops = 300;

        for(int i=0; i<loops; i++) {
            System.out.println(new Random().nextInt(max-min+1) + min);
        }
    }

}
