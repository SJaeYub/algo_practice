package Baek2504;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.next();

        int mul = 1;
        int result = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(0) == ']' || in.charAt(0) == ')') {
                System.out.println(0);
                return;
            }
            switch (in.charAt(i)) {
                case '(':
                    stack.push('(');
                    mul *= 2;
                    break;

                case '[':
                    stack.push('[');
                    mul *= 3;
                    break;

                case ')':
                    if (stack.isEmpty() || stack.peek() != '(') {
                        result = 0;
                        break;
                    }

                    if (in.charAt(i - 1) == '(') {
                        result += mul;
                    }
                    stack.pop();
                    mul /= 2;
                    break;

                case ']':
                    if (stack.isEmpty() || stack.peek() != '[') {
                        result = 0;
                        break;
                    }

                    if (in.charAt(i - 1) == '[') {
                        result += mul;
                    }
                    stack.pop();
                    mul /= 3;
                    break;
            }
        }

        if (!stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }
    }
}