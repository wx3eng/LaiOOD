package LaiDebug.BinarySearch;

public interface SearchFunctions {

    static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length-1;
        int i = 0;
        while(left<=right) {
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
