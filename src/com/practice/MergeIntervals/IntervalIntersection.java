package com.practice.MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalIntersection {

    static void main() {
        int[][] A = {{0,2},{5,10},{13,23},{24,25}};
        int[][] B = {{1,5},{8,12},{15,24},{25,26}};
        List<int[]>result = getIntersectionIntervals(A,B);
        for(int[] arr: result){
            System.out.println(Arrays.toString(arr));
        }

    }

    private static List<int[]> getIntersectionIntervals(int[][] a, int[][] b) {

        int i = 0, j=0;
        List<int[]> result = new ArrayList<>();
        while(i < a.length && j <b.length){
            int firstStart= a[i][0];
            int firstEnd = a[i][1];
            int secondStart = b[j][0];
            int secondEnd = b[j][1];
            int maxStart = Math.max(firstStart, secondStart);
            int minEnd = Math.min(firstEnd, secondEnd);
            if(maxStart <= minEnd){
               result.add(new int[]{maxStart, minEnd});
            }

            if(firstEnd < secondEnd){
                i++;
            }else{
                j++;
            }
        }

        return  result;
    }
}
