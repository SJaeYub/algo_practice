package baek16953;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static long a, b;
    static Queue<Intemp> q;

    private static class Intemp {
        long v;
        long cnt;

        public Intemp(long a, long b) {
            this.v = a;
            this.cnt = b;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        a = sc.nextLong();
        b = sc.nextLong();
        bfs();
    }

    private static void bfs() {
        q = new LinkedList<>();
        Intemp start = new Intemp(a, 0);
        q.add(start);

        while (!q.isEmpty()) {
            Intemp curr = q.poll();

            if (curr.v == b) {
                System.out.println(curr.cnt + 1);
                return;
            }

            if (curr.v >= Math.pow(10, 9)) {
                System.out.println(-1);
                return;
            }

            long twoTimes = curr.v * 2;
            long plusOne = curr.v * 10 + 1;

            Intemp temp1 = new Intemp(twoTimes, curr.cnt + 1);
            Intemp temp2 = new Intemp(plusOne, curr.cnt + 1);
            if(twoTimes <= b) {
                q.add(temp1);
            }
            if(plusOne <= b) {
                q.add(temp2);
            }
        }
        System.out.println(-1);

    }
}
