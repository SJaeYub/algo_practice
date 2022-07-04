package baek2374;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static Stack<Long> stack;
    static int n;
    static long max_val = 0, ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            long new_input = Long.parseLong(br.readLine());
            max_val = Math.max(new_input, max_val);

            if (stack.isEmpty()) {
                stack.push(new_input);
            } else {
                if ((stack.peek() > new_input)) {
                    stack.pop();
                    stack.push(new_input);
                } else if ((stack.peek() < new_input)) {
                    long tmp = stack.pop();
                    ans += new_input - tmp;
                    stack.push(new_input);
                }
            }
        }

        while (!stack.isEmpty()) {
            ans += max_val - stack.pop();
        }

        System.out.println(ans);
    }
}
