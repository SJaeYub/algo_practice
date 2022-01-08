package baek2670;

import java.util.Scanner;

public class Main {

    static double[] arr;
    static double max = Double.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        arr = new double[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextDouble();
        }

        max = arr[0];

        for (int i = 1; i < n; i++) {
            arr[i] = Math.max(arr[i], arr[i - 1] * arr[i]);
            max = Math.max(max, arr[i]);
        }

        System.out.printf("%.3f", max);
    }


}
