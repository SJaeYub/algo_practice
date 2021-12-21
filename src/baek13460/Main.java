package baek13460;

import javax.lang.model.SourceVersion;
import java.util.*;

public class Main {

    static int n, m;
    static char[][] map;
    static Queue<Position> q;
    static Queue<Position> b_q;
    static int[] dir = {1, 2, 3, 4}; //1 왼쪽 2 오른쪽 3 위 4 아래
    static int[] temp = new int[10];
    static int answer_flag = 0;
    static int answer_min = Integer.MAX_VALUE;
    static ArrayList<Integer> answer_list = new ArrayList<>();
    static int cnt = 0;

    static class Position {
        int x;
        int y;
        int dir;
        char color;

        Position(int a, int b, int c, char d) {
            this.y = a;
            this.x = b;
            this.dir = c;
            this.color = d;
        }
    }

    static class Ball_state {
        int x;
        int y;
        int wall;

        Ball_state(int a, int b, int c) {
            this.y = a;
            this.x = b;
            this.wall = c;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new char[n][m];
        q = new LinkedList<>();
        b_q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String temp = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = temp.charAt(j);
//                if (map[i][j] == 'R') {
//                    q.add(new Position(i, j, 0, 'R'));
//                }
//                if (map[i][j] == 'B') {
//                    q.add(new Position(i, j, 0, 'B'));
//                }
            }
        }

