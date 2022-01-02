package baek20053;

import java.util.Scanner;

public class Main {

    static int min;
    static int max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for (int test_case = 0; test_case < t; test_case++) {
            int n = sc.nextInt();
            int[] arr = new int[n];

            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
                min = Math.min(min, arr[i]);
                max = Math.max(max, arr[i]);
            }

            System.out.println(min + " " + max);
        }
        return;
    }
}
