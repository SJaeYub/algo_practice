package baek14467;

import java.util.Scanner;

public class Main {

    static int n;
    static int[] numOfCow = new int[11];
    static boolean[] visited = new boolean[11];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int cow = sc.nextInt();
            int position = sc.nextInt();

            if(visited[cow]) {
                if (numOfCow[cow] != position) {
                    numOfCow[cow] = position;
                    ans++;
                }
            } else {
                visited[cow] = true;
                numOfCow[cow] = position;
            }
        }

        System.out.println(ans);
    }
}
