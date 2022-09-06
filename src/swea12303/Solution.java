package swea12303;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class UserSolution {

        private final static int MAX_NODE = 10000;

        private Node[] node = new Node[MAX_NODE];
        private int nodeCnt = 0;
        private Node head;

        public Node getNode(int data) {
            node[nodeCnt] = new Node(data);
            return node[nodeCnt++];
        }

        public void init() {
            head = new Node(0);
        }

        public void addNode2Head(int data) {
            Node newNode = getNode(data);
            newNode.next = head.next;
            head.next = newNode;
        }

        public void addNode2Tail(int data) {
            Node newNode = getNode(data);
            Node nextNode = head;
            while (nextNode.next != null) {
                nextNode = nextNode.next;
            }

            nextNode.next = newNode;
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
//                System.out.println(nextNode.data);
            }

            newNode.next = nextNode.next;
            nextNode.next = newNode;
        }

        public void removeNode(int data) {
            Node nextNode = head;
//            System.out.println("test : " + nextNode.data);

            while (nextNode.next != null) {
                nextNode = nextNode.next;
                if (nextNode.next != null && nextNode.next.data == data) {
                    nextNode.next = nextNode.next.next;
                }

                if (nextNode.data == data) {
                    head.next = nextNode.next;
                }
            }

        }

        public int getList(int[] output) {
            int cnt = 0;
            Node nextNode = head;
            while (nextNode.next != null) {
                nextNode = nextNode.next;
                output[cnt] = nextNode.data;
                cnt++;
            }
            return cnt;
        }
    }


    private final static int MAX_NODE = 10000;
    private final static int ADD_HEAD = 1;
    private final static int ADD_TAIL = 2;
    private final static int ADD_NUM = 3;
    private final static int REMOVE = 4;
    private final static int PRINT = 5;
    private final static int END = 99;

    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    private static void run() throws Exception {

        int cmd, data, num, ret;
        int[] output = new int[MAX_NODE];
        String str;
        StringTokenizer st;

        while (true) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            cmd = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case ADD_HEAD:
                    data = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Head(data);
                    break;

                case ADD_TAIL:
                    data = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Tail(data);
                    break;

                case ADD_NUM:
                    data = Integer.parseInt(st.nextToken());
                    num = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Num(data, num);
                    break;

                case REMOVE:
                    data = Integer.parseInt(st.nextToken());
                    usersolution.removeNode(data);
                    break;

                case PRINT:
                    ret = usersolution.getList(output);
                    for (int i = 0; i < ret; i++) {
                        System.out.print(output[i] + " ");
                    }
                    System.out.println();
                    break;

                case END:
                    return;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int TC;
        System.setIn(new java.io.FileInputStream("src/swea12303/sll_input.txt"));

        br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        TC = Integer.parseInt(str);

        for (int tc = 1; tc <= TC; tc++) {
            System.out.println("#" + tc);
            usersolution.init();
            run();
            System.out.println();
        }
    }

}