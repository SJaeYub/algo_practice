package baek20207;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static class Schedule implements Comparable<Schedule> {
        int start;
        int end;

        Schedule(int s, int f) {
            this.start = s;
            this.end = f;
        }

        @Override
        public int compareTo(Schedule s1) {
            if (this.start < s1.start) {
                return -1;
            } else if (this.start == s1.start) {
                return -Integer.compare(this.end, s1.start);
            } else {
                return 1;
            }
        }
    }

    static int n;
    static int[][] map;
    static PriorityQueue<Schedule> pq = new PriorityQueue<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        int last_day = 0;
        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            last_day = Math.max(last_day, end);
            pq.add(new Schedule(start, end));
        }

        map = new int[n][last_day + 2];

        while (!pq.isEmpty()) {
            Schedule curr = pq.poll();
            for (int i = 0; i < n; i++) {
                if (map[i][curr.start] == 1) {
                    continue;
                }
                for (int j = curr.start; j <= curr.end; j++) {
                    map[i][j] = 1;
                }
                break;
            }
        }

        int widestart = 365;
        int wideend = 0;
        int height = 0;
        int areasum = 0;
        for (int j = 1; j < map[0].length; j++) {
            boolean stop = true;
            for (int i = 0; i < n; i++) {
                if (map[i][j] == 1) {
                    wideend = Math.max(wideend, j);
                    widestart = Math.min(widestart, j);
                    height = Math.max(height, i + 1);
                    stop = false;
                }
            }
            if (stop) {
                areasum += ((wideend - widestart) + 1) * height;
                widestart = 365;
                wideend = 0;
                height = 0;
            }
        }
        System.out.println(areasum);
    }
}
