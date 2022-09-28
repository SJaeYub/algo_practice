package baek17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class AirCleaner {
        int row;
        int col;
        boolean upper;

        AirCleaner(int r, int c, boolean f) {
            this.row = r;
            this.col = c;
            this.upper = f;
        }
    }

    static class Dust {
        int row;
        int col;
        int quantity;

        Dust(int r, int c, int q) {
            this.row = r;
            this.col = c;
            this.quantity = q;
        }
    }

    static int[][] map;
    static AirCleaner[] cleaner = new AirCleaner[2];
    static int R, C, T;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    static Queue<Dust> dust_q;
    static boolean[][] visited;
    static int ans = -1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R + 1][C + 1];
        visited = new boolean[R + 1][C + 1];
        dust_q = new LinkedList<>();
        boolean upper_flag = false;

        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= C; j++) {
                int dust_val = Integer.parseInt(st.nextToken());
                int row = i;
                int col = j;

                map[i][j] = dust_val;

                if (dust_val == -1) {
                    if (!upper_flag) {       //위쪽 청소기면
                        cleaner[0] = new AirCleaner(row, col, true);
                        upper_flag = true;
                    } else {            //아래쪽 청소기면
                        cleaner[1] = new AirCleaner(row, col, false);
                        upper_flag = true;
                    }
                }
                if (dust_val != 0) {         //먼지면
                    dust_q.add(new Dust(row, col, dust_val));
                }
            }
        }

//        for (int i = 1; i <= R; i++) {
//            for (int j = 1; j <= C; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
        for (int k = 0; k < T; k++) {
            addDust(dust_q);

//            System.out.println("확산 후");
//            for (int i = 1; i <= R; i++) {
//                for (int j = 1; j <= C; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }

            airCleaner(cleaner);

//            System.out.println("공기청정기 실행 후");
//            for (int i = 1; i <= R; i++) {
//                for (int j = 1; j <= C; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }

            ans = -1;
            dust_q = new LinkedList<>();
//            visited = new boolean[R + 1][C + 1];
            ans = search_dust(1, 1, map[1][1]);

            if (k == T - 1) {   //이때 탐색해서 총 먼지의 양 출력
                System.out.println(ans);
                return;
            }
        }
    }

    static int search_dust(int row, int col, int sum) {
        int result = 0;

        for (int i = 1; i <= C; i++) {
            for (int j = 1; j <= R; j++) {
                if(map[j][i] != -1 && map[j][i] != 0) {
                    dust_q.add(new Dust(j, i, map[j][i]));
                    result += map[j][i];
                }
            }
        }

        return result;
    }

    static void airCleaner(AirCleaner[] air_arr) {
        for (int i = 0; i < 2; i++) {
            AirCleaner curr = air_arr[i];
            if (curr.upper) {      //위
                int mr = curr.row + dr[0];
                int mc = curr.col + dc[0];
                movingAir(1, mr, mc, false, 0, 0);
            } else {                    //아래
                int mr = curr.row + dr[0];
                int mc = curr.col + dc[0];
                movingAir(2, mr, mc, false, 0, 0);
            }
        }
    }

    /**
     * @param dir        위쪽 청소기 or 아래쪽 청소기
     * @param row        y 위치
     * @param col        x 위치
     * @param start_flag 시작인지
     * @param dustQaun   해당 수행에서 적용될 먼지양
     * @param m_dir      이동방향 오,위,왼,아래 == 0,1,2,3
     */
    static void movingAir(int dir, int row, int col, boolean start_flag, int dustQaun, int m_dir) {
        if (start_flag) {
            return;
        }

        if (map[row][col] == -1) {
            return;
        }

        int next_val = map[row][col];
        map[row][col] = dustQaun;

        if (dir == 1) {      //위 면
            int mr = row + dr[m_dir];
            int mc = col + dc[m_dir];

            if (mc == C) {        //오른쪽 벽에 부딫힐때
                m_dir = 1;
            }
            if (mr == 1 && mc == C) {        //위쪽 & 오른쪽벽에 부딭힐때
                m_dir = 2;
            }
            if (mc == 1 && mr == 1) {        //왼쪽 & 위쪽 벽에 부딫힐 때
                m_dir = 3;
            }
            if (mr == R) {
                m_dir = 0;
            }

            if (mr >= 1 && mr <= R && mc >= 1 && mc <= C) {
                if (map[mr][mc] == -1) {
                    start_flag = true;
                }
                movingAir(dir, mr, mc, start_flag, next_val, m_dir);
            }

            return;
        }
        if (dir == 2) {      //아래 면
            int mr = row + dr[m_dir];
            int mc = col + dc[m_dir];

            if (mc == C) {        //오른쪽 벽에 부딫힐때
                m_dir = 3;
            }
            if (mr == 1) {        //위쪽 벽에 부딭힐때
                m_dir = 0;
            }
            if (mc == 1 && mr == R) {        //왼쪽 & 아래쪽 벽에 부딫힐 때
                m_dir = 1;
            }
            if (mr == R && mc == C) {       //아래쪽 & 오른쪽 벽에 부딪힐 때
                m_dir = 2;
            }

            if (mr >= 1 && mr <= R && mc >= 1 && mc <= C) {
                if (map[mr][mc] == -1) {
                    start_flag = true;
                }
                movingAir(dir, mr, mc, start_flag, next_val, m_dir);
            }

            return;
        }
    }

    static int addDust(Queue<Dust> origin_q) {
        Queue<Dust> after_q = new LinkedList<>();
        int total_dust_quan = 0;

        while (!origin_q.isEmpty()) {        //기존 먼지가 존재한다면
            Dust curr = origin_q.poll();

            int dir_cnt = 0;
            for (int i = 0; i < 4; i++) {
                int mr = dr[i] + curr.row;
                int mc = dc[i] + curr.col;
                int after_dust_val = curr.quantity / 5;

                //칸을 벗어나지 않고
                if (mr >= 1 && mr <= R && mc >= 1 && mc <= C) {
                    //공기청정기가 위치하지 않았다면
                    if (map[mr][mc] != -1) {
                        after_q.add(new Dust(mr, mc, after_dust_val));
                        total_dust_quan += after_dust_val;
                        dir_cnt++;
                    }
                }
            }

            map[curr.row][curr.col] = curr.quantity - (curr.quantity / 5) * dir_cnt;
        }

        q_clean(after_q);

        return total_dust_quan;
    }

    static void q_clean(Queue<Dust> remain_q) {     //확산

        while (!remain_q.isEmpty()) {
            Dust curr = remain_q.poll();

            if (map[curr.row][curr.col] != -1) {
                map[curr.row][curr.col] += curr.quantity;
            }
        }
    }

}
