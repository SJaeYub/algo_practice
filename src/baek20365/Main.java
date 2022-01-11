package baek20365;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static char[] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = new char[n];

        String temp = br.readLine();
        int cnt_b = 0;
        int cnt_r = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = temp.charAt(i);
            if (i == 0) {
                if (arr[i] == 'B') {
                    cnt_b++;
                } else {
                    cnt_r++;
                }
            }
            if (i > 0) {
                if (arr[i] != arr[i - 1]) {
                    if (arr[i] == 'B') {
                        cnt_b++;
                    } else {
                        cnt_r++;
                    }
                }
            }
        }

        if (cnt_b >= cnt_r) {
            System.out.println(cnt_r + 1);
        } else {
            System.out.println(cnt_b + 1);
        }

        return;
    }
}
