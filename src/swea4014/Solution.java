package swea4014;

import java.util.*;

public class Solution {

    static int n;
    static int size_road;
    static int[][] map;
    static Queue<Position> q;
    static int[] x = {1};
    static boolean[] visited;
    static int[] row_max;
    static int[] row_min;
    static int[][] check_road;
    static int[] row_min_index;

    static class Position {
        int y;
        int x;
        int height;
        int cnt;

        Position(int a, int b, int c, int d) {
            this.y = a;
            this.x = b;
            this.height = c;
            this.cnt = d;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();
            size_road = sc.nextInt();
            map = new int[n][n];
            row_max = new int[n];                               //각 행의 최대 높이 담음
            row_min = new int[n];                               //각 행의 최소 높이 담음
            row_min_index = new int[n];


            for (int i = 0; i < n; i++) {
                row_max[i] = Integer.MIN_VALUE;
                row_min[i] = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                    row_max[i] = Math.max(row_max[i], map[i][j]);
                    row_min[i] = Math.min(row_min[i], map[i][j]);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j != n - 1) {
                        if (row_min[i] == map[i][j + 1]) {
                            row_min_index[i] = j + 1;                           //각 행별 최소높이 인덱스 저장
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                visited = new boolean[n];
                check_road = new int[n][n];
                Position start = new Position(i, 0, map[i][0], 1);          //최소 높이를
                q.add(start);
//                bfs();
            }



        }
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Position curr = q.poll();
            visited[curr.x] = true;

            int mx = curr.x + 1;

            if (mx < n) {
                if (!visited[curr.x]) {
                    if (map[curr.y][curr.x] == map[curr.y][mx] && map[curr.y][mx] != row_max[mx]) {                       //높이가 같은 경우, 그 행의 최대 높이가 아닐때
                        Position temp = new Position(curr.y, mx, map[curr.y][mx], curr.cnt + 1);
                        visited[mx] = true;
                        check_road[curr.y][mx] = 1;
                        q.add(temp);
                    }
                    if (map[curr.y][curr.x] > map[curr.y][mx]) {                        //높이가 낮아진 경우
                        if (Math.abs(map[curr.y][curr.x] - map[curr.y][mx]) == 1) {         //차이가 1이라면
                            Position temp = new Position(curr.y, mx, map[curr.y][mx], 1);
                            visited[mx] = true;
                            check_road[curr.y][mx] = 1;                                 //활주로 설치
                            q.add(temp);
                        }
                    }
                }

            }
        }
    }
}
