package baek11265;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static class Node {
        int to;
        int time;

        Node(int t, int i) {
            this.to = t;
            this.time = i;
        }
    }

    static int N, M;
    static int[][] relations = new int[N + 1][N + 1];
    static ArrayList<Node>[] arr_list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        arr_list = new ArrayList[N + 1];
        relations = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int from = i;
                int to = j;
                int cost = sc.nextInt();

                relations[from][to] = cost;
            }
        }

        int[][] results = floyd();

        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int target = sc.nextInt();
            int time_limit = sc.nextInt();

            if (relations[start][target] > time_limit) {
                System.out.println("Stay here");
            } else {
                System.out.println("Enjoy other party");
            }
        }
    }

    static int[][] floyd() {
        int[][] result = new int[N + 1][N + 1];

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (relations[i][j] > relations[i][k] + relations[k][j]) {
                        relations[i][j] = relations[i][k] + relations[k][j];
                        result[i][j] = k;
                    }
                }
            }
        }

        return result;
    }
}
