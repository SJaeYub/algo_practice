package swea1926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static char[] samyookgu = {'3', '6', '9'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] ans = new String[n + 1];

        for (int i = 1; i <= n; i++) {
            String temp = Integer.toString(i);
            char[] tmp_arr = new char[temp.length()];

            String s = makeChar(temp.toCharArray());

            ans[i] = s;
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(ans[i] + " ");
        }
    }

    private static String makeChar(char[] tmp) {
        char[] tmp_arr = new char[tmp.length];
        String result = new String();
        boolean flag = false;
        int cnt = 0;


        for (int j = 0; j < tmp.length; j++) {
            for (int l = 0; l < 3; l++) {
                if (samyookgu[l] == tmp[j]) {
                    cnt++;
                    flag = true;
                    break;
                }
            }
        }

        if (flag) {
            char[] tmp_arr2 = new char[cnt];
            for (int i = 0; i < cnt; i++) {
                tmp_arr2[i] = '-';
            }


            result = new String(tmp_arr2);

        } else {
            result = new String(tmp);;
        }

        return result;
    }
}