        for (int i = 1; i <= 4; i++) {
            dfs(i, 0, map);
        }


    }

    static void dfs(int direc, int index, char[][] c) {                         //방향 진행의 모든 경우의 수 입력

        if (index == 10) {
            return;
        }

        char[][] newMap = new char[n][m];
        for (int i = 0; i < 4; i++) {
            int mv = dir[i];
            if (direc != mv) {
                copy(newMap, c);
                temp[index] = mv;
                bfs(mv, newMap);
                if (answer_flag == -1) {
                    return;
                }
                if (answer_flag == 1) {
                    answer_min = Math.min(answer_min, index + 1);
                    return;
                }
                if (index + 1 == 10) {
                    answer_flag = -1;
                    return;
                }
                dfs(mv, index + 1, newMap);
                if (answer_flag == 1) {
                    return;
                }
                if (answer_flag == -1) {
                    return;
                }
            }
        }
    }

    static void check_output(Queue temp, int direc) {

        if (direc == 1) {
            if (temp.size() == 3) {

            }
        }
        if (direc == 2) {

        }
        if (direc == 3) {

        }
        if (direc == 4) {

        }


    }

    //큐에 순서대로 들어가있다 -> 배열 이동!
    static void bfs(int direc, char[][] map) {                            //인자로 들어온 방향으로 구슬 쭉 진행 후 n혹은 n번만큼 이동 후 함수 탈출, #를 만나면 자리이동x
        Queue<Character> temp_q = new LinkedList<>();
//        Position curr_r = q.poll();
//        Position curr_b = q.poll();
        Queue<Position> check = new LinkedList<>();

        if (direc == 1) {
            for (int i = 0; i < n; i++) {
                int temp_sharp = 0;
                int start_x = 0;
                while (temp_sharp < m - 1) {
                    int check_out = 0;
                    int temp_x = temp_sharp;                                     //add 용도
                    start_x = temp_sharp;                                   //add 후 poll 용도
                    while (map[i][temp_x] != '#') {
                        if (map[i][temp_x] == 'R' || map[i][temp_x] == 'B') {
                            if (map[i][temp_x] == 'R') {

                            }
                            temp_q.add(map[i][temp_x]);
                            map[i][temp_x] = '.';
                        }
                        temp_x++;                                           //제일 끝 # 위치 인덱스 저장
                    }

                    while (!temp_q.isEmpty()) {
                        if (map[i][start_x] != '#') {
                            if (map[i][start_x] == '0') {
                                temp_q.poll();
                                continue;
                            }
                            map[i][start_x] = temp_q.poll();
                        }
                        start_x++;
                    }                                       //poll 완료

                    temp_sharp = temp_x + 1;                            //시작 위치 #이후로 조정
                                                                            //이동한 인덱스 사이에 '0'가 있는가?
                }
            }
        }
        if (direc == 2) {                   //오른쪽 이동
            for (int i = 0; i < n; i++) {
                int temp_sharp = m - 1;
                int start_x = m - 1;
                while (temp_sharp > 0) {
                    int check_out = 0;
                    int temp_x = temp_sharp;                                     //add 용도
                    start_x = temp_sharp;                                   //add 후 poll 용도
                    while (map[i][temp_x] != '#') {
                        if (map[i][temp_x] == 'R' || map[i][temp_x] == 'B') {
                            temp_q.add(map[i][temp_x]);
                            map[i][temp_x] = '.';
                        }
                        if (map[i][temp_x] == '0') {
                            check_out = 1;
                        }
                        temp_x--;                                           //제일 끝 # 위치 인덱스 저장
                    }
                    if (check_out == 1) {
                        if (temp_q.size() == 2) {
                            answer_flag = -1;
                            return;
                        }
                        if (temp_q.size() == 1) {
                            if (temp_q.poll() == 'R') {
                                answer_flag = 1;
                                return;
                            }
                            if (temp_q.poll() == 'B') {
                                answer_flag = -1;
                                return;
                            }
                        }
                    }
                    while (!temp_q.isEmpty()) {
                        if (map[i][start_x] != '#') {
                            map[i][start_x] = temp_q.poll();
                        }
                        start_x--;
                    }                                       //poll 완료

                    temp_sharp = temp_x - 1;                            //시작 위치 #이후로 조정
                }
            }
        }
        if (direc == 3) {
            for (int i = 0; i < m; i++) {
                int temp_sharp = 0;
                int start_x = 0;
                while (temp_sharp < n - 1) {
                    int check_out = 0;
                    int temp_x = temp_sharp;                                     //add 용도
                    start_x = temp_sharp;                                   //add 후 poll 용도
                    while (map[temp_x][i] != '#') {
                        if (map[temp_x][i] == 'R' || map[temp_x][i] == 'B') {
                            temp_q.add(map[temp_x][i]);
                            map[temp_x][i] = '.';
                        }
                        if (map[temp_x][i] == '0') {
                            check_out = 1;
                        }
                        temp_x++;                                           //제일 끝 # 위치 인덱스 저장
                    }
                    if (check_out == 1) {
                        if (temp_q.size() == 2) {
                            answer_flag = -1;
                            return;
                        }
                        if (temp_q.size() == 1) {
                            if (temp_q.poll() == 'R') {
                                answer_flag = 1;
                                return;
                            }
                            if (temp_q.poll() == 'B') {
                                answer_flag = -1;
                                return;
                            }
                        }
                    }
                    while (!temp_q.isEmpty()) {
                        if (map[start_x][i] != '#') {
                            map[start_x][i] = temp_q.poll();
                        }
                        start_x++;
                    }                                       //poll 완료

                    temp_sharp = temp_x + 1;                            //시작 위치 #이후로 조정
                }
            }
        }
        if (direc == 4) {
            for (int i = 0; i < m; i++) {
                int temp_sharp = n - 1;
                int start_x = n - 1;
                while (temp_sharp > 0) {
                    int check_out = 0;
                    int temp_x = temp_sharp;                                     //add 용도
                    start_x = temp_sharp;                                   //add 후 poll 용도
                    while (map[temp_x][i] != '#') {
                        if (map[temp_x][i] == 'R' || map[temp_x][i] == 'B') {
                            temp_q.add(map[temp_x][i]);
                            map[temp_x][i] = '.';
                        }
                        if (map[temp_x][i] == '0') {
                            check_out = 1;
                        }
                        temp_x--;                                           //제일 끝 # 위치 인덱스 저장
                    }
                    if (check_out == 1) {
                        if (temp_q.size() == 2) {
                            answer_flag = -1;
                            return;
                        }
                        if (temp_q.size() == 1) {
                            if (temp_q.poll() == 'R') {
                                answer_flag = 1;
                                return;
                            }
                            if (temp_q.poll() == 'B') {
                                answer_flag = -1;
                                return;
                            }
                        }
                    }
                    while (!temp_q.isEmpty()) {
                        if (map[temp_x][i] != '#') {
                            map[temp_x][i] = temp_q.poll();
                        }
                        start_x--;
                    }                                       //poll 완료

                    temp_sharp = temp_x - 1;                            //시작 위치 #이후로 조정
                }
            }
        }
    }

    static void copy(char[][] a, char[][] b) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = b[i][j];
            }
        }
    }
}
