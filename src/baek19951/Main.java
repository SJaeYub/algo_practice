package baek19951;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int n, m, a, b, k;
    static int[] grd;

    static class Command {
        int a;
        int b;
        int k;

        Command(int a, int b, int k) {
            this.a = a;
            this.b = b;
            this.k = k;
        }
    }

    static ArrayList<Command> arr_list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        grd = new int[n + 1];
        int[] cmd = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            grd[i] = sc.nextInt();
        }

        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            k = sc.nextInt();
            cmd[a] += k;
            cmd[b + 1] -= k;
        }

        int temp = 0;
        for (int i = 1; i <= n; i++) {
            temp += cmd[i];
            grd[i] += temp;
            System.out.print(grd[i] + " ");
        }

    }
}
