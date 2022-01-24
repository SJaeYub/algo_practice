package programmers12978;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    static class Edges implements Comparable<Edges> {
        int to;
        int weight;

        Edges(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edges e1) {
            return Integer.compare(this.weight, e1.weight);
        }
    }

    static ArrayList<Edges>[] arr_list;


    public static void main(String[] args) {
        int[][] inputs = {{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2},
                {5, 3, 1}, {5, 4, 2}};
        System.out.println(solution(5, inputs, 3));
    }

    static int solution(int N, int[][] road, int K) {

        arr_list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int[] inputs : road) {
            arr_list[inputs[0]].add(new Edges(inputs[1], inputs[2]));
            arr_list[inputs[1]].add(new Edges(inputs[0], inputs[2]));
        }

        int[] results = dijkstra(1, N);

        int answer = 0;
        for (int dest : results) {
            if (K >= dest) {
                answer++;
            }
        }

        return answer;
    }

    static int[] dijkstra(int from, int numOfTown) {
        PriorityQueue<Edges> pq = new PriorityQueue<>();
        int[] d = new int[numOfTown + 1];
        boolean[] visited = new boolean[numOfTown + 1];

        Arrays.fill(d, Integer.MAX_VALUE);
        d[from] = 0;

        pq.add(new Edges(from, 0));

        while (!pq.isEmpty()) {
            Edges curr = pq.poll();

            for (Edges next : arr_list[curr.to]) {
                if (!visited[next.to]) {
                    int cost = d[curr.to] + next.weight;
                    if (d[next.to] > cost) {
                        d[next.to] = cost;
                        pq.add(new Edges(next.to, d[next.to]));
                    }
                }
            }

            visited[curr.to] = true;
        }

        return d;
    }
}
