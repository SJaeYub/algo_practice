package programmers_ROR;

import java.util.*;

class Solution {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Position> q;
    static boolean[][] visited;
    static int n, m;
    static int dist;

    private static class Position {
        int y;
        int x;
        int dist;

        public Position(int a, int b, int c) {
            this.y = a;
            this.x = b;
            this.dist = c;
        }
    }

    public static void main(String[] args) {
        int[][] temp_map = {{1,0,1,1,1},
                            {1,0,1,0,1},
                            {1,0,1,1,1},
                            {1,1,1,0,1},
                            {0,0,0,0,1}};
        System.out.println(solution(temp_map));
    }

    static private int solution(int[][] maps) {
        q = new LinkedList<>();
        int start_y = 0;
        int start_x = 0;
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(maps[i][j] == 0) {
                    visited[i][j] = true;
                }
            }
        }

        // maps.length = y 길이
        // maps[0].length = x 길이                

        Position start = new Position(start_y, start_x, 1);
        q.add(start);

        bfs();

        int answer = dist;
        return answer;
    }

    static void bfs() {
        while(!q.isEmpty()) {
            Position curr = q.poll();
            visited[curr.y][curr.x] = true;

            if(curr.y == n - 1 && curr.x == m - 1) {
                dist = curr.dist;
                return;
            }

            for(int i = 0; i < 4; i++) {
                int my = curr.y + dy[i];
                int mx = curr.x + dx[i];

                if(my >= 0 && my < n && mx >= 0 && mx < m) {
                    if(!visited[my][mx]) {
                        visited[my][mx] = true;
                        Position temp = new Position(my, mx, curr.dist + 1);
                        q.add(temp);
                    }
                }


            }
        }

        dist = -1;
    }
}