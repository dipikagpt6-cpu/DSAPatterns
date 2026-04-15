package com.practice.SlowAndFastPointer;

public class FindDuplicatesInNPlusOneArrayWithOneToNNumbers {

    static void main() {
        int[] arr  ={1, 2, 3, 4,2};
        System.out.println(getDuplicateNumber(arr));
    }

    private static Integer getDuplicateNumber(int[] arr) {

        int slow = arr[0];
        int fast =  arr[0];
        do{
            slow = arr[slow];
            fast = arr[arr[fast]];
        }while(slow != fast);

        slow = arr[0];
        while(slow != fast){
            slow= arr[slow];
            fast = arr[fast];

        }
return slow;
    }
}
