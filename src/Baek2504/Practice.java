package Baek2504;

import java.util.Scanner;
import java.util.Stack;

public class Practice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.next();
        Stack<Character> stack = new Stack<>();
        int result = 0;
        int mul = 1;

        for(int i = 0; i < in.length(); i++) {
            if(in.charAt(0) == ']' || in.charAt(0) == ')') {
                System.out.println(0);
                return;
            }

            switch (in.charAt(i)) {
                case '(':
                    stack.push('(');
                    mul *= 2;
                    break;

                case ')':
                    if(stack.isEmpty() || stack.peek() != '(') {
                        result = 0;
                        break;
                    }
                    if(in.charAt(i-1) == '(') {
                        result += mul;
                    }
                    stack.pop();
                    mul /= 2;
                    break;

                case '[':
                    stack.push('[');
                    mul *= 3;
                    break;

                case ']':
                    if(stack.isEmpty() || stack.peek() != '[') {
                        result = 0;
                        break;
                    }
                    if(in.charAt(i-1) == '[') {
                        result += mul;
                    }
                    stack.pop();
                    mul /= 3;
                    break;
            }
        }

        if(!stack.isEmpty()) {
            System.out.println(0);
            return;
        }
        else {
            System.out.println(result);
        }
    }
}
