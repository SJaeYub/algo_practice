package baek15662;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int t, k;
    static int[][] gear;
    static boolean[] rotate_check;
    static Queue<gearState> q = new LinkedList<>();
    static int[] x = {-1, 1};

    static class gearState {
        int gearNum, rotateNum;

        gearState(int a, int b) {
            this.gearNum = a;
            this.rotateNum = b;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        t = sc.nextInt();
        gear = new int[t][8];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < 8; j++) {
                gear[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < k; i++) {
            q.add(new gearState(sc.nextInt(), sc.nextInt()));

        }
    }

    static void checkGear() {
        while (!q.isEmpty()) {
            gearState g = q.poll();

            rotate_check[g.gearNum] = true;

            for (int i = 0; i < 2; i++) {
                int mx = g.gearNum - x[i];

                if (mx == 1) {
                    if (gear[mx][2] == gear[mx + 1][6]) {

                    }
                }
            }
        }
    }

    static void rotate() {

    }
}
