package com.practice.queue.window;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MovingAverageForWindowSizeK {

    /*
    Queue is enough as it will be FIFO list;
     */

    static Queue<Integer> queue = new LinkedList<>();
    int size;
    int sum =0;

    MovingAverageForWindowSizeK(int size){
        this.size = size;
    }

    static void main() {
        MovingAverageForWindowSizeK movingAverage = new MovingAverageForWindowSizeK(3);

        System.out.println(movingAverage.getMovingAverageForWindowSizeK(1));   // 1.0
        System.out.println(movingAverage.getMovingAverageForWindowSizeK(10));  // 5.5
        System.out.println(movingAverage.getMovingAverageForWindowSizeK(3));   // 4.666666666666667
        System.out.println(movingAverage.getMovingAverageForWindowSizeK(5));   // 6.0
    }

    private double getMovingAverageForWindowSizeK(int val) {
        queue.offer(val);
        sum= sum + val;
        if(queue.size() > this.size){
            sum = sum-queue.poll();
        }
        return (double)sum/queue.size();
    }
}
