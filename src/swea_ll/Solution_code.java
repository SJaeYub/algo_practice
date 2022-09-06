package swea_ll;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;

public class Solution_code {

    static LinkedList<Integer> list;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea_ll/input.txt"));

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            list = new LinkedList<>();
            int codeLength_origin = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            while (st.hasMoreTokens()) {
                list.add(Integer.parseInt(st.nextToken()));
//                System.out.println(Integer.parseInt(st.nextToken()));
            }

            int order_cnt = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            String order = "";
            int cnt = 0;
            LinkedList<Integer> param = new LinkedList<>();
            int params = 0;
            int params_num = 0;
            while (st.hasMoreTokens()) {
                String tmp = st.nextToken();
                if (tmp.compareTo("I") == 0 || tmp.compareTo("D") == 0 || tmp.compareTo("A") == 0) {
                    if (cnt != 0) {
                        method(order, param, params, params_num);
                    }
                    order = tmp;
                    cnt = 0;
                    param = new LinkedList<>();
                } else {
                    if (order.compareTo("I") == 0) {
                        if (cnt == 1) {
                            params = Integer.parseInt(tmp);
                        } else if (cnt == 2) {
                            params_num = Integer.parseInt(tmp);
                        } else {
                            param.add(Integer.parseInt(tmp));
                        }
                    }
                    if (order.compareTo("D") == 0) {
                        if (cnt == 1) {
                            params = Integer.parseInt(tmp);
                        } else if (cnt == 2) {
                            params_num = Integer.parseInt(tmp);
                        }
                    }
                    if (order.compareTo("A") == 0) {
                        if (cnt == 1) {
                            params_num = Integer.parseInt(tmp);
                        } else {
                            param.add(Integer.parseInt(tmp));
                        }
                    }
                }
                cnt++;
            }

            StringBuilder sb = new StringBuilder();
            int cnt2 = 0;
            for (int integer : list) {
                cnt2++;
                sb.append(integer + " ");
                if (cnt2 == 10) {
                    break;
                }
            }

            System.out.println("#" + test_case + " " + sb);
        }
    }

    static void method(String order, LinkedList<Integer> list_tmp, int pos, int nums) {
        if (order.compareTo("I") == 0) {
            int cnt = 0;
            int position = pos;
//            int add_num = nums;
            list.addAll(position, list_tmp);
        }
        if (order.compareTo("D") == 0) {
            int position = pos;
            int del_num = nums;
            int cnt = 0;

            for (int integer : list) {
                if (cnt == position) {
                    list.remove();
                    del_num--;
                }
                if (del_num == 0) {
                    break;
                }
            }
        }
        if (order.compareTo("A") == 0) {
            list.addAll(list_tmp);
        }
    }
}