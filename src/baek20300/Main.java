package baek20300;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int m;
    static long ans = 0, max = 0;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        m = Integer.parseInt(br.readLine());
        arr = new long[m];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        max = arr[m - 1];

        if (m % 2 == 0) {
            for (int i = m - 1; i >= m/2; i--) {
                ans = Math.max(ans, arr[i] + arr[m - 1 - i]);
            }
        } else {
            for (int i = m - 2; i >= m / 2; i--) {
                ans = Math.max(ans, arr[i] + arr[m - 2 - i]);
            }

            ans = Math.max(arr[m - 1], ans);
        }

        System.out.println(ans);
    }
}
