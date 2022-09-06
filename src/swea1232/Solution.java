package swea1232;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {

    static class Tree {

        static class Node {
            int idx;
            String data;
            Node left;
            Node right;

            Node(int idx) {
                this.idx = idx;
                this.data = "";
                this.left = null;
                this.right = null;
            }
        }

        static Node root;

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
                Node next = search(root, idx);
                if (next.idx != 0) {
                    next.data = data;
                    if (leftIdx != 0) {
                        next.left = new Node(leftIdx);
                    }
                    if (rightIdx != 0) {
                        next.right = new Node(rightIdx);
                    }
                }
            }
        }

        static Node search(Node temp, int idx) {
            if (temp.idx == idx) {
                return temp;
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

            return new Node(0);
        }

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
        System.setIn(new FileInputStream("src/swea1232/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            Tree tree = new Tree();
            int size = Integer.parseInt(br.readLine());

            for (int i = 0; i < size; i++) {
                String[] strLine = br.readLine().split(" ");

                int idx = Integer.parseInt(strLine[0]);
                String data = strLine[1];
                int left = 0;
                int right = 0;

                if (strLine.length == 4) {
                    left = Integer.parseInt(strLine[2]);
                    right = Integer.parseInt(strLine[3]);
                } else if (strLine.length == 3) {
                    left = Integer.parseInt(strLine[2]);
                }

                tree.add(idx, data, left, right);
            }

            double ans = tree.inOrder(tree.root);
            System.out.println("#" + test_case + " " + (int) ans);
        }
    }
}