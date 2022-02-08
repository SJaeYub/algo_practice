package baek11780;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    static int[][] d, p;
    static int n, m;
    static int INF = 987654321;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        d = new int[n + 1][n + 1];
        p = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    d[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            if(d[from][to] != INF) {
                d[from][to] = Math.min(cost, d[from][to]);
            } else {
                d[from][to] = cost;
            }
            p[from][to] = from;
        }

        int[][] results = floyd(d);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(results[i][j] == INF) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(results[i][j] + " ");
                }
            }
            System.out.println();
        }

        for (int i = 1; i <= n; i++) {
            path(i, n);
        }
    }

    static int[][] floyd(int[][] d) {
        int[][] result = d;

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (result[i][j] > result[i][k] + result[k][j]) {
                        result[i][j] = result[i][k] + result[k][j];
                        p[i][j] = p[k][j];
                    }
                }
            }
        }

        return result;
    }

    static void path(int start, int n) {
        for (int end = 1; end <= n; end++) {
            if (start == end)
                System.out.println(0);
            else {
                Stack<Integer> st = new Stack<>();
                int idx = p[start][end];
                while (idx != 0) {
                    st.push(idx);
                    idx = p[start][idx];
                }

                if(st.empty()) {
                    System.out.println(0);
                }
                else { // 경로 출력
                    System.out.print(st.size() + 1 + " ");
                    while (!st.empty()) {
                        System.out.print(st.peek() + " ");
                        st.pop();
                    }
                    System.out.println(end);
                }
            }
        }
    }


}
