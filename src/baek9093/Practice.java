package baek9093;

import java.util.*;
import java.io.*;

public class Practice {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            int n = Integer.parseInt(br.readLine()); //입력 받을 문장의 수

            for (int i = 0; i < n; i++) {
                Stack<Character> st = new Stack();
                char[] input = br.readLine().toCharArray();
                for (char c : input) {
                    if (c != ' ') {
                        st.add(c);
                    } else {
                        //공백 문자 나오면 문자 출력
                        while (!st.empty()) {
                            bw.write(st.pop());
                        }
                        bw.write(" ");
                    }
                }
                //문장의 마지막 단어 출력
                while (!st.empty()) {
                    bw.write(st.pop());
                }
                bw.write("\n");

            }
            bw.close();
            br.close();
        }catch (IOException e) {
            e.printStackTrace();
        }


    }
}
