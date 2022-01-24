package baek16118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



//시간 초과 나오는 답안임


public class Main {

    static class Edges implements Comparable<Edges> {
        int to;
        int weight;
        int mode;

        Edges(int to, int weight, int mode) {
            this.to = to;
            this.weight = weight;
            this.mode = mode;
        }

        @Override
        public int compareTo(Edges e1) {
            return Integer.compare(this.weight, e1.weight);
        }
    }

    static int n, m;
    static ArrayList<Edges>[] arr_list;
    static StringTokenizer st;
    static int[] d_fox;
    static int[][] d_wol;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        d_fox = new int[n + 1];
        d_wol = new int[n + 1][2];
        arr_list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int[] ints : d_wol) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        Arrays.fill(d_fox, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            arr_list[from].add(new Edges(to, weight * 2, 1));
            arr_list[to].add(new Edges(from, weight * 2, 1));
        }

        int[] results_fox = dijkstra_fox(1);
        int[][] results_wol = dijkstra_wolf(1);

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (results_fox[i] < Math.min(results_wol[i][0], results_wol[i][1])) {
                ans++;
            }
        }

        System.out.println(ans);

    }

    static int[][] dijkstra_wolf(int from) {
        PriorityQueue<Edges> pq = new PriorityQueue<>();
//        int[][] d = new int[n + 1][2];


        d_wol[from][1] = 0;

        pq.add(new Edges(from, 0, 1));

        while (!pq.isEmpty()) {
            Edges curr = pq.poll();

            if (d_wol[curr.to][curr.mode] < curr.weight) {
                continue;
            }

            for (Edges next : arr_list[curr.to]) {
                int mode_reverse = 1 - curr.mode;
                int tmp = 0;
                if (mode_reverse == 0) {
                    tmp = next.weight / 2;
                } else {
                    tmp = next.weight * 2;
                }
                if (d_wol[next.to][mode_reverse] > d_wol[curr.to][curr.mode] + tmp) {
                    d_wol[next.to][mode_reverse] = d_wol[curr.to][curr.mode] + tmp;
                    pq.add(new Edges(next.to, d_wol[next.to][mode_reverse], mode_reverse));
                }
            }
        }
        return d_wol;
    }

    static int[] dijkstra_fox(int from) {
        PriorityQueue<Edges> pq = new PriorityQueue<>();
        d_fox[from] = 0;
        pq.add(new Edges(from, 0, 1));

        while (!pq.isEmpty()) {
            Edges curr = pq.poll();


            if (d_fox[curr.to] < curr.weight) {
                continue;
            }

            for (Edges next : arr_list[curr.to]) {
                if (d_fox[next.to] > d_fox[curr.to] + next.weight) {
                    d_fox[next.to] = d_fox[curr.to] + next.weight;
                    pq.add(new Edges(next.to, d_fox[next.to], 1));
                }

            }

        }

        return d_fox;
    }
}
