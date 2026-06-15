package com.practice.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
shortest path in an unweighted graph/grid
 */

public class WallsAndGates {
    /*
    Fill each room with distance to the nearest gate.
     */

    static void main() {
        int[][] arr = {{Integer.MAX_VALUE,-1,0,Integer.MAX_VALUE},{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,-1},{Integer.MAX_VALUE,-1,Integer.MAX_VALUE,-1},{0,-1,Integer.MAX_VALUE,Integer.MAX_VALUE}};
        wallsAndGates(arr);
        for(int[] a : arr){
            System.out.println(Arrays.toString(a));
        }
    }

    private static void wallsAndGates(int[][] rooms) {
        int rows = rooms.length;
        int cols = rooms[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // Step 1: Add all gates
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        // Step 2: BFS
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int[] d : dirs) {
                int r = curr[0] + d[0];
                int c = curr[1] + d[1];

                // Only update INF cells
                if (r >= 0 && c >= 0 && r < rows && c < cols
                        && rooms[r][c] == Integer.MAX_VALUE) {

                    rooms[r][c] = rooms[curr[0]][curr[1]] + 1;
                    queue.offer(new int[]{r, c});
                }
            }
        }

    }

}
