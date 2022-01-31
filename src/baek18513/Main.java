package baek18513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {

    static class RestRoom {
        long position;
        long dist;

        RestRoom(long position, long dist) {
            this.position = position;
            this.dist = dist;
        }
    }

    static int n, k;
    static StringTokenizer st;
    static Queue<RestRoom> q = new LinkedList<>();
    static HashSet<Long> check = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            long restRoom = Long.parseLong(st.nextToken());
            check.add(restRoom);
            q.add(new RestRoom(restRoom, 0));
        }

        long answer = bfs();
        System.out.println(answer);
    }

    static long bfs() {
        long result = 0;
        int cnt = 0;

        while (!q.isEmpty()) {
            RestRoom curr = q.poll();
            Long before = curr.position - 1;
            Long after = curr.position + 1;

            if (!check_pos(before)) {     //지어져 있지 않다면
                check.add(before);
                q.add(new RestRoom(before, curr.dist + 1));
                result += curr.dist + 1;
                cnt++;
                if (cnt >= k) {
                    break;
                }
            }
            if (!check_pos(after)) {
                check.add(after);
                q.add(new RestRoom(after, curr.dist + 1));
                result += curr.dist + 1;
                cnt++;
                if (cnt >= k) {
                    break;
                }
            }
        }

        return result;
    }

    static boolean check_pos(Long position) {
        if (!check.contains(position)) {       //비어있다면 false라면
            return false;
        } else {
            return true;                //이미 지어져있다면
        }
    }
}
