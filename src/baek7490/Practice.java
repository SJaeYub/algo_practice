package baek7490;

import java.util.Scanner;

public class Practice {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int test_case = 0; test_case < t; test_case++) {
            int n = sc.nextInt();
            dfs(n, 1, 1, 1, 0, "1");
            System.out.println();
        }
    }

    private static void dfs(int number, int now, int num, int sign, int sum, String str) {
        if (now == number) {
            sum = sum + (num * sign);
            if (sum == 0) {
                System.out.println(str);
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                dfs(number, now + 1, now + 1, 1, sum + (num * sign), str + "+" + String.valueOf(now + 1));
            } else if (i == 2) {
                dfs(number, now + 1, now + 1, -1, sum + (num * sign), str + "-" + String.valueOf(now + 1));
            } else if (i == 0) {
                dfs(number, now + 1, num * 10 + (now + 1), sign, sum, str + " " + String.valueOf(now + 1));
            }
        }
    }


}
