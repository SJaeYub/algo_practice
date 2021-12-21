package baek14889;

import java.util.Scanner;

public class Main {

    static boolean[] visited;
    static int theNumOfTeamMate;
    static int n, start = 0, link = 0, result = Integer.MAX_VALUE;
    static int[][] s;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        s = new int[n][n];
        visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                s[i][j] = sc.nextInt();
//                System.out.println("s = " + s[i][j]);
            }
        }

        theNumOfTeamMate = n / 2;

        dfs(0, 0);
        System.out.println(result);

    }

    static void dfs(int team, int cnt) {
        if (cnt == theNumOfTeamMate) {
            cal();
            return;
        }

        for(int i = team; i < n; i++) {
            if(visited[i] == false) {
                visited[i] = true;
                dfs(i+1, cnt+1);
                visited[i] = false;
            }
        }
    }

    static int cal() {
        start = 0;
        link = 0;

        for(int i = 0; i < n-1; i++) {
            for(int j = i + 1; j < n; j++) {
                if(visited[i] && visited[j]) {
                    start += s[i][j];
                    start += s[j][i];
                }
                if (!visited[i] && !visited[j]) {
                    link += s[i][j];
                    link += s[j][i];
                }
            }
        }

        int sub = Math.abs(start - link);

        if(sub == 0) {
//            System.out.println(sub);
            result = sub;
            return 0;
        }
        else {
            result = Math.min(result, sub);
            return 0;
        }
    }

}
