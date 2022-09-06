package swea3998;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {

    static int[] arr, temp;
    static long answer = 0;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea3998/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();

            arr = new int[n + 1];
            temp = new int[n + 1];
            answer = 0;

            for (int i = 1; i <= n; i++) {
                arr[i] = sc.nextInt();
            }

            mergeSort(1, n);
            System.out.println("#" + test_case + " " + answer);
        }
    }

    static void mergeSort(int left, int right) {
        if(left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        merge(left, mid, right);
    }

    static void merge(int left, int mid, int right) {
        int x = left;
        int y = mid + 1;
        int k = left;

        while (x <= mid || y <= right) {
            if (y > right || (x <= mid && arr[x] < arr[y])) {
                temp[k] = arr[x++];
            } else {
                answer += (mid - x + 1);
                temp[k] = arr[y++];
            }
            k++;
        }

        for (int i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }
}