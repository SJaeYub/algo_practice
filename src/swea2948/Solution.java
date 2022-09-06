package swea2948;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea2948/sample_input.txt"));

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int fir_cnt = Integer.parseInt(st.nextToken());
            int sec_cnt = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");

            HashSet<String> hSet = new HashSet<>();

            for (int i = 0; i < fir_cnt; i++) {
                hSet.add(st.nextToken());
            }
            st = new StringTokenizer(br.readLine(), " ");
            int ans = 0;
            for (int i = 0; i < sec_cnt; i++) {
                if (hSet.contains(st.nextToken())) {
                    ans++;
                }
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }
}