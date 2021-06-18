package LaiDebug.BFS;

import java.util.PriorityQueue;

public class TrappedWater2D {

    public static void main(String[] args) {

        int[][] matrix = new int[][]{{5,8,7,7},{5,2,1,5},{7,1,7,1},{8,9,6,9},{9,8,9,9}};
        System.out.println(maxTrapped(matrix));
    }

    public static int maxTrapped(int[][] matrix) {

        PriorityQueue<Pair> reference = new PriorityQueue<>(matrix.length*matrix[0].length, (p1, p2) -> {
            if(p1.value==p2.value)
                return 0;
            return p1.value<p2.value ? -1 : 1;
        });

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        for(int i=1; i<matrix[0].length-1; i++) {
            reference.offer(new Pair(matrix[0][i], 0, i));
            visited[0][i] = true;
            reference.offer(new Pair(matrix[matrix.length-1][i], matrix.length-1, i));
            visited[matrix.length-1][i] = true;
        }
        for(int i=1; i<matrix.length-1; i++) {
            reference.offer(new Pair(matrix[i][0], i, 0));
            visited[i][0] = true;
            reference.offer(new Pair(matrix[i][matrix[0].length-1], i, matrix[0].length-1));
            visited[i][matrix[0].length-1] = true;
        }

        visited[0][0] = true;
        visited[0][matrix[0].length-1] = true;
        visited[matrix.length-1][0] = true;
        visited[matrix.length-1][matrix[0].length-1] = true;

        int sum = 0;

        while(!reference.isEmpty()) {
            Pair cur = reference.poll();
            if(cur.row>0 && !visited[cur.row-1][cur.column]) {
                visited[cur.row-1][cur.column] = true;
                sum += cur.value-Math.min(matrix[cur.row-1][cur.column], cur.value);
                reference.offer(new Pair(Math.max(matrix[cur.row-1][cur.column], cur.value), cur.row-1, cur.column));
            }
            if(cur.row<matrix.length-1 && !visited[cur.row+1][cur.column]) {
                visited[cur.row+1][cur.column] = true;
                sum += cur.value-Math.min(matrix[cur.row+1][cur.column], cur.value);
                reference.offer(new Pair(Math.max(matrix[cur.row+1][cur.column], cur.value), cur.row+1, cur.column));
            }
            if(cur.column>0 && !visited[cur.row][cur.column-1]) {
                visited[cur.row][cur.column-1] = true;
                sum += cur.value-Math.min(matrix[cur.row][cur.column-1], cur.value);
                reference.offer(new Pair(Math.max(matrix[cur.row][cur.column-1], cur.value), cur.row, cur.column-1));
            }
            if(cur.column<matrix[0].length-1 && !visited[cur.row][cur.column+1]) {
                visited[cur.row][cur.column+1] = true;
                sum += cur.value-Math.min(matrix[cur.row][cur.column+1], cur.value);
                reference.offer(new Pair(Math.max(matrix[cur.row][cur.column+1], cur.value), cur.row, cur.column+1));
            }
        }

        return sum;
    }

    private static class Pair {
        int value;
        int row;
        int column;
        Pair(int value, int row, int column) {
            this.value = value;
            this.row = row;
            this.column = column;
        }
    }


}
