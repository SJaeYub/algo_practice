package baek9093;

import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < t; test_case++) {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            Stack<Character> stack = new Stack<>();
            boolean flag = false;

            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == ' ') {
                    if (flag) {
                        sb.append(' ');
                    }
                    flag = true;
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                } else {
                    stack.push(input.charAt(i));
                }
            }

            sb.append(' ');
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }

            if (test_case != t - 1) {
                sb.append(' ');
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }
}
