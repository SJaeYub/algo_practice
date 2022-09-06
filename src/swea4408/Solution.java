package swea4408;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileInputStream;

public class Solution {

    static class MovingData implements Comparable<MovingData>{
        int from;
        int to;

        MovingData(int f, int t) {
            this.from = f;
            this.to = t;
        }

        @Override
        public int compareTo(MovingData m1) {
            return Integer.compare(this.to, m1.to);
        }
    }

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("src/swea4408/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int from = 0;
            int to = 0;

            int[] arr = new int[401];
            int time = 0;

            for (int i = 0; i < n; i++) {
                from = (sc.nextInt()-1)/2;
                to = (sc.nextInt()-1)/2;

                int max = Math.max(from, to);
                int min = Math.min(from, to);

                for (int j = min; j <= max; j++) {
                    arr[j] += 1;
                }
            }

            for (int i : arr) {
                time = Math.max(time, i);
            }

            System.out.println("#" + test_case + " " + time);
        }
    }
}