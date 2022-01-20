package baek16928;
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
        int cnt;
        Position(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
    }
    static int n, m;
    static int[][] map;
    static int ans = Integer.MAX_VALUE;
    static boolean[][] visited;
    static StringTokenizer st;
    static Queue<Position> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[10][10];
        visited = new boolean[10][10];
        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int[] info = position_info(from);
            map[info[0]][info[1]] = to;           //사다리 혹은 뱀 출발 위치에 목적지 위치 정보를 담아둔다
        }

        bfs();
    }
    private static void print_map() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void bfs() {
        visited[0][0] = true;
        q.add(new Position(0, 0, 0));
        while (!q.isEmpty()) {
            Position curr = q.poll();
            if (curr.row == 9 && curr.col == 9) {
                System.out.println(curr.cnt);
                return;
            }

            visited[curr.row][curr.col] = true;

            for (int i = 1; i <= 6; i++) {
                int position = (curr.row * 10 + curr.col) + 1;
                int next = position + i;
                int[] arr = position_info(next);
                if (next <= 100 && !visited[arr[0]][arr[1]]) {
                    if (map[arr[0]][arr[1]] != 0) {
                        int[] arr2 = position_info(map[arr[0]][arr[1]]);
                        if (!visited[arr2[0]][arr2[1]]) {
                            visited[arr2[0]][arr2[1]] = true;
                            q.add(new Position(arr2[0], arr2[1], curr.cnt + 1));
                        }
                    } else {
                        visited[arr[0]][arr[1]] = true;
                        q.add(new Position(arr[0], arr[1], curr.cnt + 1));
                    }
                }
            }
        }
    }
    /**
     * @param a = 위치 정보 정수
     * @return = 위치 row, col 배열
     */
    static int[] position_info(int a) {
        int[] result = new int[2];
        if (a % 10 == 0) {
            result[0] = a / 10 - 1;
            result[1] = 9;
        } else {
            result[0] = a / 10;
            result[1] = a % 10 - 1;
        }
        return result;
    }
}
