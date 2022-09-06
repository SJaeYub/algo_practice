package swea_graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_basic_bfs {

    static class UserSolution {

        static class Node {
            int row;
            int col;
            int dist;

            Node(int r, int c, int d) {
                this.row = r;
                this.col = c;
                this.dist = d;
            }
        }

        static final int node_num = 100;
        static int q_size = 0;
        static int front = -1, rear = -1;


        static void q_add(Node input) {
            if (q_size >= node_num) {
//                System.out.println("overflow");
                return;
            } else {
                q_size++;
                rear = (rear + 1) % node_num;
                queue[rear] = input;
            }
        }

        static Node q_poll() {
            if (q_size == 0) {
//                System.out.println("queue is empty");
                return null;
            } else {
                q_size--;
                front = (front + 1) % node_num;
                return queue[front];
            }
        }

        static Node[] queue;
        static int[][] g_map;
        static int n;
        static boolean[][] visited;
        static int[] dr = {-1, 1, 0, 0};
        static int[] dc = {0, 0, -1, 1};

        void bfs_init(int map_size, int map[][]) {
            n = map_size;
            g_map = new int[n][n];
            queue = new Node[n * n];
            visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    g_map[i][j] = map[i][j];
                }
            }
        }

        int bfs(int x1, int y1, int x2, int y2) {
            visited = new boolean[n][n];
            queue = new Node[n * n];

            q_add(new Node(y1 - 1, x1 - 1, 0));
//            System.out.println(front + " " + rear);

            while (front != rear) {
                Node curr = q_poll();
                if (curr == null) {
                    continue;
                }
//                System.out.println(curr);
//                System.out.println(curr.row + " " + curr.col + " " + curr.dist);
                visited[curr.row][curr.col] = true;

                if (curr.row == y2 - 1 && curr.col == x2 - 1) {
                    return curr.dist;
                }

                for (int i = 0; i < 4; i++) {
                    int mr = curr.row + dr[i];
                    int mc = curr.col + dc[i];

                    if (mr >= 0 && mr < n && mc >= 0 && mc < n) {
                        if (!visited[mr][mc] && g_map[mr][mc] == 0) {
                            visited[mr][mc] = true;
                            Node temp = new Node(mr, mc, curr.dist + 1);
//                            System.out.println(temp);
                            q_add(temp);
                        }
                    }
                }
            }

            return -1;
        }
    }

    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    private static int cmd_bfs() throws Exception {

        int score = 100;
        int N, M, x1, y1, x2, y2, dist, ans;
        int[][] map = new int[10][10];
        String str;
        StringTokenizer st;

        System.setIn(new FileInputStream("src/swea_graph/bfs_sample_input.txt"));


        br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        N = Integer.parseInt(str);

        for (int i = 0; i < N; i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        usersolution.bfs_init(N, map);

        str = br.readLine();
        M = Integer.parseInt(str);

        for (int i = 1; i <= M; i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            ans = Integer.parseInt(st.nextToken());

            dist = usersolution.bfs(x1, y1, x2, y2);
//            System.out.println(dist);

            if (dist != ans) {
                score = 0;
            }
        }
        return score;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("#total score : " + cmd_bfs());
    }
}