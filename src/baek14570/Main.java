package baek14570;

import java.util.*;

public class Main {

    static ArrayList<Integer>[] arr_list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();

        arr_list = new ArrayList[Long.valueOf(n).intValue() + 1];
        for (int i = 1; i < n + 1; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 1; i < n + 1; i++) {
            int temp1 = sc.nextInt();
            int temp2 = sc.nextInt();
            if (temp1 != -1) {
                arr_list[i].add(temp1);
            }
            if (temp2 != -1) {
                arr_list[i].add(temp2);
            }
        }

        long target = sc.nextLong();
        dfs(1, target);


    }

    private static void dfs(int node, long num) {
        if (arr_list[node].size() == 0) {
            System.out.println(node);
            return;
        } else if (arr_list[node].size() == 1) {
            dfs(arr_list[node].get(0), num);
        } else {
            for (int i = 0; i < 2; i++) {
                if (num % 2 == 0) {     //짝수
                    dfs(arr_list[node].get(i), num / 2 + 1);
                } else {                    //홀수
                    dfs(arr_list[node].get(i), num / 2);
                }
                break;
            }
        }
    }
}
