package swea3000;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileInputStream;

public class Solution {

    static PriorityQueue<Integer> max_pq = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> min_pq = new PriorityQueue<>();
    static long ans;

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("src/swea3000/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int first_val = sc.nextInt();
            ans = 0;

            max_pq = new PriorityQueue<>(Collections.reverseOrder());
            min_pq = new PriorityQueue<>();

            max_pq.add(first_val);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 2; j++) {
                    int value = sc.nextInt();

                    if (value > max_pq.peek()) {
                        min_pq.add(value);
                    } else if (value < max_pq.peek()) {
                        max_pq.add(value);
                    }

                    if (max_pq.size() > min_pq.size() + 1) {
                        int mid_ingre = max_pq.poll().intValue();
                        min_pq.add(mid_ingre);
                    } else if (max_pq.size() < min_pq.size()) {
                        int mid_ingre = min_pq.poll().intValue();
                        max_pq.add(mid_ingre);
                    }
                }
                int mid_value = max_pq.peek().intValue();
//                System.out.println(mid_value + " ");
                ans += mid_value;
            }

            System.out.println("#" + test_case + " " + ans % 20171109);
        }
    }
}