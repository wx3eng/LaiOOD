package LaiDebug.General;

import java.util.*;

public class TestClass {

    public static void main(String[] args) {

        Pair trial = new Pair(3, 4);
    }

    private static class Pair {

        private int value;
        private int position;

        Pair(int value, int position) {
            this.value = value;
            this.position = position;
        }

    }

}
