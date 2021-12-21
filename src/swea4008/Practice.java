package swea4008;

import java.util.*;

public class Practice {

    static int n;
    static int[] arr;
    static int[] num;
    static int max_ans;
    static int min_ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();
            max_ans = Integer.MIN_VALUE;
            min_ans = Integer.MAX_VALUE;
            arr = new int[4];


            for (int i = 0; i < 4; i++) {
                arr[i] = sc.nextInt();
            }


            num = new int[n];

            for (int i = 0; i < n; i++) {
                num[i] = sc.nextInt();
            }


            dfs(num[0], 1);

            System.out.println("#" + test_case + " " + (max_ans - min_ans));
        }
    }

    static void dfs(int value, int index) {
        if (index == n) {                           //인자로 들어온 수를 다 연산함
            max_ans = Math.max(max_ans, value);
            min_ans = Math.min(min_ans, value);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (arr[i] != 0) {                //사용할 연산자가 남아있는 경우
                arr[i]--;                     //연산자를 하나 사용했으므로
                if (i == 0) {
                    dfs(value + num[index], index + 1);
                } else if (i == 1) {
                    dfs(value - num[index], index + 1);
                } else if (i == 2) {
                    dfs(value * num[index], index + 1);
                } else if (i == 3) {
                    dfs(value / num[index], index + 1);
                }
                arr[i]++;                 //return 후 돌아왔을때 사용가능한 연산가 개수가 하나 늘어나므로
            }
        }
    }
}
