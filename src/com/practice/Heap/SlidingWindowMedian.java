package com.practice.Heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class SlidingWindowMedian {
/*
Max Heap → stores the smaller half of numbers
Min Heap → stores the larger half of numbers
find the median in O(1) and update the window in O(log k).
 */

    static void main() {
        int[] arr = {1,3,-1,-3,5,3,6,7};
        int k =3;
        double[] result =  getMedianInSlidingWindowOfLengthK(arr, k);
        System.out.println(Arrays.toString(result));
    }

    private static double[] getMedianInSlidingWindowOfLengthK(int[] arr, int k) {

        double[] result = new double[arr.length -k +1];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        PriorityQueue<Integer> minHeap =  new PriorityQueue<>();

        for(int i = 0; i < arr.length; i++) {
            if (maxHeap.isEmpty() || arr[i] <= maxHeap.peek()) {
                maxHeap.offer(arr[i]);
            } else {
                minHeap.offer(arr[i]);
            }
            balanceHeap(minHeap, maxHeap);
            //check window
            if (i >= k) {
                int outgoing = arr[i - k];
                if (outgoing <= maxHeap.peek()) {
                    maxHeap.remove(outgoing);
                } else {
                    minHeap.remove(outgoing);
                }

                balanceHeap(minHeap, maxHeap);
            }


            if (i >= k - 1) {
                if (k % 2 == 0) {
                     result[i-k+1] = (maxHeap.peek() +minHeap.peek() )/ 2.0;
                } else {
                    result[i-k+1] = maxHeap.peek();
                }
            }

        }
        return result;
    }

    private static void balanceHeap(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {

        if(maxHeap.size() > minHeap.size()+1){
            minHeap.offer(maxHeap.poll());
        }else if(minHeap.size() > maxHeap.size()){
            maxHeap.offer(minHeap.poll());
        }

    }

}
