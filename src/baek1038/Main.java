package baek1038;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static ArrayList<Long> arr_list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        arr_list = new ArrayList<>();
        if (N <= 10) System.out.println(N);
        else if (N > 1022) System.out.println("-1");
        else {
            for (int i = 0; i <= 9; i++) {
                makeSmallerNum(i, 1);
            }
            Collections.sort(arr_list);
            System.out.println(arr_list.get(N));
        }
    }

    static void makeSmallerNum(long num, int idx) {

        if (idx > 10) {
            return;
        }

        arr_list.add(num);

        for (int i = 0; i < num % 10; i++) {
            makeSmallerNum((num * 10) + i, idx + 1);
        }
    }
}
