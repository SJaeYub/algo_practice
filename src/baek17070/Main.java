package baek17070;

import java.util.Scanner;

public class Main {

    static class Pipe {
        int row;
        int col;
        int dist;       //가로 0, 대각선 1, 세로 2

        Pipe(int r, int c, int d) {
            row = r;
            col = c;
            dist = d;
        }
    }

    static int[] dr = {0, 1, 1};
    static int[] dc = {1, 1, 0};

    static int N;
    static int ans = 0;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        visited[1][1] = true;
        visited[1][2] = true;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = sc.nextInt();

                if (map[i][j] == 1) {
                    visited[i][j] = true;
                }

                if (i == N && j == N) {
                    if (map[i][j] == 1) {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }

        Pipe start = new Pipe(1, 2, 0);
        dfs(start);

        System.out.println(ans);
    }

    static void dfs(Pipe curr) {
        if (curr.row == N && curr.col == N) {
            ans++;
            return;
        }

        visited[curr.row][curr.col] = true;

        if (curr.dist == 0) {       //가로일때
            for (int i = 0; i < 3; i++) {
                if (i == 2) {
                    continue;
                }
                int mr = curr.row + dr[i];
                int mc = curr.col + dc[i];

                boolean flag = false;

                if (mr >= 1 && mr <= N && mc <= N && mc >= 1) {
                    for (int j = curr.row; j <= mr; j++) {
                        for (int l = curr.col; l <= mc; l++) {
                            if (j == curr.row && l == curr.col) {
                                continue;
                            }

                            if (map[j][l] == 1 || visited[j][l]) {
                                flag = true;
                                break;
                            }
                        }

                        if (flag) {
                            break;
                        }
                    }

                    if (!flag) {
                        for (int j = curr.row; j <= mr; j++) {
                            for (int l = curr.col; l <= mc; l++) {
                                visited[j][l] = true;
                            }
                        }

                        dfs(new Pipe(mr, mc, i));

                        for (int j = curr.row; j <= mr; j++) {
                            for (int l = curr.col; l <= mc; l++) {
                                visited[j][l] = false;
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        if (curr.dist == 1) {
            for (int i = 0; i < 3; i++) {
                int mr = curr.row + dr[i];
                int mc = curr.col + dc[i];

                boolean flag = false;

                if (mr >= 1 && mr <= N && mc <= N && mc >= 1) {
                    for (int j = curr.row; j <= mr; j++) {
                        for (int l = curr.col; l <= mc; l++) {
                            if (j == curr.row && l == curr.col) {
                                continue;
                            }

                            if (map[j][l] == 1 || visited[j][l]) {
                                flag = true;
                                break;
                            }
                        }

                        if (flag) {
                            break;
                        }
                    }

                    if (!flag) {
                        for (int j = curr.row; j <= mr; j++) {
                            for (int l = curr.col; l <= mc; l++) {
                                visited[j][l] = true;
                            }
                        }

                        dfs(new Pipe(mr, mc, i));

                        for (int j = curr.row; j <= mr; j++) {
                            for (int l = curr.col; l <= mc; l++) {
                                visited[j][l] = false;
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        if (curr.dist == 2) {
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    continue;
                }
                int mr = curr.row + dr[i];
                int mc = curr.col + dc[i];

                boolean flag = false;

                if (mr >= 1 && mr <= N && mc <= N && mc >= 1) {
                    for (int j = curr.row; j <= mr; j++) {
                        for (int l = curr.col; l <= mc; l++) {
                            if (j == curr.row && l == curr.col) {
                                continue;
                            }

                            if (map[j][l] == 1 || visited[j][l]) {
                                flag = true;
                                break;
                            }
                        }

                        if (flag) {
                            break;
                        }
                    }

                    if (!flag) {
                        for (int j = curr.row; j <= mr; j++) {
                            for (int l = curr.col; l <= mc; l++) {
                                visited[j][l] = true;
                            }
                        }

                        dfs(new Pipe(mr, mc, i));

                        for (int j = curr.row; j <= mr; j++) {
                            for (int l = curr.col; l <= mc; l++) {
                                visited[j][l] = false;
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
    }

}
