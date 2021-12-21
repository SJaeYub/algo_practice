package baek13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class Practice {

    static class Ball {
        int redR, redC, blueR, blueC, cnt;

        Ball() {

        }

        Ball(int a, int b, int c, int d, int e) {
            this.redR = a;
            this.redC = b;
            this.blueR = c;
            this.blueC = d;
            this.cnt = e;
        }
    }

    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Ball ball;
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];              //빨간 공 파란공 위치정보 저장
        ball = new Ball();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    ball.redR = i;
                    ball.redC = j;
                } else if (map[i][j] == 'B') {
                    ball.blueR = i;
                    ball.blueC = j;
                }
            }
        }
        bfs();
    }

    static void bfs() {
        Queue<Ball> q = new LinkedList<>();
        q.add(ball);
        visited[ball.redR][ball.redC][ball.blueR][ball.blueC] = true;

        while (!q.isEmpty()) {
            Ball cur = q.poll();
            if (cur.cnt >= 10) {
                System.out.println(-1);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int br = cur.blueR, bc = cur.blueC;
                boolean isBlueHole = false;
                while (map[br + dr[i]][bc + dc[i]] != '#') {
                    br += dr[i];                //한쪽방향으로 계속 이동
                    bc += dc[i];

                    if (map[br][bc] == '0') {
                        isBlueHole = true;
                        break;
                    }
                }

                int rr = cur.redR, rc = cur.redC;
                boolean isRedHole = false;
                while (map[rr + dr[i]][rc + dc[i]] != '#') {
                    rr += dr[i];
                    rc += dc[i];

                    if (map[rr][rc] == '0') {
                        isRedHole = true;
                        break;
                    }
                }

                if (isBlueHole) {
                    continue;
                }
                if (isRedHole) {
                    System.out.println(cur.cnt + 1);
                    return;
                }

                if (br == rr && bc == rc) {
                    switch (i) {
                        case 0:
                            if (cur.redR > cur.blueR) {             //이동 전 빨강 공 파란공의 위치정보로 순서 조정
                                rr++;
                            } else {
                                br++;
                            }
                            break;
                        case 1:
                            if (cur.redR > cur.blueR) {
                                br--;
                            } else {
                                rr--;
                            }
                            break;
                        case 2:
                            if (cur.redC > cur.blueC) {
                                rc++;
                            } else {
                                bc++;
                            }
                            break;
                        case 3:
                            if (cur.redC > cur.blueC) {
                                bc--;
                            } else {
                                rc--;
                            }
                            break;
                    }
                }

                if (!visited[rr][rc][br][bc]) {
                    visited[rr][rc][br][bc] = true;
                    q.add(new Ball(rr, rc, br, bc, cur.cnt + 1));
                }
            }
        }
        System.out.println(-1);
    }
}
