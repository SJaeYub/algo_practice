package baek20208;

import java.util.*;

public class Main {

    static class Position {
        int row;
        int col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[][] map;
    static int n, m, h;
    static ArrayList<Position> arr_list = new ArrayList<>();
    static boolean[] visited;
    static int start_row = 0, start_col = 0, ans = 0;
    static ArrayList<Position> mint_list = new ArrayList<>();
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        h = sc.nextInt();

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) {
                    arr_list.add(new Position(i, j));
                }
                if (map[i][j] == 1) {
                    start_col = j;
                    start_row = i;
                }

            }
        }

        arr = new int[arr_list.size()];
        visited = new boolean[arr_list.size()];

        dfs(0);
        System.out.println(ans);

    }

    static void search_mint(int[] mints) {
        int temp_row = start_row;
        int temp_col = start_col;
        int temp_h = m;
        int temp_ans = 0;
        for (int i = 0; i < mints.length; i++) {
            int k = arr[i];
            int dist = Math.abs(arr_list.get(k).row - temp_row) + Math.abs(arr_list.get(k).col - temp_col);
            int home_dist = Math.abs(start_row - arr_list.get(k).row) + Math.abs(start_col - arr_list.get(k).col);
            if (temp_h >= dist) {
                temp_ans++;
                temp_h -= dist;
                temp_h += h;

                if (temp_h >= home_dist) {          //집에 갈수있다
                    ans = Math.max(ans, temp_ans);          //집에 못가도 다음 민초를 먹고 체력을 얻어 집에 갈수도 있기 때문에 else 처리 안한다
                }

                temp_row = arr_list.get(k).row;
                temp_col = arr_list.get(k).col;
            } else {
                return;
            }
        }
    }

    static void dfs(int cnt) {
        if (cnt == arr_list.size()) {
            search_mint(arr);
            return;
        }

        for (int i = 0 ; i < arr_list.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[cnt] = i;
                dfs(cnt + 1);
                visited[i] = false;
            }
        }
    }
}
