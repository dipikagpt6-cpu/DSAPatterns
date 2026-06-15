package com.practice.greedy;

public class BuySellStock {

    static void main() {
        int prices[] = {7,1,5,3,6,4};
        System.out.println(getMaxProfit(prices));
    }

    private static int getMaxProfit(int[] prices) {

        int maxProfit =0;
        int minPrice= Integer.MAX_VALUE;
        for(int price: prices){
            minPrice = Math.min(minPrice, price);
            int profit = price - minPrice;
            maxProfit = Math.max(profit, maxProfit );
        }
        return maxProfit;
    }
}
