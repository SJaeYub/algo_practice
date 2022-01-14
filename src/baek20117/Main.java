package baek20117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] hbw;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        hbw = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            hbw[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(hbw);

        boolean flag = false;
        if (n % 2 != 0) {
            flag = true;                //홀수인 경우
        }

        int ans = 0;
        for (int i = 0; i < n / 2; i++) {
            ans += hbw[n - 1 - i] * 2;
        }

        if (flag) {
            ans += hbw[n / 2];
        }

        System.out.println(ans);


    }
}
