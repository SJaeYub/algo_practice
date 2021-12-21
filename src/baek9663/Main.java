package baek9663;

import java.util.Scanner;

public class Main {

    static int n;
    static boolean[][] visited;
    static int[][] map;
    static int cnt = 0;
    static int[][] ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited = new boolean[n][n];
                visited[i][j] = true;
                dfs(i, j, 1);
                visited[i][j] = false;
            }
        }


    }

    private static void dfs(int row, int col, int idx) {
        visited[row][col] = true;
        if(idx == n) {                  //n개를 성공적으로 놓으면 cnt 증가 시키고 리턴
            cnt++;
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!check(i, j)) {            //새 말을 놓을 수 있는 자리인가 아닌가?

                }
            }
        }
    }

    private static boolean check(int i, int j) {

    }


}
