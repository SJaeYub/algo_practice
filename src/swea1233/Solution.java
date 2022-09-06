package swea1233;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {

    static class Tree {

        static class Node {
            int idx;
            char data;
            Node left;
            Node right;

            Node(int idx) {
                this.idx = idx;
                this.data = ' ';
                this.left = null;
                this.right = null;
            }
        }

        static Node root;

        static void add(int idx, char data, int leftIdx, int rightIdx) {
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
                Node temp = search(root, idx);

                if (temp.idx != 0) {
                    temp.data = data;

                    if (leftIdx != 0) {
                        temp.left = new Node(leftIdx);
                    }
                    if (rightIdx != 0) {
                        temp.right = new Node(rightIdx);
                    }
                }
            }
        }

        static Node search(Node temp, int idx) {
            if (temp.idx == idx) {
                return temp;
            } else {
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
            }
            return new Node(0);
        }

        static int inOrder(Node temp) {
            if (temp.left != null) {
                inOrder(temp.left);
            }
//            char[] arr = {'+', '-', '*', '/'};
            if (temp.left == null && temp.right == null) {
                if (temp.data == '+') {
                    flag = true;
                    return 0;
                } else if (temp.data == '-') {
                    flag = true;
                    return 0;
                } else if (temp.data == '*') {
                    flag = true;
                    return 0;
                } else if (temp.data == '/') {
                    flag = true;
                    return 0;
                }
            }

            if (temp.right != null) {
                inOrder(temp.right);
            }

            return 1;
        }
    }

    static boolean flag;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea1233/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            Tree tree = new Tree();
            int size = Integer.parseInt(br.readLine());
            flag = false;

            for (int i = 0; i < size; i++) {
                String[] strLine = br.readLine().split(" ");

                int idx = Integer.parseInt(strLine[0]);
                char data = strLine[1].charAt(0);
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

            tree.inOrder(tree.root);

            if (flag) {
                System.out.println("#" + test_case + " " + 0);
            } else {
                System.out.println("#" + test_case + " " + 1);
            }
        }
    }
}