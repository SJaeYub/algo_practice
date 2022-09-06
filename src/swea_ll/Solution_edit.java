package swea_ll;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;


public class Solution_edit {

    static ArrayList<Integer> arr_list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea_ll/sample_input_2.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int l = sc.nextInt();

            arr_list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                arr_list.add(sc.nextInt());
            }

            for (int i = 0; i < m; i++) {
                char order = sc.next().charAt(0);
                if (order != 'D') {
                    method(order, sc.nextInt(), sc.nextInt());
                } else {
                    method(order, sc.nextInt(), 0);
                }
            }

            int result = 0;
            if (arr_list.size() < l) {
                result = -1;
            } else {
                result = arr_list.get(l);
            }
            System.out.println("#" + test_case + " " + result);
        }
    }

    static void method(char order, int param1, int param2) {
        if (order == 'I') {
            arr_list.add(param1, param2);
            return;
        }
        if (order == 'D') {
            arr_list.remove(param1);
            return;
        }
        if (order == 'C') {
            arr_list.remove(param1);
            arr_list.add(param1, param2);
            return;
        }
    }
}