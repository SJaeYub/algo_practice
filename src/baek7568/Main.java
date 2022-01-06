package baek7568;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static class Physical {
        int weight;
        int height;
        int cnt;

        Physical(int w, int h, int c) {
            this.weight = w;
            this.height = h;
            this.cnt = c;
        }
    }

    static int n;
    static ArrayList<Physical> arr_list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            arr_list.add(new Physical(sc.nextInt(), sc.nextInt(), 0));
        }

        check(0, n);

        for (Physical curr : arr_list) {
            System.out.print(curr.cnt + 1 + " ");
        }
    }

    static void check(int start_idx, int end_idx) {
        if (start_idx == end_idx - 1) {
            return;
        }

        for (int i = start_idx; i < end_idx - 1; i++) {
            if (arr_list.get(start_idx).weight < arr_list.get(i + 1).weight) {
                if (arr_list.get(start_idx).height < arr_list.get(i + 1).height) {
                    arr_list.get(start_idx).cnt++;
                }
            } else if (arr_list.get(start_idx).weight > arr_list.get(i + 1).weight) {
                if (arr_list.get(start_idx).height > arr_list.get(i + 1).height) {
                    arr_list.get(i + 1).cnt++;
                }
            }
        }

        check(start_idx + 1, end_idx);
        return;
    }
}
