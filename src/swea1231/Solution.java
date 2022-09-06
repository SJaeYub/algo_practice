package swea1231;

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
                left = null;
                right = null;
            }
        }

        static Node root;

        static void add(int idx, char data, int leftidx, int rightidx) {
            if (root == null) {
                Node node = new Node(idx);
                root = node;
                root.data = data;

                if (leftidx != 0) {
                    root.left = new Node(leftidx);
                }
                if (rightidx != 0) {
                    root.right = new Node(rightidx);
                }
            } else {
                Node result = search(root, idx);

                if (result.idx != 0) {
                    result.data = data;
                    if (leftidx != 0) {
                        result.left = new Node(leftidx);
                    }
                    if (rightidx != 0) {
                        result.right = new Node(rightidx);
                    }
                }
            }
        }

        static Node search(Node temp, int idx) {
            Node result;
            if (temp.idx == idx) {
                result = temp;
                return result;
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

        static void inOrder(Node root) {
            if (root.left != null) {
                inOrder(root.left);
            }
            System.out.print(root.data);
            if (root.right != null) {
                inOrder(root.right);
            }
        }
    }


    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea1231/input.txt"));

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            Tree tree = new Tree();
            int size = Integer.parseInt(br.readLine());

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

            System.out.print("#" + test_case + " ");
            tree.inOrder(tree.root);
            System.out.println();
        }
    }
}