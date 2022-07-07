package baek16395;

import java.util.Scanner;

public class Main {

    static int[][] arr, sum;
    static int n, k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();

        sum = new int[31][31];

        dp(n, k);
        System.out.println(sum[n][k]);

    }

    static void dp(int row, int col) {
        if(row == 1) {
            sum[row][col] =  1;
            return;
        }
        if (col == 1) {
            sum[row][col] =  1;
            return;
        }
        if (col == row) {
            sum[row][col] =  1;
            return;
        }

        dp(row - 1, col - 1);
        dp(row - 1, col);
        sum[row][col] = sum[row - 1][col - 1] + sum[row - 1][col];
//        System.out.println("in dp for " + row + " " + col + " " + sum[row][col]);
        return;

    }
}
