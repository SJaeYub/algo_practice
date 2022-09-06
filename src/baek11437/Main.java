package baek11437;

import java.util.ArrayList;
import java.util.Scanner;

/*
LCA
1) 각 타깃의 depth를 맞춘다
2) 각 타깃의 부모가 일치할 때 까지 부모 노드로 이동
3) 같으면 LCA 출력
 */
public class Main {

    static class Node {
        int idx;
        int parent;
        int depth;

        Node(int idx) {
            this.idx = idx;
            this.parent = 0;
            this.depth = 0;
        }
    }

    static ArrayList<Node>[] arr;
    static int[] arr_depth;
    static int[] arr_parent;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        arr = new ArrayList[n + 1];
        arr_depth = new int[n + 1];
        arr_parent = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int parent = sc.nextInt();
            int child = sc.nextInt();

            Node child_node = new Node(child);
            Node parent_node = new Node(parent);

            arr[parent].add(child_node);
            arr[child].add(parent_node);
        }

        dfs(1, 1, 0);

        int cnt = sc.nextInt();

//        for (int i = 1; i <= n; i++) {
//            System.out.print(i + " " + arr_parent[i]);
//            System.out.println();
//        }

        while (cnt != 0) {
            int target1 = sc.nextInt();
            int target2 = sc.nextInt();

            while (true) {
//                System.out.println(target1 + " " + target2);
                if (arr_depth[target1] > arr_depth[target2]) {
                    target1 = arr_parent[target1];
                } else if (arr_depth[target1] < arr_depth[target2]) {
                    target2 = arr_parent[target2];
                } else {            //깊이가 같을때
                    if (arr_parent[target1] == arr_parent[target2]) {
                        if(target1 == target2) {
                            System.out.println(target1);
                            break;
                        } else {
                            System.out.println(arr_parent[target1]);
                            break;
                        }
                    } else {
                        target1 = arr_parent[target1];
                        target2 = arr_parent[target2];
                    }
                }
            }
            cnt--;
        }

        return;
    }

    static void dfs(int idx, int depth, int parent) {
        visited[idx] = true;
        arr_depth[idx] = depth;
        arr_parent[idx] = parent;

        for (Node next : arr[idx]) {
            if (!visited[next.idx]) {
                visited[next.idx] = true;
                dfs(next.idx, depth + 1, idx);
                visited[next.idx] = false;
            }
        }

        return;
    }

}
