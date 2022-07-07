package baek9095;

import java.util.Scanner;

public class Main {

    static int[] dp = new int[11];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
    }
}
