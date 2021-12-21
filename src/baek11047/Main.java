package baek11047;

import java.util.Scanner;

public class Main {

    static int n, k;
    static int[] coin;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();

        coin = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = sc.nextInt();
        }

        System.out.println(calcul_coin(k));
    }

    static int calcul_coin(int value) {
        int answer = 0;
        for(int i = n-1; i >= 0; i--) {
            while (value / coin[i] >= 1 && value > 0 && value >= coin[i]) {
                answer++;
                value -= coin[i];
            }
            if(value == 0) {
                break;
            }
        }


        return answer;
    }
}
