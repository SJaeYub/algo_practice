package swea_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;


public class Solution_operation {

    static char[] arr;

    static class Tree {

        static class Node {
            int idx;
            String data;
            Node left;
            Node right;

            Node(int idx) {
                this.idx = idx;
                this.data = " ";
                left = null;
                right = null;
            }
        }

        static Node root = null;

        //삽입
        static void add(int idx, String data, int leftIdx, int rightIdx) {
            if (root == null) {
                root = new Node(idx);
                root.data = data;
                if (leftIdx != 0) {
                    root.left = new Node(leftIdx);
                }
                if (rightIdx != 0) {
                    root.right = new Node(rightIdx);
                }
            } else {
//                System.out.println(idx);
                Node result = search(root, idx);
                result.data = data;
                if (leftIdx != 0) {
                    result.left = new Node(leftIdx);
                }
                if (rightIdx != 0) {
                    result.right = new Node(rightIdx);
                }
            }
        }

        //탐색
        static Node search(Node temp, int idx) {
            Node result = new Node(0);
            if (temp.idx == idx) {
                result = temp;
                return result;
            }
            if (temp.left != null) {
                Node left = search(temp.left, idx);
                if (left.idx == idx) {
                    return left;
                }
            }
            if (temp.right != null) {
                Node right = search(temp.right, idx);
                if (right.idx == idx) {
                    return right;
                }
            }
            return result;
        }

        //순회
        static double inOrder(Node start) {
            String data = start.data;
            double result = 0;
            double left = 0;
            double right = 0;


            if (start.left.left != null) {
                left = inOrder(start.left);
            } else {
                left = Double.parseDouble(start.left.data);
            }
            if (start.right.left != null) {
                right = inOrder(start.right);
            } else {
                right = Double.parseDouble(start.right.data);
            }

            if (data.equals("+")) {
                result = (left + right);
            } else if (data.equals("-")) {
                result = (left - right);
            } else if (data.equals("*")) {
                result = (left * right);
            } else if (data.equals("/")) {
                result = (left / right);
            }
            return result;
        }
    }

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("src/swea_tree/input_operation.txt"));

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            Tree tree = new Tree();

            arr = new char[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                String val = st.nextToken();
                int left = 0;
                int right = 0;
                if (st.countTokens() == 2) {
                    left = Integer.parseInt(st.nextToken());
                    right = Integer.parseInt(st.nextToken());
                } else if (st.countTokens() == 1) {
                    left = Integer.parseInt(st.nextToken());
                }

                tree.add(idx, val, left, right);
            }
            double ans = tree.inOrder(tree.root);
            System.out.println("#" + test_case + " " + (int)ans);
        }
    }
}