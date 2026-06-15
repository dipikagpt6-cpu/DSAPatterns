package com.practice.Stack;

import java.util.*;

/*
push(x)	O(1)
pop()	O(1)
increment(k, val)	O(1)
 */
import java.util.*;

class DesignStackWithIncrementOperation {

    static class CustomStack {

        private int[] stack;
        private int[] inc;
        private int top;

        public CustomStack(int maxSize) {
            stack = new int[maxSize];
            inc = new int[maxSize];
            top = -1;
        }

        public void push(int x) {
            if (top == stack.length - 1) {
                return;
            }
            stack[++top] = x;
        }

        public int pop() {

            if (top == -1) {
                return -1;
            }

            int result = stack[top] + inc[top];

           // increements below ones
            if (top > 0) {
                inc[top - 1] += inc[top];
            }

            inc[top] = 0;
            top--;

            return result;
        }

        public void increment(int k, int val) {

            if (top == -1) {
                return;
            }

            int idx = Math.min(k - 1, top);
            inc[idx] += val;
        }
    }

    public static void main(String[] args) {

        String[] operations = {
                "CustomStack",
                "push",
                "push",
                "pop",
                "push",
                "push",
                "push",
                "increment",
                "increment",
                "pop",
                "pop",
                "pop",
                "pop"
        };

        int[][] values = {
                {3},        // CustomStack(3)
                {1},        // push(1)
                {2},        // push(2)
                {},         // pop()
                {2},        // push(2)
                {3},        // push(3)
                {4},        // push(4)
                {5, 100},   // increment(5,100)
                {2, 100},   // increment(2,100)
                {},         // pop()
                {},         // pop()
                {},         // pop()
                {}          // pop()
        };

        CustomStack stack = null;

        List<Object> output = new ArrayList<>();

        for (int i = 0; i < operations.length; i++) {

            String op = operations[i];

            switch (op) {

                case "CustomStack":
                    stack = new CustomStack(values[i][0]);
                    output.add(null);
                    break;

                case "push":
                    stack.push(values[i][0]);
                    output.add(null);
                    break;

                case "pop":
                    output.add(stack.pop());
                    break;

                case "increment":
                    stack.increment(values[i][0], values[i][1]);
                    output.add(null);
                    break;
            }
        }

        System.out.println(output);
    }
}

