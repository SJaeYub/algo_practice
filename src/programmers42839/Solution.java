package programmers42839;

import java.util.*;

public class Solution {
    static int answer = 0;
    static boolean[] visited;
    static ArrayList<Integer> arr_list = new ArrayList<Integer>();

    public static void main(String[] args) {
        String input = "17";
        System.out.println(solution(input));
    }

    static int solution(String numbers) {
        visited = new boolean[numbers.length()];

        dfs(new StringBuilder(), numbers);

        for (Integer curr : arr_list) {
            if (isPrime(curr)) {
                answer++;
            }
        }

        return answer;
    }

    static void dfs(StringBuilder strb, String nums) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                strb.append(nums.charAt(i));

                if (arr_list.indexOf(Integer.parseInt(strb.toString())) < 0) {
                    arr_list.add(Integer.parseInt(strb.toString()));
                }

                dfs(strb, nums);
                strb.deleteCharAt(strb.length() - 1);
                visited[i] = false;
            }
        }


    }

    static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        if (num == 2) {
            return true;
        }

        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
