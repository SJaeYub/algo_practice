package baek16987;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static class Eggs {
        int durability;
        int weight;
        boolean status;

        Eggs(int dura, int wei) {
            this.durability = dura;
            this.weight = wei;
            this.status = false;
        }
    }

    static ArrayList<Eggs> arr_list = new ArrayList<>();
    static boolean[] visited;
    static int crash_num = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            arr_list.add(new Eggs(sc.nextInt(), sc.nextInt()));
        }


        dfs(n, 0, 0);

        System.out.println(crash_num);

    }

    static void dfs(int n, int idx, int ans) {
        if (idx == n) {
            return;
        }

        Eggs curr = arr_list.get(idx);

        if(curr.status) {           //지금 든게 깨졌으면 넘기고
            dfs(n, idx + 1, ans);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (i == idx) {             //같은거 넘기고
                continue;
            }

            Eggs next = arr_list.get(i);

            boolean tmp_curr = curr.status;
            boolean tmp_next = next.status;

            if(next.status) {        //이미 깨진거 넘기고
                continue;
            }

            boolean[] after_eggs = crash(curr, next);       //충돌하고 난 결과 상태를 저장
            boolean[] flag = new boolean[2];
            if (after_eggs[0]) {
                curr.status = after_eggs[0];
                flag[0] = true;
                ans++;
            }
            if(after_eggs[1]) {
                next.status = after_eggs[1];
                flag[1] = true;
                ans++;
            }
            crash_num = Math.max(crash_num, ans);
            dfs(n, idx + 1, ans);         //다음 순서의 계란을 집어든다

            curr.status = tmp_curr;                     //원상복구
            next.status = tmp_next;
            for (int j = 0; j < 2; j++) {
                if(flag[j]) {
                    ans--;
                }
            }

            reroll(curr, next);
        }
    }

    static boolean[] crash(Eggs e1, Eggs e2) {
        boolean[] results = new boolean[2];
        e1.durability -= e2.weight;
        e2.durability -= e1.weight;
        if (e1.durability <= 0) {
            results[0] = true;
        }
        if (e2.durability <= 0) {
            results[1] = true;
        }
        return results;
    }

    static void reroll(Eggs e1, Eggs e2) {
        e1.durability += e2.weight;
        e2.durability += e1.weight;
    }
}
