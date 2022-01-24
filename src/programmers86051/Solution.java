package programmers86051;

public class Solution {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 3, 4, 6, 7, 8, 0}));
    }

    static int solution(int[] numbers) {

        int sum = 0;
        for (int curr : numbers) {
            sum += curr;
        }

        int answer = 45 - sum;
        return answer;
    }
}
