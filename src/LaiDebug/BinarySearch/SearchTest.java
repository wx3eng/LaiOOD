package LaiDebug.BinarySearch;

import java.util.*;

public class SearchTest {

    public static void main(String[] args) {

        int testSize = (int) Math.pow(2, 8);
        int trialSize = 100*testSize;
        int[] array = new int[testSize];

        double expectation = 0;

        for(int i=0; i<testSize; i++)
            array[i] = new Random().nextInt(trialSize) + 1;

        for(int j=0; j<trialSize; j++) {
            expectation += SearchFunctions.binarySearch(array, array[new Random().nextInt(testSize)]);
        }

        /*
        List<Integer> list = new ArrayList<>();
        for(int i : array) list.add(i);

        Collections.sort(list);

        int[] result = list.stream().mapToInt(Integer::intValue).toArray();

         */

        System.out.println(expectation/trialSize);

    }

}
