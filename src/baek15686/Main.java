package baek15686;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static class Position {
        int row;
        int col;
        int dist;

        Position(int r, int c, int d) {
            this.row = r;
            this.col = c;
            this.dist = d;
        }
    }

    static int n, m;
    static int ans = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[] visited;
    static ArrayList<Position> home_list = new ArrayList<>();
    static ArrayList<Position> chick_list = new ArrayList<>();

    static Queue<Position> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][n];

        int chick_cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) {
                    home_list.add(new Position(i, j, 0));
                } else if (map[i][j] == 2) {
                    chick_cnt++;
                    chick_list.add(new Position(i, j, 0));
                }
            }
        }

        visited = new boolean[chick_list.size()];

        dfs(0, 0);

        System.out.println(ans);
        return;
    }

    private static void dfs(int start, int cnt) {
        if (cnt == m) {
            int result = 0;
            for (int i = 0; i < home_list.size(); i++) {
                int temp = Integer.MAX_VALUE;
                for (int j = 0; j < chick_list.size(); j++) {
                    if (visited[j]) {
                        int dist = Math.abs(home_list.get(i).row - chick_list.get(j).row) +
                                Math.abs(home_list.get(i).col - chick_list.get(j).col);

                        temp = Math.min(temp, dist);
                    }
                }
                result += temp;
            }
            ans = Math.min(ans, result);
            return;
        }

        for (int i = start; i < chick_list.size(); i++) {
            visited[i] = true;
            dfs(i + 1, cnt + 1);
            visited[i] = false;
        }
    }


}
