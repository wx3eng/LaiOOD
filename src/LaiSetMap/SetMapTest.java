package LaiSetMap;

import java.util.Random;

public class SetMapTest {

    public static void main(String[] args) {

        IntegerCounterHashMap<String> hashSet = new IntegerCounterHashMap<>();
        String randomString = generateRandomString();
        for(int i=0; i<20; i++) {
            hashSet.add(generateRandomString());
        }
        while(!hashSet.isEmpty()) {
            System.out.print(hashSet.remove());
            System.out.print(" ");
        }
        System.out.println(" ");
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
