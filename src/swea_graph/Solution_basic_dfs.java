package swea_graph;

import swea12304.Solution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_basic_dfs {


    static class UserSolution {

        static class Node {
            public int data;
            public Node prev;
            public Node next;

            public Node(int data) {
                this.data = data;
                this.prev = null;
                this.next = null;
            }
        }

        static class LinkedList {
            private final static int MAX_NODE = 10000;

            private Node[] node = new Node[MAX_NODE];
            private int nodeCnt = 0;
            private Node head;
            private Node tail;

            public Node getNode(int data) {
                node[nodeCnt] = new Node(data);
                return node[nodeCnt++];
            }

            public void init() {
                head = getNode(0);
                tail = getNode(0);
                head.next = tail;
                tail.prev = head;
            }

            public void addNode2Head(int data) {
                Node newNode = getNode(data);
                Node prev_addr = head.next;
                newNode.next = prev_addr;
                prev_addr.prev = newNode;
                newNode.prev = head;
                head.next = newNode;
//            System.out.println(newNode.data);
            }

            public void addNode2Tail(int data) {
                Node newNode = getNode(data);
                Node prev = tail.prev;
                newNode.prev = prev;
                prev.next = newNode;
                tail.prev = newNode;
                newNode.next = tail;
//            System.out.println(newNode.data);
            }

            public void addNode2Num(int data, int num) {
                Node newNode = getNode(data);
                Node nextNode = head;
                int cnt = 1;

                while (nextNode.next != null) {
                    if (cnt == num) {
                        break;
                    }
                    nextNode = nextNode.next;
                    cnt++;
                }

                if (nextNode.prev != null && nextNode.next != null) {
                    newNode.next = nextNode.next;
                    nextNode.next.prev = newNode;
                    nextNode.next = newNode;
                    newNode.prev = nextNode;

                }
                if (nextNode.prev == null) {
                    nextNode.next.prev = newNode;
                    newNode.next = nextNode.next;
                    head.next = newNode;
                    newNode.prev = head;
                }
                if (nextNode.next == null) {
                    nextNode.prev.next = newNode;
                    newNode.prev = nextNode.prev;
                    tail.prev = newNode;
                    newNode.next = tail;
                }
            }

            public int findNode(int idx) {
                int result = 0;
                Node nextNode = head;

                int cnt = 0;

                while (nextNode.next != null) {
                    result++;
                    nextNode = nextNode.next;
                    cnt++;
                    if (idx == cnt) {
                        break;
                    }
                }

                return nextNode.data;
            }

            public void removeNode(int data) {
                Node nextNode = head;

                while (nextNode.next != null) {
                    nextNode = nextNode.next;

                    if (nextNode.data == data) {
                        if (nextNode.prev != null && nextNode.next != null) {
                            nextNode.prev.next = nextNode.next;
                            nextNode.next.prev = nextNode.prev;
                        }
                        if (nextNode.prev == null) {
                            nextNode.next.prev = head;
                            head.next = nextNode.next;
                        }
                        if (nextNode.next == null) {
                            nextNode.prev.next = tail;
                            tail.prev = nextNode.prev;
                        }
                    }
                }
            }

            public int getList() {
                int result = 0;
                Node nextNode = head;
                while (nextNode.next != null) {
                    nextNode = nextNode.next;
                    if (nextNode.data == 0) {
                        break;
                    }
//                    output[result] = nextNode.data;
                    result++;
                }
                return result;
            }

            public int[] getList(int[] output) {
                int result = 0;
                Node nextNode = head;
                while (nextNode.next != null) {
                    nextNode = nextNode.next;
                    if (nextNode.data == 0) {
                        break;
                    }
                    output[result] = nextNode.data;
                    result++;
                }
                return output;
            }

            public int getReversedList(int[] output) {
                int result = 0;
                Node nextNode = tail;
                while (nextNode.prev != null) {
                    nextNode = nextNode.prev;
                    if (nextNode.data == 0) {
                        break;
                    }
                    output[result] = nextNode.data;
                    result++;
                }

                return result;
            }

        }

                static LinkedList[] list;
//        static LinkedList<Integer>[] list;
        static boolean[] visited;
        static boolean flag = false;
        static int global_result = 0;

        public void dfs_init(int N, int[][] path) {
            list = new LinkedList[100];

            for (int i = 0; i <= 99; i++) {
                list[i] = new LinkedList();
                list[i].init();
            }

            for (int i = 0; i < N; i++) {
                int parent = path[i][0];
                int child = path[i][1];

                list[parent].addNode2Tail(child);
//                list[child].addNode2Tail(parent);

            }

        }

        public int dfs(int N) {
            visited = new boolean[100];
            global_result = -1;
            flag = false;
            int result = dfs_temp(N, visited, N);

            if(!flag) {
//                System.out.println(N + " " + "-1 check");
                global_result = -1;
            }

            return global_result;
        }

        public int dfs_temp(int num, boolean[] visit_tmp, int origin_num) {
            int result = 0;
//            System.out.println(num);
            if (num > origin_num) {
                global_result = num;
                flag = true;
//                System.out.println(origin_num + " " + global_result + " " + "check");
                return result;
            }

            int[] vals = UserSolution.list[num].getList(new int[UserSolution.list[num].getList()]);

            for (int i = 0; i < vals.length; i++) {
                if (!visited[vals[i]] && !flag) {
                    visited[vals[i]] = true;
                    dfs_temp(vals[i], visited, origin_num);
                    visited[vals[i]] = false;
                }
            }

            return -1;
        }
    }

    private final static int MAX_N = 40;
    private final static int MAX_K = 98;
    private final static int MIN_N = 2;
    private final static int MAX_CHILD = 5;

    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    private static long seed = 12345;

    private static int[][] path = new int[MAX_N][2];
    private static int[] p = new int[MAX_K + 2];
    private static int[] c = new int[MAX_K + 2];

    public static long pseudo_rand(int max) {
        seed = (seed * 1103515245 + 12345) & 0x7fffffffL;
        return seed % max;
    }

    public static void makeTree(int N) {

        boolean[] check = new boolean[MAX_K + 2];

        for (int i = 1; i < MAX_K + 2; i++) {
            p[i] = c[i] = -1;
        }
        c[(int) (pseudo_rand(MAX_K + 1) + 1)] = 0;

        for (int i = 0; i < N; i++) {
            int pi = (int) (pseudo_rand(MAX_K + 1) + 1);
            while (c[pi] < 0 || c[pi] >= MAX_CHILD) {
                pi++;
                if (pi == MAX_K + 2) pi = 1;
            }

            int ci = (int) (pseudo_rand(MAX_K + 1) + 1);
            while (c[ci] >= 0) {
                ci++;
                if (ci == MAX_K + 2) ci = 1;
            }

            p[ci] = pi;
            c[pi]++;
            c[ci] = 0;
        }

        for (int i = 0; i < N; i++) {
            int e = (int) (pseudo_rand(MAX_K + 1) + 1);
            while (check[e] == true || c[e] < 0 || p[e] == -1) {
                e++;
                if (e == MAX_K + 2) e = 1;
            }
            check[e] = true;
            path[i][0] = p[e];
            path[i][1] = e;
        }
    }

    public static void main(String[] args) throws Exception {
        int TC, N, Q, K, ans, ret;
        int totalScore = 0, score;
        String str;
        StringTokenizer st;
        System.setIn(new FileInputStream("src/swea_graph/dfs_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        TC = Integer.parseInt(str);

        for (int tc = 1; tc <= TC; tc++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");

            N = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());
            seed = Long.parseLong(st.nextToken());

            makeTree(N - 1);
            usersolution.dfs_init(N, path);

            score = 100;
            for (int i = 1; i <= Q; i++) {
                str = br.readLine();
                st = new StringTokenizer(str, " ");

                K = Integer.parseInt(st.nextToken());
                ans = Integer.parseInt(st.nextToken());

                ret = usersolution.dfs(K);

                if (ret != ans) score = 0;
            }

            System.out.println("#" + tc + " : " + score);
            totalScore += score;
        }

        System.out.println("#totalScore score : " + totalScore / TC);
    }
}
