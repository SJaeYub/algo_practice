package baek15558;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Position {
    int x;
    int y;
    int del_pos;

    Position(int a, int b, int del) {
        this.x = a;
        this.y = b;
        this.del_pos = del;
    }
}

public class Main {

    static int[] x = {0, 0, 1, -1};
    static int[] y = {1, -1, 0, 0};
    static int[] z;
    static int n, k;
    static int[][] col;
    static Queue<Position> q;
    static boolean visited[][];
    static boolean flag = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();
        z = new int[4];
        z[0] = k;
        z[1] = k;
        z[2] = 0;
        z[3] = 0;
        col = new int[2][n];
        String col_State = sc.next();

        visited = new boolean[2][n+100000];
        for (int i = 0; i < n; i++) {
            col[0][i] = col_State.charAt(i) - '0';
//            System.out.print(col[0][i]);
            if (col[0][i] == 0) {
                visited[0][i] = true;
            }
        }
//        System.out.println();

        col_State = sc.next();
        for (int i = 0; i < n; i++) {
            col[1][i] = col_State.charAt(i) - '0';
//            System.out.print(col[1][i]);
            if (col[1][i] == 0) {
                visited[1][i] = true;
            }

        }
        q = new LinkedList<>();

        Position n = new Position(0, 0, -1);
        q.add(n);

        bfs();

        if(!flag) {
            System.out.println(0);
        }
    }

    static void bfs() {

        while (!q.isEmpty()) {
            Position current = q.poll();
            visited[current.y][current.x] = true;
//            System.out.println(current.y + " " + current.x);

            if (current.x >= n) {
                System.out.println(1);
                flag = true;
                return;
            }
            int current_del = current.del_pos;

            for (int i = 0; i < 4; i++) {
                int goAndBack = current.x + x[i];
                int tempJump = current.y + y[i];
                int jumpX = current.x + z[i];

                if (goAndBack > current_del && tempJump < 2 && jumpX > current_del && tempJump >= 0) {
                    if (!visited[tempJump][goAndBack]) {
                        Position temp = new Position(goAndBack, tempJump, current_del + 1);
                        q.add(temp);
                        visited[tempJump][goAndBack] = true;
                    }

                    if (!visited[tempJump][jumpX]) {
                        Position temp = new Position(jumpX, tempJump, current_del + 1);
                        q.add(temp);
                        visited[tempJump][jumpX] = true;
                    }
                }

            }
        }
    }
}
