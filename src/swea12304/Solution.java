package swea12304;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

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

    public static class UserSolution {

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

        public int findNode(int data) {
            int result = 0;
            Node nextNode = head;

            while (nextNode.next != null) {
                result++;
                nextNode = nextNode.next;

                if (nextNode.data == data) {
                    break;
                }
            }

            return result;
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

        public int getList(int[] output) {
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
            return result;
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


    private final static int MAX_NODE = 10000;
    private final static int ADD_HEAD = 1;
    private final static int ADD_TAIL = 2;
    private final static int ADD_NUM = 3;
    private final static int FIND = 4;
    private final static int REMOVE = 5;
    private final static int PRINT = 6;
    private final static int PRINT_REVERSE = 7;
    private final static int END = 99;

    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    public static void run() throws Exception {
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

                case FIND:
                    data = Integer.parseInt(st.nextToken());
                    num = usersolution.findNode(data);
                    System.out.println(num);
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

                case PRINT_REVERSE:
                    ret = usersolution.getReversedList(output);
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
        System.setIn(new java.io.FileInputStream("src/swea12304/dll_input.txt"));

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