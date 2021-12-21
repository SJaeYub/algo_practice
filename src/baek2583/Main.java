package baek2583;

import java.util.*;

public class Main {

    static int n, m, k;
    static Queue<Position> q;
    static int[][] map;
    static boolean[][] visited;
    static int[] x = {0, 0, -1, 1};
    static int[] y = {-1, 1, 0, 0};
    static LinkedList<Integer> arr_list = new LinkedList<>();

    private static class Position {
        int y, x;
        int size;

        Position(int a, int b, int c) {
            this.y = a;
            this.x = b;
            this.size = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        k = sc.nextInt();

        int cnt = 0;

        map = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < k; i++) {
            int first_x = sc.nextInt(),
                    first_y = sc.nextInt();
            int sec_x = sc.nextInt(),
                    sec_y = sc.nextInt();

            for (int j = first_x; j < sec_x; j++) {
                for (int k = first_y; k < sec_y; k++) {
                    map[k][j] = 1;
                }
            }

        }

        q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 1) {
                    if (!visited[i][j]) {
                        q.add(new Position(i, j, 1));
                        cnt++;
                        bfs();
                    }
                }
            }
        }

        int[] arr = new int[cnt];

        for (int i = 0; i < cnt; i++) {
            arr[i] = arr_list.poll();
        }

        Arrays.sort(arr);

        System.out.println(cnt);
        for (int i = 0; i < cnt; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void bfs() {
        int size = 0;

        while (!q.isEmpty()) {
            Position curr = q.poll();
            visited[curr.y][curr.x] = true;

            size += curr.size;

            for (int i = 0; i < 4; i++) {
                int mx = curr.x + x[i];
                int my = curr.y + y[i];

                if (my >= 0 && my < m && mx >= 0 && mx < n) {
                    if (map[my][mx] == 0) {
                        if(!visited[my][mx]) {
                            visited[my][mx] = true;
                            q.add(new Position(my, mx, 1));
                        }
                    }
                }

            }
        }

        arr_list.add(size);
    }
}
