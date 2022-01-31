package programmers76501;

public class Solution {

    public static void main(String[] args) {
        int[] absolutes = {4, 7, 12};
        boolean[] signs = {true, false, true};
        System.out.println(solution(absolutes, signs));
    }

    static int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;

        for (int i = 0; i < absolutes.length; i++) {
            int operand = 1;
            if (signs[i]) {
                operand *= absolutes[i];
            } else {
                operand *= -1 * absolutes[i];
            }
            answer += operand;
        }

        return answer;
    }
}
