package swea1228;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {

    static LinkedList<String> lnk_list;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea1228/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            lnk_list = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                String next = sc.next();
                lnk_list.add(next);
            }

            n = sc.nextInt();

            while (n != 0) {
                String cmd = sc.next();
                int idx = sc.nextInt();
                int cnt = sc.nextInt();

                for (int i = 0; i < cnt; i++) {
                    lnk_list.add(idx + i, sc.next());
                }

                n--;
            }

            System.out.print("#" + test_case + " ");
            for (int i = 0; i < 10; i++) {
                System.out.print(lnk_list.get(i) + " ");
            }
            System.out.println();

        }
    }
}