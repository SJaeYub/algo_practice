package swea1859;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int t, num, arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < t; test_case++) {
            num = Integer.parseInt(br.readLine());
            arr = new int[num];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for (int i = 0; i < num; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int max = arr[num - 1];
            long ans = 0;

            for (int i = num - 2; i >= 0; i--) {
                if (max > arr[i]) {
                    ans += max - arr[i];
                } else {
                    max = arr[i];
                }
            }

            System.out.println("#" + (test_case+1) + " " + ans);
        }
    }
}
