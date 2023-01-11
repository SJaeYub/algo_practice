package baek20551;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

//미완성
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        for (int i = 0; i < m; i++) {
            int comTarget = sc.nextInt();
            boolean flag = false;

            int[] clone = arr.clone();

            int idx = Arrays.binarySearch(clone, comTarget);

            for(Integer curr : arr) {
                System.out.print(curr + " ");
            }

            if(idx >= 0) {
                System.out.println(idx);
            } else {
                System.out.println(-1);
            }
        }

    }

    static int binarySearch(int[] arr, int low, int high, int target) {
        int result = -1;

        int mid;
        if(low <= high) {
            mid = (low + high)/2;

            if(arr[mid] == target) {
                if(low == mid) {

                }
                return mid;
            }else if(target < arr[mid]) {
                return binarySearch(arr, low, mid - 1, target);
            } else {
                return binarySearch(arr, mid + 1, high, target);
            }
        }

        return result;
    }
}
