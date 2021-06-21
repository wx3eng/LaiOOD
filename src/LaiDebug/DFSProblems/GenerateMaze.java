package LaiDebug.DFSProblems;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateMaze {

    public static void main(String[] args) {


        List<int[][]> arrays = new ArrayList<>();
        for(int i=0; i<10; i++)
            arrays.add(maze(5));

        System.out.println(arrays.toString());
    }

    public static int[][] maze(int n) {

        if(n<1)
            return null;

        int[][] maze = new int[n][n];

        int numOfRows = n/2;
        int rowLength = n-1;

        int how = new Random().nextInt(4);

        if(how==0) {
            for(int i=0; i<numOfRows; i++) {
                maze[2*i+1][(new Random().nextInt(2))*(n-1)] = 1;
                for(int j=1; j<rowLength; j++)
                    maze[2*i+1][j] = 1;
            }
        }
        else if(how==1){
            for(int i=0; i<numOfRows; i++) {
                maze[(new Random().nextInt(2))*(n-1)][2*i+1] = 1;
                for(int j=1; j<rowLength; j++)
                    maze[j][2*i+1] = 1;
            }
        }
        else if(how==2){
            for(int i=0; i<numOfRows; i++) {
                for(int j=0; j<maze.length; j++)
                    if(maze.length/2==j)
                        continue;
                    else
                        maze[2*i+1][j] = 1;
            }
        }
        else if(how==3){
            for(int i=0; i<numOfRows; i++) {
                for(int j=0; j<maze.length; j++)
                    if(maze.length/2==j)
                        continue;
                    else
                        maze[j][2*i+1] = 1;
            }
        }

        return maze;
    }

}
