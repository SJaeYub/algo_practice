package baek5585;

import java.util.Scanner;

public class Main {

    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        System.out.println(greedy(n));

    }

    private static int greedy(int a) {
        int remain = 1000 - a;
        int[] coin = {500, 100, 50, 10, 5, 1};
        int cnt = 0;
        for (int i = 0; i < 6; i++) {
            while ((remain / coin[i] >= 1)) {
                remain -= coin[i];
                cnt++;
            }
        }
        return cnt;
    }
}
