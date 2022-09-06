package swea_heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;

public class Solution {

    static class Heap {
        int max_size;
        static int idx;
        static int[] heap_arr;

        Heap(int max_val) {
            this.max_size = max_val;
            this.idx = 0;
            heap_arr = new int[max_size + 1];
        }

        static void push(int val) {
            heap_arr[++idx] = val;
            int parent = idx / 2;

            for (int i = idx; parent != 0 && heap_arr[parent] > heap_arr[i]; ) {
                int temp = heap_arr[parent];
                heap_arr[parent] = heap_arr[i];
                heap_arr[i] = parent;
                i = parent;
                parent = i / 2;
            }
        }

        static int poll() {     //잘못됨
            int result = heap_arr[1];
            heap_arr[1] = heap_arr[idx];
            int parent = idx / 2;

            for (int i = idx; parent != 0 && heap_arr[parent] > heap_arr[i]; ) {
                int temp = heap_arr[parent];
                heap_arr[parent] = heap_arr[i];
                heap_arr[i] = parent;
                i = parent;
                parent = i / 2;
            }
            idx--;
            return result;
        }

    }

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("src/swea_heap/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        Scanner sc = new Scanner(System.in);
        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int order_cnt = Integer.parseInt(br.readLine());
            StringTokenizer st;
            Heap heap = new Heap(order_cnt);
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < order_cnt; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                if (st.countTokens() == 2) {
                    st.nextToken();
                    int num = Integer.parseInt(st.nextToken());
                    heap.push(num);
                } else {
                    int val = heap.poll();
                    if (val == 0) {
                        sb.append(-1 + " ");
                    } else {
                        sb.append(val + " ");
                    }
                }
            }

            System.out.println("#" + test_case + " " + sb);
        }
    }
}
