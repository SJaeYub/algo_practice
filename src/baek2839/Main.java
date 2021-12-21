package baek2839;

import java.util.Scanner;

public class Main {

    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        System.out.println(greedy(n));
    }

    private static int greedy(int a) {
        int cnt = 0;
        int remain = a;

        while (true) {
            if (remain % 5 == 0) {
                remain -= 5;
                cnt++;
                if (remain == 0) {
                    break;
                }
            } else {
                remain -= 3;
                cnt++;
                if (remain == 0) {
                    break;
                }
                if(remain < 3) {
                    cnt = -1;
                    break;
                }
            }
        }

        return cnt;
    }
}
