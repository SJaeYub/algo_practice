package swea1256;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileInputStream;

public class Solution {

    static class Node {
        boolean end;
        HashMap<Character, Node> child;


        public Node() {
            child = new HashMap<>();
            end = false;
        }

    }

    public static class Trie {
        Node root = new Node();

        public Trie() {

        }

        public void insert(String str) {
            Node node = root;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                node.child.putIfAbsent(c, new Node());
                node = node.child.get(c);

                if (i == str.length() - 1) {
                    node.end = true;
                    return;
                }
            }
        }

        public boolean search(String str) {
            Node node = root;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (node.child.containsKey(c)) {
                    node = node.child.get(c);
                } else {
                    return false;
                }

                if (i == str.length() - 1) {
                    if (!node.end) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void delete(String str) {
            Node node = root;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (i < str.length() - 1) {
                    node = node.child.get(c);
                } else {
                    Node child = node.child.get(c);

                    if (child.child.isEmpty()) {
                        node.child.remove(c);
                    } else {
                        child.end = false;
                    }
                }
            }
        }
    }

    static Trie trie;
    static boolean[] visited;
    static String str;
    static LinkedList<String> list = new LinkedList<>();

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("src/swea1256/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            trie = new Trie();

            int target = sc.nextInt();
            str = sc.next();

            visited = new boolean[str.length()];
            list = new LinkedList<>();

            for (int i = str.length(); i >= 0; i--) {
//                trie.insert(str.substring(0, i));
                list.add(str.substring(i, str.length()));
            }

            Collections.sort(list);

            System.out.println("#" + test_case + " " + list.get(target));
        }
    }


}