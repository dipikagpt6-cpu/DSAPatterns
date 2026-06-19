package com.practice.monotonicDequeue.window.aggregation;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowAllMaximum {

    //Monotonic Deque.
   // Time: O(n)
   // Space: O(k)

    static void main() {
        int[] arr = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(getSlidingWindowMaxElementsInWidowSizeOfK(arr, k)));
    }

    private static int[] getSlidingWindowMaxElementsInWidowSizeOfK(int[] arr, int k) {

        Deque<Integer> dq = new LinkedList<>();
        /*
        result size  = Window length
        First window will start at 0 and last window will start at n-k
        window length = n-k + 0 +1
         */
        int[] result = new int[arr.length - k +1];
        int idx =0;

        for(int  i = 0 ; i < arr.length; i ++){

            //case when element in dq is out of window
            while(!dq.isEmpty() && dq.peekFirst() <= i-k){
                dq.pollFirst();
            }
            //in case greater number comes
            while(!dq.isEmpty() && dq.peekLast() <= arr[i]){
                dq.pollLast();
            }
            //stores index, to check valid window
            dq.offerLast(i);

            //is there enough elements for window to complete
            if(i >= k-1){
                //greatest elemet is always in front.
                result[idx++] = arr[dq.peekFirst()];
            }
        }
        return result;
    }


}
