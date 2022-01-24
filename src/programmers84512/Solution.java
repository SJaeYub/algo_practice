package programmers84512;

import java.util.ArrayList;

public class Solution {

    static ArrayList<String> arr_list = new ArrayList<>();
    static char[] input = {'A', 'E', 'I', 'O', 'U'};
    static int answer = 0, cnt = 0;

    public static void main(String[] args) {
        int aaaae = solution("AAAAE");
        System.out.println(aaaae);
    }


    static int solution(String word) {
        dfs(0, new StringBuilder(), 5, word);
        return cnt;
    }

    static void dfs(int idx, StringBuilder sb, int length, String cmd) {
        if (idx == 5) {
            return;
        }

        for (int i = 0; i < length; i++) {
            sb.append(input[i]);
            answer++;
            if (sb.toString().equals(cmd)) {
                System.out.println(cnt = answer);
            }

            dfs(idx + 1, sb, length, cmd);
            sb.delete(sb.length() - 1, sb.length());
        }
    }

    static boolean equals(String cmd, String versus) {
        boolean result = false;
        if (cmd.equals(versus)) {
            result = true;
        }
        return result;
    }

}
