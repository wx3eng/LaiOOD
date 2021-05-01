package LaiSetMap;

import LaiSetMap.LaiMap.HybridHashMap;

import java.util.Arrays;
import java.util.Random;

public class USetMapTest {

    public static void main(String[] args) {

        HybridHashMap<String, String> hashSet = new HybridHashMap<>();          // a HashMap
        //TreeBucketHashSet<String> hashSet = new TreeBucketHashSet<>();        // a HashSet

        int testSize = 1000000;
        int trendSize = 3;
        int trials = 40;

        double[] searchMean = new double[trendSize];
        double[] deleteMean = new double[trendSize];

        for(int n=0; n<trendSize; n++) {
            double[] searchArray = new double[trials];
            double[] deleteArray = new double[trials];

            for(int k=0; k<trials; k++){

                String randomString = generateRandomString();
                String[] randomStrings = new String[testSize];
                for(int i=0; i<testSize; i++)
                    randomStrings[i] = generateRandomString();

                long startTime = System.nanoTime();

                for(int i=0; i<testSize; i++) {
                    //hashSet.put(randomString, randomStrings[i]);
                    hashSet.put(randomStrings[i], randomStrings[i]);
                    //hashSet.add(randomString);
                    //hashSet.add(generateRandomString());
                }

                long endTime = System.nanoTime();
                long totalTime = endTime-startTime;
                searchArray[k] = Math.round(((double) totalTime/1000000))/1000.0;

                for(int i=0; i<testSize; i++) {
                    hashSet.remove(randomStrings[i]);
                }

                endTime = System.nanoTime();
                totalTime = endTime-startTime;
                deleteArray[k] = Math.round(((double) totalTime/1000000))/1000.0;
            }
            searchArray[0] = 0;
            deleteArray[0] = 0;
            for(int i=searchArray.length/2; i<searchArray.length; i++)
                searchArray[0] += searchArray[i];
            for(int i=deleteArray.length/2; i<deleteArray.length; i++)
                deleteArray[0] += deleteArray[i];

            int searchLength = searchArray.length-searchArray.length/2;
            int deleteLength = deleteArray.length-deleteArray.length/2;
            searchMean[n] = Math.round(searchArray[0]/searchLength * 1000.0)/1000.0;
            deleteMean[n] = Math.round(deleteArray[0]/deleteLength * 1000.0)/1000.0;

        }

        System.out.println(Arrays.toString(searchMean));
        System.out.println(Arrays.toString(deleteMean));


        /*
        for(int i=0; i<testSize; i++) {
            hashSet.put(i*143, i);
        }

        for(int i=0; i<testSize; i++) {
            System.out.println(hashSet.removeRandom());
        }

        System.out.println(hashSet.isEmpty());
        */
    }

    public static String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 40;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

}
