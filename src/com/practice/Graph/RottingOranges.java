package com.practice.Graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

    static void main() {

        //2 rotten, 1 fresh, 0 empty
        int[][] arr = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(getMinutesThatAllOrangesWillBeRotten(arr));

    }

    private static int getMinutesThatAllOrangesWillBeRotten(int[][] arr) {
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        int minutes = 0;
        int rows = arr.length;
        int cols = arr[0].length;

        for(int i = 0 ; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(arr[i][j] == 2){
                    queue.offer(new int[]{i,j});
                } else if(arr[i][j] == 1){
                    fresh++;
                }
            }
        }

        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while(!queue.isEmpty() && fresh > 0){
            int size = queue.size();   // ⭐
            minutes++;

            for(int k = 0; k < size; k++){
                int[] rottenOrange = queue.poll();

                for(int i = 0; i < dirs.length; i++){
                    int r = rottenOrange[0] + dirs[i][0];
                    int c = rottenOrange[1] + dirs[i][1];

                    if(r >= 0 && c >= 0 && r < rows && c < cols && arr[r][c] == 1){
                        arr[r][c] = 2;
                        queue.offer(new int[]{r,c});
                        fresh--;
                    }
                }
            }
        }

        return fresh == 0 ? minutes : -1;
    }

}
