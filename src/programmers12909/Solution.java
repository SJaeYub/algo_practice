package programmers12909;

import java.util.Stack;

public class Solution {
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) {
        boolean solution = solution("()()");
        System.out.println(solution);
    }

    static boolean solution(String s) {
        boolean answer = false;
        for(int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);
            if(i == 0 && tmp == ')') {
                return answer;
            }
            if(tmp == '(') {
                stack.push(tmp);
            } else {
                if(stack.size() >= 1) {
                    stack.pop();
                }
            }
        }


        if(!stack.isEmpty()) {
            answer = false;
        } else {
            answer = true;
        }

        return answer;
    }
}
