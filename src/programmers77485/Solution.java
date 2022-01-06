package programmers77485;

import java.util.*;

public class Solution {

    static int[][] arr;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int rows = 6;
        int cols = 6;
        int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};

        int[] answers = Arrays.stream(solution(rows, cols, queries)).toArray();
        System.out.println(Arrays.toString(answers));
    }

    static int[] solution(int rows, int cols, int[][] queries) {


        int[] answer = new int[queries.length];
        arr = new int[rows + 1][cols + 1];

        int val = 1;
        for(int i = 1; i <= rows; i++) {
            for(int j = 1; j <= cols; j++) {
                arr[i][j] = val;
                val++;
            }
        }

        for(int i = 0; i < queries.length; i++) {
            ans = Integer.MAX_VALUE;
            rotate(queries[i][0], queries[i][1], queries[i][2], queries[i][3], queries[i][0], queries[i][1], arr[queries[i][0]][queries[i][1]], 0);

            answer[i] = ans;
        }


        return answer;
    }

    static void rotate(int start_r, int start_c, int end_r, int end_c,
                       int first_r, int first_c,
                       int temp_val, int sign) {

        int temp = arr[start_r][start_c];
        ans = Math.min(ans, temp);

        if(start_r == end_r && start_c == end_c) {
            if(sign == 0) {
                arr[start_r][start_c] = temp_val;
                rotate(end_r, end_c - 1, first_r, first_c, end_r, end_c, temp, 1);
            } else {
                arr[start_r][start_c] = temp_val;
            }

            return;
        }

        if(sign == 0) {                 //위쪽 회전
            if(start_r < end_r && start_c < end_c) {
                arr[start_r][start_c] = temp_val;
                rotate(start_r, start_c + 1, end_r, end_c, first_r, first_c, temp, sign);
            } else if(start_r < end_r && start_c == end_c) {
                arr[start_r][start_c] = temp_val;
                rotate(start_r + 1, start_c, end_r, end_c, first_r, first_c, temp, sign);
            }
        } else {                        //아래쪽 회전
            if(start_r > end_r && start_c > end_c) {
                arr[start_r][start_c] = temp_val;
                rotate(start_r, start_c - 1, end_r, end_c, first_r, first_c, temp, sign);
            } else if(start_r > end_r && start_c == end_c) {
                arr[start_r][start_c] = temp_val;
                rotate(start_r - 1, start_c, end_r, end_c, first_r, first_c, temp, sign);
            }
        }

        return;
    }
}