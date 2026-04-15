package com.practice.SlowAndFastPointer;

public class IsNumberAHappyNumber {

    static void main() {
        System.out.println(isHappyNumber(2));
    }

    private static boolean isHappyNumber(int n) {

        int slow = n;
        int fast = n;
        do{
            slow = findSquareOfnumber(slow);
            fast = findSquareOfnumber(findSquareOfnumber(fast));
        }while(slow != fast);
        return (slow == 1);

    }

    private static int findSquareOfnumber(int n) {
        int sum = 0;
        while(n>0){
            int digit = n%10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}
