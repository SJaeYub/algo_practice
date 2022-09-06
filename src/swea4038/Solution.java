package swea4038;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea4038/sample_input.txt"));

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String first = br.readLine();
//            String[] arr = subSetString(first, first.length());
            String second = br.readLine();

            int[] table = makeTable(first);

            int idx = 0;
            int ans = 0;
            for (int i = 0; i < first.length(); i++) {
                while (idx > 0 && first.charAt(i) != second.charAt(idx)) {
                    idx = table[idx - 1];
                }

                if (first.charAt(i) == second.charAt(idx)) {
                    if (idx == second.length() - 1) {
                        idx = table[idx];
                        ans++;
                    } else {
                        idx += 1;
                    }
                }
            }
            System.out.println("#" + test_case + " " + ans);
        }
    }

    static int[] makeTable(String pattern) {
        int n = pattern.length();
        int[] table = new int[n];

        int idx = 0;
        for (int i = 1; i < n; i++) {
            while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
                idx = table[idx - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(idx)) {
                idx += 1;
                table[i] = idx;
            }
        }

        return table;
    }
}