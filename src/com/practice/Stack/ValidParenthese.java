package com.practice.Stack;

import java.util.Stack;

public class ValidParenthese {

    static void main() {
        Character[] arr = {'{', '{', '[', ']', '}', '}'};
        System.out.println(isValidParentheseOrder(arr));
    }

    private static boolean isValidParentheseOrder(Character[] arr) {

        Stack<Character> stack =  new Stack<>();
        for(char ch: arr){
            if(ch == '{' || ch == '(' || ch == '['){
                stack.push(ch);
            }else{
                char top = stack.pop();
                if(top == '(' && ch != ')')
                    return false;
                else if(top == '[' && ch != ']')
                    return false;
                else if(top == '{' && ch != '}')
                    return false;
            }

        }
         return stack.isEmpty();
    }
}
