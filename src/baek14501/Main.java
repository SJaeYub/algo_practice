package baek14501;

import java.util.Scanner;

public class Main {

    static class Schedule {
        int time;
        int profit;

        Schedule(int t, int p) {
            this.time = t;
            this.profit = p;
        }
    }

    static int N;
    static Schedule[] arr;
    static boolean[] visited;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        arr = new Schedule[N + 1];
        visited = new boolean[N + 1];

        arr[0] = new Schedule(0, 0);

        for (int i = 1; i <= N; i++) {
            int time = sc.nextInt();
            int cost = sc.nextInt();

            arr[i] = new Schedule(time, cost);
        }

        dfs(0, 0);

        System.out.println(ans);
    }

    static void dfs(int day, int sum) {

        visited[day] = true;

        if(day != 0 && day + arr[day].time > N + 1) {
            return;
        }

        for (int i = day + 1; i <= N; i++) {
            if(day + arr[day].time <= i) {
                if (!visited[i]) {
                    visited[i] = true;
                    int temp_sum = sum + arr[i].profit;
                    dfs(i, temp_sum);
                    visited[i] = false;
                }
            }
        }

        ans = Math.max(ans, sum);
    }


}
