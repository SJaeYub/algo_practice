package baek10828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String cmd = st.nextToken();
            if (cmd.equals("push")) {
                stack.push(Integer.parseInt(st.nextToken()));
            } else if (cmd.equals("pop")) {
                if (stack.size() >= 1) {
                    System.out.println(stack.pop());
                } else {
                    System.out.println(-1);
                }
            } else if (cmd.equals("size")) {
                System.out.println(stack.size());
            } else if (cmd.equals("empty")) {
                if (stack.empty()) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else if (cmd.equals("top")) {
                if (stack.size() >= 1) {
                    System.out.println(stack.peek());
                } else {
                    System.out.println(-1);
                }
            }
        }
    }
}
