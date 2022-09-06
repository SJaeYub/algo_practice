package swea2930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;

public class Solution {

    static PriorityQueue<Integer> pq;

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("src/swea2930/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        Scanner sc = new Scanner(System.in);
        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            pq = new PriorityQueue<>(Collections.reverseOrder());
            int cnt_order = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < cnt_order; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");

                if (st.countTokens() == 2) {
                    st.nextToken();
                    pq.add(Integer.parseInt(st.nextToken()));
                } else {
                    st.nextToken();
                    if (!pq.isEmpty()) {
                        int value = pq.poll().intValue();
                        sb.append(value + " ");
                    } else {
                        sb.append(-1 + " ");
                    }
                }
            }

            System.out.println("#" + test_case + " " + sb);
        }
    }
}