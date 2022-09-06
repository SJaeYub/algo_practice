package swea1233;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_valid_operand {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/swea_tree/input_2.txt"));

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            int ans = 1;

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                st.nextToken();

                char val = st.nextToken().charAt(0);
                if (st.hasMoreTokens()) {
                    if (val >= '0' && val <= '9') {
                        if (st.hasMoreTokens()) {
                            ans = 0;
                        }
                    }
                } else {
                    if (val < '0' || val > '9') {
                        ans = 0;
                    }
                }
            }
            System.out.println("#" + test_case + " " + ans);
        }
    }
}