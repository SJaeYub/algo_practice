package swea1986;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/swea1986/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int num = sc.nextInt();
            int ans = 0;

            for (int i = 1; i <= num; i++) {
                if (i % 2 == 0) {
                    ans -= i;
                } else if (i % 2 != 0) {
                    ans += i;
                }
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }
}
