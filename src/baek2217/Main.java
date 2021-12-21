package baek2217;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();           //밧줄 개수
        int[] w = new int[n];           //밧줄당 무게 제한

        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }

        Arrays.sort(w);

        int max_ans = Integer.MIN_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            max_ans = Math.max(w[i] * Math.abs(n - i), max_ans);
        }

        System.out.println(max_ans);
    }
}
