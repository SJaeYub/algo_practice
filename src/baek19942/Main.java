package baek19942;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int[][] ingredients;
    static StringTokenizer st;
    static int[] limitIngre;
    static boolean[] visited;
    static ArrayList<Integer> arr = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;
    static ArrayList<Integer> ans_list = new ArrayList<>();
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        ingredients = new int[n][5];
        limitIngre = new int[4];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            limitIngre[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++) {
                ingredients[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        if (flag) {
            System.out.println(ans);
            Collections.sort(ans_list);
            for (int curr : ans_list) {
                System.out.print(curr + 1 + " ");
            }
            return;
        } else {
            System.out.println(-1);
            return;
        }

    }

    static void dfs(int idx, int start) {
        if (idx == n) {
            int cost = compute(arr);
            if (cost < ans) {            //현재 식재료 조합 비용이 지금까지 비용보다 작다면
                ans_list.clear();           //기존을 초기화하고
                ans_list.addAll(arr);
                ans = cost;                 //비용을 갱신한다.
            }
            return;
        }

        for (int i = start; i < n; i++) {               //재귀 시작 값을 전의 값으로 넣으면 뒤로 돌아가지 않는다!
            if (!visited[i]) {
                visited[i] = true;
                arr.add(i);           //조합한 식재료 배열
                int cost = compute(arr);
                if (cost < ans) {            //현재 식재료 조합 비용이 지금까지 비용보다 작다면
                    ans_list.clear();           //기존을 초기화하고
                    ans_list.addAll(arr);
                    ans = cost;                 //비용을 갱신한다.
                }
                dfs(i, idx + 1);
                arr.remove(arr.size() - 1);
                visited[i] = false;
            }
        }

    }

    static int compute(ArrayList<Integer> tmp) {
        int result = 0;
        int sum_0 = 0;
        int sum_1 = 0;
        int sum_2 = 0;
        int sum_3 = 0;
        for (int curr : tmp) {
            sum_0 += ingredients[curr][0];
            sum_1 += ingredients[curr][1];
            sum_2 += ingredients[curr][2];
            sum_3 += ingredients[curr][3];
            result += ingredients[curr][4];
        }

        if (sum_0 >= limitIngre[0] && sum_1 >= limitIngre[1] &&
                sum_2 >= limitIngre[2] && sum_3 >= limitIngre[3]) {
            flag = true;
            return result;
        } else {
            return Integer.MAX_VALUE;
        }
    }
}
