package swea1855;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;

public class Solution {

    static class Node {
        int num;
        //            int dist;
        Node prev;
        Node next;

        Node(int n) {
            this.num = n;
//                this.dist = d;
            prev = null;
            next = null;
        }
    }

    static Node[] node = new Node[100000];
    static int nodeCnt = 0;
    static Node head;
    static Node tail;

    static class LinkedList {

        static Node getNode(int data) {
            node[nodeCnt] = new Node(data);
            return node[nodeCnt++];
        }

        static void linkedList_init() {
            head = getNode(0);
            tail = getNode(0);
            head.next = tail;
            tail.prev = head;
        }

        static void add(int data) {
            Node next = getNode(data);
            Node lastNode = tail.prev;
            next.prev = lastNode;
            lastNode.next = next;
            tail.prev = next;
            next.next = tail;
        }

        static Node get(int idx) {
            Node next = head;
            int cnt = 0;
            while (next.next != null) {
                next = next.next;
                cnt++;

                if (cnt == idx) {
                    return next;
                }
            }
            return null;
        }
    }

    static class Queue_Node {
        int dist;
        int num;

        Queue_Node(int d, int n) {
            this.dist = d;
            this.num = n;
        }
    }

    static int n;
    static LinkedList[] lList;
    static Queue_Node[] queue;
    static int q_size = 0, front = -1, rear = -1, size = 0;
    static boolean[] visited;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea1855/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        Scanner sc = new Scanner(System.in);
        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= 1; test_case++) {
            LinkedList ll = new LinkedList();
//            ll.linkedList_init();

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            q_size = n;
            lList = new LinkedList[n + 1];

            for (int i = 1; i <= n; i++) {
                lList[i] = new LinkedList();
                lList[i].linkedList_init();
            }

            st = new StringTokenizer(br.readLine(), " ");
            int start = 2;
            while (st.hasMoreTokens()) {
                int parent = Integer.parseInt(st.nextToken());
                int child = start;

                lList[parent].add(child);
//                System.out.println(parent + " " + lList[parent].get(2).num);
                start++;

            }

            for (int i = 1; i <= 3; i++) {
                for (int j = 1; lList[i].get(j) != null; j++) {
                    System.out.println(i + " " + lList[i].get(j).num);
                }
            }

//            System.out.println(bfs());
        }
    }

    static int bfs() {
        Queue_Node start = new Queue_Node(0, 1);
        queue = new Queue_Node[n + 1];
        queue_add(start);
        visited = new boolean[n + 1];

        int max_dist = -1;

        while (front != rear) {
            Queue_Node curr = queue_poll();
            if (curr == null) {
                break;
            }
//            System.out.println(curr.num + " " + curr.dist);
            max_dist = Math.max(max_dist, curr.dist);

            visited[curr.num] = true;

            for (int i = 1; lList[curr.num].get(i) != null; i++) {
                Node next = lList[curr.num].get(i);
//                System.out.println(next.num);
                if (!visited[next.num]) {
                    visited[next.num] = true;
                    queue_add(new Queue_Node(curr.dist + 1, next.num));
                }
            }
        }
        return max_dist;
    }

    static void queue_add(Queue_Node input) {
        if (size >= q_size) {
            return;
        } else {
            size++;
            rear = (rear + 1) % q_size;
            queue[rear] = input;
        }
    }

    static Queue_Node queue_poll() {
        if (size == 0) {
            return null;
        } else {
            size--;
            front = (front + 1) % q_size;
            return queue[front];
        }
    }
}