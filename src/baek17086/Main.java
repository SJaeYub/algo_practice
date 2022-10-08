package baek17086;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static class Shark implements Comparable<Shark> {
        int row;
        int col;
        int dist;

        Shark(int r, int c, int d) {
            row = r;
            col = c;
            dist = d;
        }

        @Override
        public int compareTo(Shark s1) {
            return -Integer.compare(this.dist, s1.dist);
        }
    }

    static int[][] map;
    static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dc = {0, 0, -1, 1, 1, -1, 1, -1};
    static LinkedList<Shark> list = new LinkedList<>();
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();

                if (map[i][j] == 1) {
                    list.add(new Shark(i, j, 0));
                }
            }
        }

        Shark ans = bfs(N, M);
        System.out.println(ans.dist);
    }

    static Shark bfs(int N, int M) {
        Shark result = new Shark(0, 0, 0);
        PriorityQueue<Shark> pq = new PriorityQueue<>();
        pq.add(new Shark(0, 0, 0));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                visited = new boolean[N][M];
                Queue<Shark> q = new LinkedList<>();
                Shark next = new Shark(i, j, 0);
                q.add(next);

                while (!q.isEmpty()) {
                    Shark curr = q.poll();

//                System.out.println(curr.row + " " + curr.col + " " + curr.dist);

//                    if (next.row != curr.row || next.col != curr.col) {
                    if (map[curr.row][curr.col] == 1) {
                        pq.add(new Shark(curr.row, curr.col, curr.dist));
//                        System.out.println("안전거리 : " + curr.dist);
                        break;
                    }
//                    }

                    visited[curr.row][curr.col] = true;

                    for (int l = 0; l < 8; l++) {
                        int mr = curr.row + dr[l];
                        int mc = curr.col + dc[l];

                        if (mr >= 0 && mr < N && mc >= 0 && mc < M) {
                            if (!visited[mr][mc]) {
                                visited[mr][mc] = true;
                                q.add(new Shark(mr, mc, curr.dist + 1));
                            }
                        }
                    }
                }
            }
        }

        return pq.poll();
    }
}
