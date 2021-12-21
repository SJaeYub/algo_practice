package baek1182;

import java.util.Scanner;

public class Main {
    static int n,s;
    static int[] arr;
    static int answer;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        s=sc.nextInt();
        arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=sc.nextInt();
        }

        subset(0,0,0);
        System.out.println(answer);

    }
    private static void subset(int idx,int sum,int cnt) {
        if(idx==n) {
            if(cnt!=0 && sum==s) {
                answer+=1;
            }
            return;
        }

        subset(idx+1,sum+arr[idx],cnt+1);

        subset(idx+1,sum,cnt);
    }
}