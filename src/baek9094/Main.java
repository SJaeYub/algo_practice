package baek9094;

import java.util.Scanner;

public class Main {

    static int n;
    static int m;
    static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for (int test_case = 0; test_case < t; test_case++) {
            n = sc.nextInt();
            m = sc.nextInt();

            cnt = 0;
            comp(1, 2);

            System.out.println(cnt);

        }
    }

    private static void comp(int a, int b) {

        for (int i = a; i <= n - 2; i++) {
            for (int j = i + 1; j <= n - 1; j++) {
                if ((i * i + j * j + m) % (i * j) == 0) {
                    cnt++;
                }
            }
        }
    }
}
