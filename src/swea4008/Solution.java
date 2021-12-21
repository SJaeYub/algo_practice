package swea4008;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    static int[] arr;
    static int[] num;
    static int N;
    static int min;
    static int max;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            arr = new int[4];
            String[] input = br.readLine().split(" ");
            for(int i=0; i<4; i++)
                arr[i] = Integer.parseInt(input[i]);

            num = new int[N];
            input = br.readLine().split(" ");
            for(int i=0; i<N; i++)
                num[i] = Integer.parseInt(input[i]);

            dfs(num[0], 1);
            System.out.println("#"+test_case+" "+(max-min));
        }
    }

    public static void dfs(int ans, int idx) {
        if(idx==N) {
            min = Math.min(min, ans);
            max = Math.max(max, ans);
            return;
        }

        for(int i=0; i<4; i++) {
            if(arr[i]!=0) {
                arr[i]--;
                if(i==0)
                    dfs(ans+num[idx], idx+1);

                else if(i==1)
                    dfs(ans-num[idx], idx+1);

                else if(i==2)
                    dfs(ans*num[idx], idx+1);

                else if(i==3)
                    dfs(ans/num[idx], idx+1);

                arr[i]++;
            }
        }
    }
}