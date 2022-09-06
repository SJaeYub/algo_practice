package swea_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;


public class Solution_inOrder {

    static class Tree {

        static class Node {
            int index;
            char data;
            Node left;
            Node right;

            Node(int idx) {
                this.index = idx;
                this.data = ' ';
                left = null;
                right = null;
            }
        }

        static Node root;

        static void add(int idx, char val, int leftIdx, int rightIdx) {
            if (root == null) {
                root = new Node(idx);
                root.data = val;
                if (leftIdx != 0) {
                    root.left = new Node(leftIdx);
                }
                if (rightIdx != 0) {
                    root.right = new Node(rightIdx);
                }
            } else {
                Node result = search(root, idx);
//                System.out.println(idx + " " + result.index);
                if (result.index != 0) {
                    result.data = val;
                    if (leftIdx != 0) {
                        result.left = new Node(leftIdx);
                    }
                    if (rightIdx != 0) {
                        result.right = new Node(rightIdx);
                    }
                }
            }
        }

        static Node search(Node temp, int idx) {
            Node result;
            if (temp.index == idx) {
                result = temp;
                return result;
            } else {
                if (temp.left != null) {
                    Node left = search(temp.left, idx);
                    if (left.index == idx) {
                        return left;
                    }
                }
                if (temp.right != null) {
                    Node right = search(temp.right, idx);
                    if (right.index == idx) {
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

        System.setIn(new FileInputStream("src/swea_tree/input.txt"));

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = 10;
        ;

        for (int test_case = 1; test_case <= T; test_case++) {
            Tree tree = new Tree();
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken());
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int idx = Integer.parseInt(st.nextToken());
                char data = st.nextToken().charAt(0);
                int left = 0;
                int right = 0;

                if (st.countTokens() == 2) {
                    left = Integer.parseInt(st.nextToken());
                    right = Integer.parseInt(st.nextToken());
                } else if (st.countTokens() == 1) {
                    left = Integer.parseInt(st.nextToken());
                }

                tree.add(idx, data, left, right);
            }
            System.out.print("#" + test_case + " ");
            tree.inOrder(tree.root);
            System.out.println();
        }
    }
}