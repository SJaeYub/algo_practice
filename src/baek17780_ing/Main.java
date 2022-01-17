package baek17780_ing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Piece {
        int row;
        int col;
        int dir;
        int height;

        Piece(int row, int col, int direction, int h) {
            this.row = row;
            this.col = col;
            this.dir = direction;
            this.height = h;
        }
    }

    static int n;
    static int k;
    static Queue<Piece>[][] virtual;
    static int[][] map;
    static int[] dr = {0, 0, 0, -1, 1};
    static int[] dc = {0, 1, -1, 0, 0};
    static Queue<Piece> q = new LinkedList<>();
    static ArrayList<Piece> arr_list = new ArrayList<>();
    static StringTokenizer st;
    static boolean flag = false;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        virtual = new LinkedList[n][n];
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                virtual[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            Piece piece = new Piece(row, col, dir, 1);
            virtual[row][col].add(piece);
            arr_list.add(piece);
        }

        while (!flag) {
            ans++;
            moving_piece();
        }
        if (ans <= 1000) {
            System.out.println(ans);
        } else {
            System.out.println(-1);
        }
    }

    static void moving_piece() {
        q.clear();
        q.addAll(arr_list);

        while (!q.isEmpty()) {
            Piece curr = q.poll();
            boolean tmp = false;
            if (curr.height == 1) {
                if (virtual[curr.row][curr.col].size() >= 4) {
                    System.out.println(0);
                    System.exit(0);
                }

                int mr = dr[curr.dir] + curr.row;
                int mc = dc[curr.dir] + curr.col;

                if (mr < 0 || mr >= n || mc < 0 || mc >= n) {
                    tmp = true;
                }

                if (tmp) {

                } else {
                    if (map[mr][mc] == 1) {
                        reverse_dir(curr);
                        int new_r = dr[curr.dir] + curr.row;
                        int new_c = dc[curr.dir] + curr.col;

                        tmp = false;
                        if (mr < 0 || mr >= n || mc < 0 || mc >= n) {
                            tmp = true;
                        }

                        if (tmp) {
                            System.out.println(-1);
                            System.exit(0);
                        } else {
                            if (map[new_r][new_c] == 1) {

                            } else if (map[new_r][new_c] == 2) {
                                System.out.println(-1);
                                System.exit(0);
                            } else {
                                move(curr, new_r, new_c, false);
                            }
                        }
                    } else if (map[mr][mc] == 2) {
                        reverse_dir(curr);
                        int new_r = dr[curr.dir] + curr.row;
                        int new_c = dc[curr.dir] + curr.col;

                        tmp = false;
                        if (mr < 0 || mr >= n || mc < 0 || mc >= n) {
                            tmp = true;
                        }

                        if (tmp) {
                            System.out.println(-1);
                            System.exit(0);
                        } else {
                            if (map[new_r][new_c] == 1) {

                            } else if (map[new_r][new_c] == 2) {
                                System.out.println(-1);
                                System.exit(0);
                            } else {
                                move(curr, new_r, new_c, false);
                            }
                        }


                    } else {
                        move(curr, mr, mc, false);
                    }
                }
            } else {
                continue;
            }

        }
    }

    //    static void moving_piece() {
//        q.clear();
//        q.addAll(arr_list);             //1~k번까지 기물을 큐에 새로 담아준다
//        while (!q.isEmpty()) {
//            boolean tmp = false;
//            Piece curr = q.poll();
//            if (curr.height == 1) {     //현재 기물이 바닥인지 아닌지
//                int mr = dr[curr.dir] + curr.row;
//                int mc = dc[curr.dir] + curr.col;
//
//                if (mr < 0 || mr >= n || mc < 0 || mc >= n) {           //맵을 벗어난다면
//                    tmp = true;
//                }
//
//                if (tmp) {
//                    reverse_dir(curr);                                      //방향을 바꾸고
//                    int new_r = dr[curr.dir] + curr.row;
//                    int new_c = dc[curr.dir] + curr.col;
//
//                    if (map[new_r][new_c] == 1) {
//                        move(curr, new_r, new_c, true);                 //이동 시키고 위치 인덱스도 바까주고
//                    } else if (map[new_r][new_c] == 2) {
//                        //파랑이면 아무것도 안함
//                    } else if (map[new_r][new_c] == 0) {
//                        move(curr, new_r, new_c, false);            //기존 위치의 모든 기물을 이동시킨다
//                    }
//
//                } else {                                    //아니라면
//
//                    if (map[mr][mc] == 1) {                 //이동 칸이 빨강
//                        move(curr, mr, mc, true);                 //이동 시키고 위치 인덱스도 바까주고
//                    } else if (map[mr][mc] == 2) {          //이동 칸이 파랑
//                        reverse_dir(curr);                  //방향 뒤집어 주고
//
//                        int new_r = dr[curr.dir] + curr.row;    //바뀐 방향의 이동할 타일 색
//                        int new_c = dc[curr.dir] + curr.col;
//
//                        tmp = false;            //tmp 초기화
//                        if (new_r < 0 || new_r >= n || new_c < 0 || new_c >= n) {
//                            tmp = true;
//                        }
//
//                        if (tmp) {           //맵을 벗어나면
//                            //바뀐 방향 유지한채 끝
//                        } else {                  //맵을 벗어나지 않는다면
//                            if (map[new_r][new_c] == 1) {
//                                move(curr, new_r, new_c, true);                 //이동 시키고 위치 인덱스도 바까주고
//                            } else if (map[new_r][new_c] == 2) {
//                                //파랑이면 아무것도 안함
//                            } else if (map[new_r][new_c] == 0) {
//                                move(curr, new_r, new_c, false);            //기존 위치의 모든 기물을 이동시킨다
//                            }
//                        }
//                    } else {                                //이동 칸이 흰
//                        move(curr, mr, mc, false);                 //기존 위치의 모든 기물을 이동시킨다
//                    }
//                }
//            } else {            //위에 얹혀있는 기물이면 스킵
//                continue;
//            }
//        }
//    }
//
    static void move(Piece piece, int mr, int mc, boolean red_check) {
        int next_height = virtual[mr][mc].size();
        for (Piece temp : virtual[mr][mc]) {
            temp.height += next_height;
            virtual[mr][mc].add(temp);
            if (virtual[mr][mc].size() >= 4) {
                flag = true;
                return;
            }
        }
    }

    //
//    static void reverse_height(ArrayList<Piece> arr_tmp, int start) {
//        int cnt = 0;
//        for (int i = arr_tmp.size() - 1; i >= 0; i--) {
//            arr_tmp.get(i).height = start + cnt;
//            cnt++;
//        }
//    }
//
    static void reverse_dir(Piece piece) {
        if (piece.dir == 1) {                //기존 방향에 맞춰 뒤집어준다
            piece.dir = 2;
        } else if (piece.dir == 2) {
            piece.dir = 1;
        } else if (piece.dir == 3) {
            piece.dir = 4;
        } else {
            piece.dir = 3;
        }
    }

    private static void print_map(int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
