package baek1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            makeSet(i);
        }

        for (int i = 0; i < m; i++) {
            String[] str2 = br.readLine().split(" ");

            int cmd = Integer.parseInt(str2[0]);
            int target1 = Integer.parseInt(str2[1]);
            int target2 = Integer.parseInt(str2[2]);

            if (cmd == 0) {
                unionSet(target1, target2);
            } else {
                int result1 = findSet(target1);
                int result2 = findSet(target2);

                if (result1 == result2) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    static void makeSet(int v) {
        parent[v] = v;
    }

    static void unionSet(int u, int v) {
        parent[findSet(u)] = findSet(v);
    }

    static int findSet(int v) {
        if (v == parent[v]) {
            return v;
        } else {
            return parent[v] = findSet(parent[v]);
        }
    }
}
