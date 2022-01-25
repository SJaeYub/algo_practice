package baek17780_ing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Object {
        int num;
        int dir;

        Object(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }
    }

    static int[][] object_order;
    static Queue<Object>[][] virtual_map;
    static int[][] map;
    static int n, k;
    static StringTokenizer st;
    static int[] dc = {0, 1, -1, 0, 0};
    static int[] dr = {0, 0, 0, -1, 1};
    static boolean ans_flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        virtual_map = new LinkedList[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                virtual_map[i][j] = new LinkedList<>();
            }
        }

        object_order = new int[k + 1][2];           //기물 이동 순서를 위해
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            Object tmp = new Object(i + 1, dir);
            virtual_map[row][col].add(tmp);
            object_order[i + 1][0] = row;
            object_order[i + 1][1] = col;
        }

        for (int[] ints : object_order) {
            System.out.println(ints[0] + " " + ints[1]);
        }

        int ans = 0;
        while (!ans_flag) {
            ans++;
            move_object();
            if (ans >= 1000) {
                System.out.println(-1);
                ans_flag = true;
            }
        }
        if (ans < 1000) {
            System.out.println(ans);
        }

    }

    static boolean check_index(int row, int col, int num) {      //움직일 순서의 기물의 위치정보를 받아서

        System.out.println(num + " " + row + " " + col + " "
         + virtual_map[row][col].peek().dir);

        if (virtual_map[row][col].peek().num == num) {              //바닥 기물 번호가 자기자신이라면
            return true;
        } else {
            return false;
        }
    }

    static void move_object() {
        for (int i = 1; i <= k; i++) {
            if (ans_flag) {
                break;
            }
            int curr_row = object_order[i][0];
            int curr_col = object_order[i][1];

            if (!check_index(curr_row, curr_col, i)) {
                continue;
            }

            Object curr = virtual_map[curr_row][curr_col].peek();

            int next_row = curr_row + dr[curr.dir];
            int next_col = curr_col + dc[curr.dir];
            int next_box_col;

            if (next_row < 1 || next_row > n || next_col < 1 || next_col > n) {             //벗어나느 ㄴ경우
                change_dir(curr_row, curr_col);             //방향을 바꿔주고

                next_row = curr_row + dr[curr.dir];
                next_col = curr_col + dc[curr.dir];
                next_box_col = map[next_row][next_col];

                if (next_box_col == 0) {                //흰
                    move(curr_row, curr_col, next_row, next_col);
                } else if (next_box_col == 1) {             //빨
                    move(curr_row, curr_col, next_row, next_col);

                    reverse_index(next_row, next_col);

               } else {                                    //파
                    continue;
                }

            } else {        //벗어나지 않는 경우에
                next_box_col = map[next_row][next_col];
                if (next_box_col == 0) {                //흰
                    move(curr_row, curr_col, next_row, next_col);

                } else if (next_box_col == 1) {             //빨
                    move(curr_row, curr_col, next_row, next_col);

                    reverse_index(next_row, next_col);

                } else {                                    //파
                    change_dir(curr_row, curr_col);             //방향을 바꿔주고

                    next_row = curr_row + dr[curr.dir];
                    next_col = curr_col + dc[curr.dir];

                    if (next_row < 1 || next_row > n || next_col < 1 || next_col > n) {
                        continue;
                    } else {
                        next_box_col = map[next_row][next_col];
                        if (next_box_col == 0) {                //흰
                            move(curr_row, curr_col, next_row, next_col);

                        } else if (next_box_col == 1) {             //빨
                            move(curr_row, curr_col, next_row, next_col);

                            reverse_index(next_row, next_col);

                        } else {                                    //파
                            continue;
                        }
                    }
                }
            }
            System.out.println(curr.num + " " + next_row + " " + next_col
            + " " + virtual_map[next_row][next_col].peek().dir);
        }
        return;
    }

    static void reverse_index(int row, int col) {
        Object[] tmp_arr = new Object[virtual_map[row][col].size()];
        int idx = 0;

        while (!virtual_map[row][col].isEmpty()) {
            tmp_arr[idx] = virtual_map[row][col].poll();
            idx++;
        }

        for (int i = tmp_arr.length - 1; i >= 0; i--) {
            virtual_map[row][col].add(tmp_arr[i]);
        }

    }

    static void change_dir(int row, int col) {
        int curr_dir = virtual_map[row][col].peek().dir;
        if (curr_dir == 1) {
            virtual_map[row][col].peek().dir = 2;
        } else if (curr_dir == 2) {
            virtual_map[row][col].peek().dir = 1;
        } else if (curr_dir == 3) {
            virtual_map[row][col].peek().dir = 4;
        } else {
            virtual_map[row][col].peek().dir = 3;
        }
    }

    static void move(int row, int col, int next_r, int next_c) {
        while (!virtual_map[row][col].isEmpty()) {
            Object curr = virtual_map[row][col].poll();         //기존꺼에서 뽑아다

            virtual_map[next_r][next_c].add(curr);              //새거에다 넣어주고
            object_order[curr.num][0] = next_r;
            object_order[curr.num][1] = next_c;                 //위치 정보 업데이트

            if (virtual_map[next_r][next_c].size() >= 4) {
                ans_flag = true;
                return;
            }
        }
    }

    private static void print_vir_map() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(virtual_map[i][j].size() + " ");
            }
            System.out.println();
        }
    }

}
