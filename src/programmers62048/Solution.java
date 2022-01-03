package programmers62048;

public class Solution {

    public static void main(String[] args) {
        int w = 8, h = 12;
        System.out.println(solution(w, h));
    }

    private static long solution(long w, long h) {
        long answer = 0;

        for (int i = 0; i < w; i++) {
            answer += h * i / w;
        }
        return answer * 2;
    }
}