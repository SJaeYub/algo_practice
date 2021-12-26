package baek11403;

import java.util.Scanner;

public class Main {

    static int n;
    static int[][] relations;
    static int[][] p;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        relations = new int[n][n];
        p = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                relations[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    relations[i][j] = 0;
                } else if (relations[i][j] != 1) {
                    relations[i][j] = 0;
                }
            }
        }

        floyd();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(relations[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static void floyd() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (relations[i][k] == 1 && relations[k][j] == 1) {     //단순히 i에서 j로 가는 길이 있는지
                        relations[i][j] = 1;
                    }
                }
            }
        }
    }

    private static void path(int q, int r) {
        if (p[q][r] != 0) {
            path(q, p[q][r]);
            System.out.println(p[q][r]);
            path(p[q][r], r);
        }
    }
}
