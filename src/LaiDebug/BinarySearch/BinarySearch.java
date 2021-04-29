package LaiDebug.BinarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BinarySearch {

    public static void main(String[] args) {

        int testSize = (int) Math.pow(2, 3);
        int trialSize = 10*testSize;
        int[] array = new int[testSize];

        double expectation = 0;

        for(int i=0; i<testSize; i++)
            array[i] = new Random().nextInt(trialSize) + 1;

        for(int j=0; j<trialSize; j++) {
            expectation += binarySearch(array, array[new Random().nextInt(testSize)]);
        }

        List<Integer> list = new ArrayList<>();
        for(int i : array) list.add(i);

        Collections.sort(list);

        int[] result = list.stream().mapToInt(Integer::intValue).toArray();


        System.out.println(expectation/trialSize);

    }

    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length-1;
        int i = 0;
        while(Math.abs(left-right)>0) {
            i++;
            int middle = left + (right-left)/2;
            if(target<array[middle]) right = middle-1;
            else if(target>array[middle]) left = middle+1;
            else {
                System.out.println(i);
                return i;
            }
        }
        System.out.println(i);
        return i;
    }

}
