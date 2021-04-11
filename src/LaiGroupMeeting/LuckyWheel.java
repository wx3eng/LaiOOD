package LaiGroupMeeting;

import java.util.Random;

public class LuckyWheel {

    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println(rand.nextInt(9));
        System.out.println(rand.nextInt(9));
        System.out.println(rand.nextInt(9));
    }

    static String[] pick(String[] array, int num) {
        Random rand = new Random();
        String[] result = new String[num];
        int counter = 0;
        while(counter < num) {
            int randNum = rand.nextInt(array.length - counter);
            result[counter] = array[randNum];
            swap(array, randNum, array.length-1-counter);
            counter++;
        }
        return result;
    }

    static boolean strIsNum(String str) {
        String regex = "^[0-9]+$";
        return str.matches(regex);
    }

    static void swap(String[] array, int i, int j) {
        String tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
