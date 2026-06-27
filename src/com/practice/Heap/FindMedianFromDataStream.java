package com.practice.Heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {

    static class MedianFinder {

        // Max Heap - stores the smaller half
        private PriorityQueue<Integer> maxHeap;

        // Min Heap - stores the larger half
        private PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {

            // Step 1: Add to maxHeap
            maxHeap.offer(num);

            // Step 2: Move largest from maxHeap to minHeap
            minHeap.offer(maxHeap.poll());

            // Step 3: Balance the heaps
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {

            if (maxHeap.size() == minHeap.size()) {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }

            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {

        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(1);
        System.out.println("Median: " + medianFinder.findMedian());

        medianFinder.addNum(2);
        System.out.println("Median: " + medianFinder.findMedian());

        medianFinder.addNum(3);
        System.out.println("Median: " + medianFinder.findMedian());

        medianFinder.addNum(4);
        System.out.println("Median: " + medianFinder.findMedian());

        medianFinder.addNum(5);
        System.out.println("Median: " + medianFinder.findMedian());
    }
}