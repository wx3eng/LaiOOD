package LaiDebug.BinarySearch;

public class FindFirstSmallest {

    public static void main(String[] args) {

        int[] array = new int[]{0,1,2,3,5,11,13,14,15,17};
        System.out.println(findFirstMissingElement(array));

    }

    public static int findFirstMissingElement(int[] array) {

        if(array==null || array.length==0)
            return 0;

        if(array[0]>0)
            return 0;

        int left = 0;
        int right = array.length-1;

        while(left<right) {
            int middle = left+(right-left+1)/2;
            if(array[middle]==middle)
                left = middle+1;
            else
                right = middle-1;
        }

        return array[array[left]==left ? left : left-1]+1;
    }

}
