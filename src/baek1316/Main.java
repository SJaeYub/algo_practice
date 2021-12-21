package baek1316;

import java.io.*;
import java.util.*;

public class Main {

    static boolean[] checkBox;
    static boolean flag;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < n; test_case++) {
            flag = false;
            String str = br.readLine();
            int str_length = str.length();
            checkBox = new boolean[200];

            String temp = " ";
            for (int i = 0; i < str_length; i++) {
                String temp2 = str.substring(i, i + 1);
                if (i == 0) {
                    temp = temp2;
                    checkBox[temp.charAt(0) - '0'] = true;
                    continue;
                }
                check(temp, temp2);
                if (flag) {
                    break;
                }
                temp = temp2;
            }
            if(!flag) {
                cnt++;
            }
        }
        bw.write(Integer.toString(cnt));
        bw.flush();
        bw.close();
    }

    private static void check(String oldT, String newT) {
        if (oldT.equals(newT)) {
            return;
        }
        if (!oldT.equals(newT)) {
            if (checkBox[newT.charAt(0) - '0']) {              //이미 방문한 경우
                flag = true;
                return;
            } else {
                checkBox[newT.charAt(0) - '0'] = true;
                return;
            }
        }
    }
}
