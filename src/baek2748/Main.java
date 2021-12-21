package baek2748;

import java.util.Scanner;

public class Main {

    static long[] arr;
    static int size;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        size = sc.nextInt();
        arr = new long[size + 1];

        for (int i = 0; i < size + 1; i++) {
            arr[i] = -1;
        }

        arr[0] = 0;
        arr[1] = 1;

        System.out.println(fivo(size));
    }

    static long fivo(int a) {
        if (arr[a] == -1) {
            arr[a] = fivo(a - 1) + fivo(a - 2);
        }

        return arr[a];
    }
}
