package baek9205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Position {
        int row;
        int col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static ArrayList<Position> arr_list;
    static ArrayList<Integer>[] graph;
    static int t;
    static StringTokenizer st;
    static boolean check = false;
    static boolean[][] map;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            arr_list = new ArrayList<>();

            int n = Integer.parseInt(br.readLine());
            int[][] dist = new int[n + 2][2];

            for (int i = 0; i < n + 2; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());

                arr_list.add(new Position(row, col));
            }

            map = new boolean[n + 2][n + 2];

            for (int i = 0; i < n + 2; i++) {
                for (int j = i + 1; j < n + 2; j++) {
                    if ((Math.abs(arr_list.get(i).row - arr_list.get(j).row) +
                            Math.abs(arr_list.get(i).col - arr_list.get(j).col)) <= 1000) {
                        map[i][j] = map[j][i] = true;
                    }
                }
            }

            floyd(map, n);

            if (map[0][n + 1]) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }

//            bfs
//            graph = new ArrayList[n+2];
//            for (int i = 0; i < n + 2; i++) {
//                graph[i] = new ArrayList<>();
//            }
//
//            for (int i = 0; i < n + 2; i++) {
//                for (int j = i + 1; j < n + 2; j++) {
//                    if ((Math.abs(arr_list.get(i).row - arr_list.get(j).row) +
//                            Math.abs(arr_list.get(i).col - arr_list.get(j).col)) <= 1000) {
//                        graph[i].add(j);
//                        graph[j].add(i);
//                    }
//                }
//            }
//
//            if (!bfs(graph, n)) {
//                System.out.println("sad");
//            } else {
//                System.out.println("happy");
//            }
        }
    }

    static void floyd(boolean[][] map, int n) {
        for (int k = 0; k < n + 2; k++) {
            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < n + 2; j++) {
                    if (map[i][k] && map[k][j]) {
                        map[i][j] = true;
                    }
                }
            }
        }
    }

    static boolean bfs(ArrayList<Integer>[] graph, int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        boolean[] visited = new boolean[n + 2];

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (curr == n + 1) {
                check = true;
                return true;
            }

            for (int next : graph[curr]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }

        return false;
    }
}
