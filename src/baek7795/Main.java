package baek7795;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int n, m;
    static int[] a, b;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int test_case = sc.nextInt();
        for (int t = 0; t < test_case; t++) {
            n = sc.nextInt();
            m = sc.nextInt();

            a = new int[n];
            b = new int[m];

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            for (int i = 0; i < m; i++) {
                b[i] = sc.nextInt();
            }

            Arrays.sort(a);
            Arrays.sort(b);

            int ans = search_feed(a, b);
            System.out.println(ans);
        }

    }

    static int search_feed(int[] a, int[] b) {
        int result = 0;
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i] <= b[j]) {
                    continue outer;
                }
                result++;
            }
        }

        return result;
    }
}
