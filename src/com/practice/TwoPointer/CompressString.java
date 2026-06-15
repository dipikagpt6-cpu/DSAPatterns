package com.practice.TwoPointer;

public class CompressString {

    static void main() {
        char[] arr = {'a', 'a','a', 'b', 'b', 'c', 'c'};
        //a3b2c2
        System.out.println(getLengthOfCompressedString(arr));
    }

    private static int getLengthOfCompressedString(char[] arr) {

        int i =0;
        int write = 0;
        while( i < arr.length){
            char current  = arr[i];
            int count =0;
            while(i < arr.length && arr[i] == current){
                i++;
                count++;
            }
            arr[write++] = current;
            if(count > 1){
                String freq = String.valueOf(count);
                for(char ch : freq.toCharArray()){
                    arr[write++] = ch;
                }
            }
        }
return write;

    }

}
