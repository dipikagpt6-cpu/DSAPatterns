package com.practice.Graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class NoOfIsland {
    public static void main(String[] args) {

        char[][] grid = {
                {'1','1','0','1'},
                {'1','1','0','0'},
                {'0','1','0','0'},
                {'1','0','1','1'}
        };

        System.out.println(numIslands(grid)); // Output: 3
    }

    public static int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0) return 0;

        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == '1') {
                    count++;
                    bfs(grid, i, j);
                }
            }
        }

        return count;
    }

    private static void bfs(char[][] grid, int i, int j) {

        int[][] directions = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        grid[i][j] = '0';
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : directions) {
                int row = curr[0] + dir[0];
                int col = curr[1] + dir[1];
                if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] == '1') {
                    queue.add(new int[]{row,col});
                    grid[row][col] = '0';
                }

            }
        }
    }
}
