package baek9081;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static ArrayList<String> arr_list;
    static char[] str_arr;
    static HashSet<String> hSet;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String str = br.readLine();
            arr_list = new ArrayList<>();
            hSet = new HashSet<>();

            str_arr = str.toCharArray();

            char[] arr = new char[str.length()];
            boolean[] visited = new boolean[str.length()];
            for (int j = 0; j < str.length(); j++) {
                dfs(0, str.length(), arr, visited);
            }

            Collections.sort(arr_list);

            boolean flag = false;
            String ans = str;
            for (String curr : arr_list) {
                if(flag) {
                    ans = curr;
                    break;
                }
                if (curr.equals(str)) {
                    flag = true;
                }
            }

            System.out.println(ans);
        }
    }

    static void dfs(int dep, int len, char[] temp, boolean[] visited) {
        if (dep == len) {
//            arr_list.add(new String(temp));
            String temp_str = new String(temp);
            if (!hSet.contains(temp_str)) {
                hSet.add(temp_str);
                arr_list.add(temp_str);
//                System.out.println(new String(temp));
            }
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp[dep] = str_arr[i];
                dfs(dep + 1, len, temp, visited);
                visited[i] = false;
            }
        }
    }
}
