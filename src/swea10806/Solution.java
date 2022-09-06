package swea10806;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileInputStream;

public class Solution {

    static class Node implements Comparable<Node> {
        int left;
        int cnt;

        Node(int l, int c) {
            this.left = l;
            this.cnt = c;
        }

        @Override
        public int compareTo(Node n1) {
            if(this.cnt > n1.cnt) return 1;
            if(this.cnt == n1.cnt) return 0;
            return -1;
        }
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea10806/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int[] a = new int[n];

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
                min = Math.min(a[i], min);
            }

            int k = sc.nextInt();

            PriorityQueue<Node> pq = new PriorityQueue<Node>();
            pq.add(new Node(k, 0));

            int cnt = k;

            while (!pq.isEmpty()) {
                Node curr = pq.poll();

                if (curr.left == 0) {
                    cnt = curr.cnt;
                    break;
                }

                pq.add(new Node(0, curr.cnt + curr.left));

                for (int i = 0; i < n; i++) {
                    pq.add(new Node(curr.left / a[i], curr.cnt + curr.left % a[i]));
                }
            }

            System.out.println("#" + test_case + " " + cnt);
        }
    }
}