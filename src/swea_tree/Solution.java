package swea_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;


public class Solution {

    static class Node {
        int num;
        int left;
        int right;
        int parent;

        Node(int n) {
            this.num = n;
            this.left = 0;
            this.right = 0;
            this.parent = 0;
        }
    }

    static Node[] arr_list;
    static boolean[] visited;
    static int ans_v = 0;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea_tree/input_4.txt"));

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int v = Integer.parseInt(st.nextToken());
            arr_list = new Node[v+1];
            visited = new boolean[v + 1];
            ans_v = 1;

            for (int i = 1; i <= v; i++) {
                arr_list[i] = new Node(i);
            }

            int e = Integer.parseInt(st.nextToken());

            int first_child = Integer.parseInt(st.nextToken());
            int second_child = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");

            for (int i = 0; i < e; i++) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                if (arr_list[parent].left == 0) {
                    arr_list[parent].left = child;
                    arr_list[child].parent = parent;
                } else {
                    arr_list[parent].right = child;
                    arr_list[child].parent = parent;
                }
            }

            dfs_check_ans(first_child);
            dfs_check_ans(second_child);

            int ans_sum = dfs_sum(ans_v);

            System.out.println("#" + test_case + " " + ans_v + " " + ans_sum);
        }
    }

    static int dfs_sum(int next) {
        int result = 1;
        if (arr_list[next].left == 0 && arr_list[next].right == 0) {
            return 1;
        }

        if (arr_list[next].left != 0) {
            result += dfs_sum(arr_list[next].left);
        }
        if (arr_list[next].right != 0) {
            result += dfs_sum(arr_list[next].right);
        }

        return result;
    }

    static void dfs_check_ans(int next) {
        if (next == 1) {
            return;
        }

        visited[next] = true;

        if (!visited[arr_list[next].parent]) {
            dfs_check_ans(arr_list[next].parent);
        } else {
            ans_v = arr_list[next].parent;
        }
        return;
    }
}