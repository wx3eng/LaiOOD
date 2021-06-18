package LaiDebug.ArrayLinear;

public class ConferenceRoom {

    public static void main(String[] args) {

        int[][] array = new int[][]{{18,28},{14,17},{24,34},{21,25},{3,13},{16,23},{10,13},{14,31},{8,26},{8,14},{23,25},{16,37},{11,28},{8,22}};
        System.out.println(minMeetingRooms(array));
    }

    public static int minMeetingRooms(int[][] intervals) {

        if(intervals==null || intervals.length==0)
            return 0;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int[] times : intervals) {
            min = Math.min(min, times[0]);
            max = Math.max(max, times[1]);
        }

        int[] reference = new int[max-min+1];

        for(int [] times: intervals) {
            reference[times[0]-min]++;
            reference[times[1]-min]--;
        }

        max = 0;

        for(int i=1; i<reference.length; i++) {
            reference[i] += reference[i - 1];
            max = Math.max(max, reference[i]);
        }

        return max;
    }

    private static void mergeSort(int[][] array, int left, int right, int[][] helper) {

        if(left>=right)
            return;

        int middle = left+(right-left)/2;
        mergeSort(array, left, middle, helper);
        mergeSort(array, middle+1, right, helper);
        merge(array, left, middle, middle+1, right, helper);
    }

    private static void merge(int[][] array, int lowA, int highA, int lowB, int highB, int[][] helper) {

        int left = lowA;
        int right = lowB;
        int temp = lowA;

        while(left<=highA) {
            helper[temp++] = (right>highB || array[left][0]<array[right][0] || array[left][0]==array[right][0]&&array[left][1]<array[right][1]) ? array[left++] : array[right++];
        }

        while(temp>lowA)
            array[--temp] = helper[temp];
    }

}
