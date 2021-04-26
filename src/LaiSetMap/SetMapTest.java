package LaiSetMap;

import java.util.Random;

public class SetMapTest {

    public static void main(String[] args) {

        long startTime = System.nanoTime();
        IntegerCounterHashMap<String> hashSet = new IntegerCounterHashMap<>();  // a HashMap that must map to Integer values
        // TreeBucketHashSet<String> hashSet = new TreeBucketHashSet<>();        // a HashSet that completely inherits the Integer HashMap
        String randomString = generateRandomString();
        for(int i=0; i<200000; i++) {
            //hashSet.add(randomString);
            hashSet.add(generateRandomString());
        }

        long endTime = System.nanoTime();
        long totalTime = endTime-startTime;
        System.out.println(totalTime);

        int index = 0;
        while(!hashSet.isEmpty()) {
            hashSet.removeRandom();
            index++;
        }

        endTime = System.nanoTime();
        totalTime = endTime-startTime;
        System.out.println(totalTime);

        System.out.println(index);

    }

    public static String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
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
