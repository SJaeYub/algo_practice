package baek5525;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        char[] arr = sc.next().toCharArray();
        int[] memo = new int[m];
        int ans = 0;

        for (int i = 1; i < m - 1; i++) {
            if (arr[i] == 'O' && arr[i + 1] == 'I') {
                memo[i + 1] = memo[i - 1] + 1;
                if (memo[i + 1] >= n && arr[i - 2 * n + 1] == 'I') {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}
