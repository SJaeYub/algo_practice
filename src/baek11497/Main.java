package baek11497;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int test_case = 0; test_case < t; test_case++) {
            int n = sc.nextInt();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            for (int i = 0; i < n; i++) {
                pq.add(sc.nextInt());
            }

            int[] arr = new int[n];

            int ans = Integer.MIN_VALUE;
            int mid = n/2;
            arr[mid] = pq.poll();
            int mp = mid + 1, mn = mid - 1;
            while (!pq.isEmpty()) {
                if (mp < n) {
                    arr[mp++] = pq.poll();
                }
                if(mn >= 0){
                    arr[mn--] = pq.poll();
                }
            }

            ans = Math.max(Math.abs(arr[0] - arr[n- 1]), ans);
            for (int i = 0; i < n - 1; i++) {
                ans = Math.max(ans, Math.abs(arr[i] - arr[i + 1]));
            }

            System.out.println(ans);
        }
    }
}
