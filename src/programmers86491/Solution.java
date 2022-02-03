package programmers86491;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        int answer = solution(sizes);
        System.out.println(answer);
    }

    static int solution(int[][] sizes) {
        int answer = 0;
        int max1 = 0, max2 = 0;


        for(int i = 0; i < sizes.length; i++) {
            if(sizes[i][0] < sizes[i][1]) {
                int tmp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = tmp;
            }

            max1 = Math.max(max1, sizes[i][0]);
            max2 = Math.max(max2, sizes[i][1]);
        }

        answer = max1 * max2;

        return answer;
    }
}