package baek2589;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Position {
        int row;
        int col;
        int dist;

        public Position(int r, int c, int d) {
            this.row = r;
            this.col = c;
            this.dist = d;
        }
    }

    static int n, m;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited_out;
    static boolean[][] visited_in;
    static Queue<Position> q;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());       //5
        m = Integer.parseInt(st.nextToken());          //7

        map = new char[n][m];
        visited_out = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String string = br.readLine();

            for (int j = 0; j < m; j++) {
//                System.out.println(string.toCharArray()[j]);
                map[i][j] = string.toCharArray()[j];
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited_out[i][j] && map[i][j] != 'W') {
                    visited_out[i][j] = true;
                    Position res = bfs(i, j);
                    ans = Math.max(ans, res.dist);
//                    System.out.println(i + " " + j + " " + map[i][j]);
//                    System.out.println(res.row + " " + res.col + " " + res.dist);
//                    visited_out[res.row][res.col] = true;
                }
            }
        }

        System.out.println(ans);

    }

    static Position bfs(int row, int col) {
        Position result = new Position(0, 0, 0);
        q = new LinkedList<>();
        q.add(new Position(row, col, 0));
        visited_in = new boolean[n][m];
        int dist = 0;

        while (!q.isEmpty()) {
            Position curr = q.poll();
//            System.out.println(curr.row + ": " + curr.col);
            visited_in[curr.row][curr.col] = true;

            if (dist < curr.dist) {
                dist = curr.dist;
                result = curr;
            }

            for (int i = 0; i < 4; i++) {
                int next_dr = curr.row + dr[i];
                int next_dc = curr.col + dc[i];
                if (next_dc >= 0 && next_dc < m && next_dr >= 0 && next_dr < n) {
                    if (!visited_in[next_dr][next_dc] && map[next_dr][next_dc] != 'W') {
                        visited_in[next_dr][next_dc] = true;
                        q.add(new Position(next_dr, next_dc, curr.dist + 1));
                    }
                }

            }

        }

        return result;
    }

}
