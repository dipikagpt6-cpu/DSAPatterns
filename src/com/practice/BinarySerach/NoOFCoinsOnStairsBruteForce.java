package com.practice.BinarySerach;


//0(root(n))
public class NoOFCoinsOnStairsBruteForce {

    public int arrangeCoins(int n) {

        int row = 1;

        //rows will always be lss than or equal to number of coins
        while (n >= row) {
            n = n - row;
            row++;
        }

        return row - 1;
    }
}
