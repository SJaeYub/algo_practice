package swea1257;

import java.util.*;
import java.io.FileInputStream;


public class Solution {

    public static class Node {
        boolean end;
        HashMap<Character, Node> child;

        public Node() {
            end = false;
            child = new HashMap<>();
        }
    }

    public static class Trie {
        Node root = new Node();

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
    }

    static LinkedList<String> list;
    static HashMap<String, Integer> hMap;
    static Trie trie;
    static boolean[] visited;

    public static void main(String args[]) throws Exception {

//        System.setIn(new FileInputStream("src/swea1257/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();


        for (int test_case = 1; test_case <= T; test_case++) {
            int target = sc.nextInt();
            String str = sc.next();
            trie = new Trie();
            list = new LinkedList<>();
            hMap = new HashMap<>();

            for (int i = 0; i < str.length(); i++) {

                for (int j = i + 1; j <= str.length(); j++) {
                    String subStr = str.substring(i, j);
                    if (hMap.getOrDefault(subStr, 0) == 0) {
                        hMap.put(subStr, 1);
                        list.add(subStr);
                    }
                }
            }

            Collections.sort(list);

            if (list.size() > target-1) {
                System.out.println("#" + test_case + " " + list.get(target-1));
            } else {
                System.out.println("#" + test_case + " none");
            }
        }
    }

    static void dfs(int idx, String str, StringBuilder sb) {

        for (int i = idx; i < str.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                StringBuilder sb_temp = new StringBuilder();
                sb_temp.append(sb);
                sb_temp.append(str.charAt(i));
                dfs(idx + 1, str, sb_temp);
                visited[i] = false;
            }
        }

        System.out.println(sb);

    }
}