package baek10711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static int h, w;
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    static ArrayList<Position> arr_list = new ArrayList<>();
    static StringTokenizer st;
    static Queue<Position> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new int[h][w];
        for (int i = 0; i < h; i++) {
            String input = br.readLine();
            for (int j = 0; j < w; j++) {
                char tmp = input.charAt(j);
                if (tmp == '.') {
                    map[i][j] = 0;
                    q.add(new Position(i, j));
                } else {
                    map[i][j] = Integer.parseInt(String.valueOf(tmp));
                }
            }
        }

        int answer = bfs(h, w);

        System.out.println(answer - 1);
    }

    static int bfs(int h, int w) {
        int result = 0;

        while (!q.isEmpty()) {
            int init_size = q.size();

            for (int i = 0; i < init_size; i++) {
                Position curr = q.poll();

                for (int j = 0; j < 8; j++) {
                    int mr = curr.row + dr[j];
                    int mc = curr.col + dc[j];

                    if (mr >= 0 && mr < h && mc >= 0 && mc < w) {
                        if (map[mr][mc] != 0) {
                            map[mr][mc]--;
                            if (map[mr][mc] == 0) {
                                map[mr][mc] = 0;
                                q.add(new Position(mr, mc));
                            }
                        }
                    }
                }
            }
            result++;
        }

        return result;
    }

    static boolean boom(ArrayList<Position> arr_list) {
        if (arr_list.size() == 0) {
            return true;
        } else {
            //파괴작업 메소드 작성
            for (Position curr : arr_list) {
                map[curr.row][curr.col] = 0;
            }
            return false;
        }
    }
}
