package LaiSetMap;

import java.util.Random;

public class USetMapTest {

    public static void main(String[] args) {

        TreeBucketHashMap<String, String> hashSet = new TreeBucketHashMap<>();  // a HashMap
        //TreeBucketHashSet<String> hashSet = new TreeBucketHashSet<>();        // a HashSet

        int testSize = 2000000;
        String randomString = generateRandomString();
        String[] randomStrings = new String[testSize];
        for(int i=0; i<testSize; i++) { randomStrings[i] = generateRandomString(); }

        long startTime = System.nanoTime();

        for(int i=0; i<testSize; i++) {
            //hashSet.put(randomString, randomStrings[i]);
            hashSet.put(randomStrings[i], randomStrings[i]);
            //hashSet.add(randomString);
            //hashSet.add(generateRandomString());
        }

        long endTime = System.nanoTime();
        long totalTime = endTime-startTime;
        System.out.println(((double) totalTime/1000000)/1000);

        int index = 0;
        /*
        while(!hashSet.isEmpty()) {
            hashSet.removeRandom();
            index++;
        }

         */


        for(int i=0; i<testSize; i++) {
            hashSet.remove(randomStrings[i]);
        }



        endTime = System.nanoTime();
        totalTime = endTime-startTime;
        System.out.println(((double) totalTime/1000000)/1000);

        System.out.println(index);

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
