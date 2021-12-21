package baek9663;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static int n;
    static boolean[][] visited;
    static int cnt = 0;
    static int[][] ans;
    static ArrayList<Position> arr_list = new ArrayList<>();

    private static class Position {
        int row;
        int col;

        Position(int a, int b) {
            this.row = a;
            this.col = b;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = true;
                Position pos = new Position(i, j);
                arr_list.add(pos);                      //체스 말 두는 위치 저장
                dfs(i, j, 1);
                arr_list.clear();                        //리스트 초기화
                visited[i][j] = false;
            }
        }

        System.out.println(cnt/n);

    }

    private static void dfs(int row, int col, int idx) {
        visited[row][col] = true;

        if (idx == n) {
            cnt++;
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {               //말이 놓여있지 않은 경우에
                    if (check(i, j)) {              //새 말을 놓아도 되는 경우에
                        visited[i][j] = true;
                        Position temp = new Position(i, j);
                        arr_list.add(temp);
                        dfs(i, j, idx + 1);
                        arr_list.remove(arr_list.size() - 1);
                        visited[i][j] = false;
                    }
                }
            }
        }

    }

    private static boolean check(int row, int col) {

        for (int i = 0; i < arr_list.size(); i++) {
            if (row == arr_list.get(i).row || col == arr_list.get(i).col) {
                return false;
            }
            if (Math.abs(arr_list.get(i).col - col) == Math.abs(arr_list.get(i).row - row)) {
                return false;
            }
        }

        return true;
    }


}
