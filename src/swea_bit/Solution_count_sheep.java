package swea_bit;

import java.util.Scanner;
import java.io.FileInputStream;

public class Solution_count_sheep {

    static int n;
    static int[] code;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea_bit/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            long beforeTime = System.currentTimeMillis();

            n = sc.nextInt();
//            System.out.println(n);

            code = new int[10];
            int cnt = 1;
            int next_num;
            while (true) {
                next_num = cnt * n;
                int length = (int) (Math.log10(next_num) + 1);
                int[] tmp_arr = new int[10];

                while (next_num > 0) {
                    tmp_arr[next_num % 10] = 1;
                    next_num /= 10;
                }

                boolean flag = true;
                for (int i = 0; i < 10; i++) {
                    code[i] = code[i] | tmp_arr[i];
                    if (code[i] == 0) {
                        flag = false;
                    }
                }

//                System.out.println(cnt);

                if (flag) {
                    next_num = cnt * n;
                    break;
                }
                cnt++;
            }

            long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
            long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
            System.out.println("#" + test_case + " " + next_num);
            System.out.println("시간차이(m) : "+secDiffTime);
        }
    }
}