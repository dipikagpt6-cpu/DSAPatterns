package com.practice.Graph;


import java.util.LinkedList;

import java.util.Queue;

public class NearestExit {

    static void main() {

        char[][] maze = {
                {'+', '+', '.', '+'},
                {'.', '.', '.', '+'},
                {'+', '+', '+', '.'}
        };

        /*
        List<List<Character>> matrix =
        List.of(
                List.of('+', '+', '.', '+'),
                List.of('.', '.', '.', '+'),
                List.of('+', '+', '+', '.')
        );
         */

        char[] entrance = {1, 2};

        System.out.println(getMinStepsToreachEntrance(maze, entrance));
    }

    private static int getMinStepsToreachEntrance(char[][] maze, char[] entrance) {

        // Defensive checks
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return -1;
        }

        int rows = maze.length;
        int cols = maze[0].length;

        if (rows == 1 && cols == 1) {
            return -1;
        }


        int[][] directions = {
                {1, 0},   // down
                {-1, 0},  // up
                {0, 1},   // right
                {0, -1}   // left
        };

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{entrance[0], entrance[1]});

        // mark entrance visited
        maze[entrance[0]][entrance[1]] = '+';

        int steps = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] current = queue.poll();

                int row = current[0];
                int col = current[1];

                // Boundary cell other than entrance = exit
                if ((row == 0 || row == rows - 1 ||
                        col == 0 || col == cols - 1)
                        &&
                        !(row == entrance[0] &&
                                col == entrance[1])) {

                    return steps;
                }

                for (int[] dir : directions) {

                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if (newRow < 0 || newRow >= rows ||
                            newCol < 0 || newCol >= cols) {
                        continue;
                    }

                    if (maze[newRow][newCol] == '+') {
                        continue;
                    }

                    // mark visited before enqueueing
                    maze[newRow][newCol] = '+';

                    queue.offer(new int[]{newRow, newCol});
                }
            }

            steps++;
        }

        return -1;
    }


    }
