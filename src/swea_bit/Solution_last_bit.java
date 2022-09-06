package swea_bit;

import java.util.Scanner;
import java.io.FileInputStream;

public class Solution_last_bit {

    static int n,m;

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("src/swea_bit/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
//            long beforeTime = System.currentTimeMillis();
            n = sc.nextInt();
            m = sc.nextInt();

            String ans = "";
            String m_binary_str = Integer.toBinaryString(m);
            boolean flag;
            System.out.println(m_binary_str);

            if (m_binary_str.length() < n) {
                flag = false;
            } else {
                flag = true;
                for (int i = m_binary_str.length() - n; i < m_binary_str.length(); i++) {
                    if ((m_binary_str.charAt(i) | '0') == '0') {
                        flag = false;
                        break;
                    }
                }
            }

            if (!flag) {
                ans = "OFF";
            } else {
                ans = "ON";
            }
            System.out.println("#" + test_case + " " + ans);

//            long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
//            long secDiffTime = (afterTime - beforeTime) / 1000; //두 시간에 차 계산
//            System.out.println("시간차이(m) : "+secDiffTime);

        }
    }
}