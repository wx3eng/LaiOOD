package LaiDebug.BinarySearch;

public class ScanPixel {

    public static void main(String[] args) {
        int[][] test = new int[][] {
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };
        System.out.println(minArea(test, 16, 1));
    }

    public static int minArea(int[][] image, int x, int y) {

        if(image==null || image.length==0 || image[0].length==0 || image[x][y]==0) {
            return 0;
        }
        return (binaryScanRight(image, y)-binaryScanLeft(image, y)+1)*(binaryScanDown(image, x)-binaryScanUp(image, x)+1);
    }

    private static int binaryScanLeft(int[][] image, int y) {
        int left = 0;
        int right = y;
        while(left<right-1) {
            int middle = left+(right-left)/2;
            if(hasOneInColumn(image, middle)) {
                right = middle;
            } else {
                left = middle+1;
            }
        }
        return hasOneInColumn(image, left) ? left : right;
    }

    private static int binaryScanRight(int[][] image, int y) {
        int left = y;
        int right = image[y].length-1;
        while(left<right-1) {
            int middle = left+(right-left)/2;
            if(hasOneInColumn(image, middle)) {
                left = middle;
            } else {
                right = middle-1;
            }
        }
        return hasOneInColumn(image, right) ? right : left;
    }

    private static int binaryScanUp(int[][] image, int x) {
        int up = 0;
        int down = x;
        while(up<down-1) {
            int middle = up+(down-up)/2;
            if(hasOneInRow(image, middle)) {
                down = middle;
            } else {
                up = middle+1;
            }
        }
        return hasOneInRow(image, up) ? up : down;
    }

    private static int binaryScanDown(int[][] image, int x) {
        int up = x;
        int down = image.length-1;
        while(up<down-1) {
            int middle = up+(down-up)/2;
            if(hasOneInRow(image, middle)) {
                up = middle;
            } else {
                down = middle-1;
            }
        }
        return hasOneInRow(image, down) ? down : up;
    }

    private static boolean hasOneInRow(int[][] image, int x) {
        for(int i=0; i<image[x].length; i++) {
            if(image[x][i]==1) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasOneInColumn(int[][] image, int y) {
        for(int i=0; i<image.length; i++) {
            if(image[i][y]==1) {
                return true;
            }
        }
        return false;
    }
}
