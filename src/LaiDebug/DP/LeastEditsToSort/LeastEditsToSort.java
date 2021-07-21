package LaiDebug.DP.LeastEditsToSort;

public class LeastEditsToSort {

    public static void main(String[] args) {

        int[] array = new int[]{362,588,552,120,105,388,711,704,551,299,925,181,359,813,107,12,221,538,112,854,436,749,423,548,72,352,116,975,689,967,94,962,671,755,281,88,677,345,168,160,664,885};
        System.out.println(leastMoves(array));
    }

    public static int leastMoves(int[] array) {

        if(array==null || array.length==0) {
            return 0;
        }

        int[][] reference = new int[array.length][array.length];
        int[][] min = new int[array.length][array.length];
        int[][] max = new int[array.length][array.length];

        for (int i = 0; i < array.length; i++) {
            min[i][i] = array[i];
            max[i][i] = array[i];
        }

        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j + i < array.length; j++) {
                min[j][i + j] = Math.min(min[j + 1][i + j], min[j][i + j - 1]);
                max[j][i + j] = Math.max(max[j + 1][i + j], max[j][i + j - 1]);
                int checkLeft = reference[j + 1][i + j] + (array[j] > min[j + 1][i + j] ? 1 : 0);
                int checkRight = reference[j][i + j - 1] + (array[i + j] < max[j][i + j - 1] ? 1 : 0);
                reference[j][i + j] = Math.min(checkLeft, checkRight);
            }
        }

        return reference[0][array.length-1];
    }

}
