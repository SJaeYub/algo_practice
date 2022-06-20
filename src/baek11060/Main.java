package baek11060;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static class Position {
        int idx;
        int cnt;

        public Position(int cur, int cn) {
            this.idx = cur;
            this.cnt = cn;
        }
    }

    static int n;
    static int[] arr;
    static Queue<Position> q = new LinkedList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visited = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

//        int cnt = bfs(0);
//
//        if (cnt == 0) {
//            System.out.println(-1);
//        } else {
//            System.out.println(cnt);
//        }

        q.add(new Position(0, 0));
        visited[0] = true;
        bfs();
    }

    static int bfs() {

        while (!q.isEmpty()) {
            Position curr = q.poll();

            if (curr.idx == n - 1) {
                System.out.println(curr.cnt);
                return 0;
            }

            for (int i = 1; i <= arr[curr.idx]; i++) {
                int next_idx = curr.idx + i;
                if (next_idx < n && !visited[next_idx]) {
                    q.add(new Position(next_idx, curr.cnt + 1));
                    visited[next_idx] = true;
                }
            }
        }

        System.out.println(-1);

        return 0;
    }
}
