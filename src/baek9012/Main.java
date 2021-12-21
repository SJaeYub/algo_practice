package baek9012;

import java.io.BufferedReader;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < t; test_case++) {
            String testStr = br.readLine();
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < testStr.length(); i++) {
                Character temp = testStr.substring(i, i + 1).charAt(0);
                if (i == 0) {
                    if(temp == ')') {
                        System.out.println("NO");
                        break;
                    } else {
                        stack.add(temp);
                        continue;
                    }

                }
                if(temp == '(') {
                    stack.add(temp);
                }
                if (temp == ')') {
                    if(stack.size() == 0) {
                        System.out.println("NO");
                        break;
                    } else {
                        stack.pop();
                    }
                }

                if(i == testStr.length() - 1) {
                    if(stack.size() > 0) {
                        System.out.println("NO");
                        break;
                    } else {
                        System.out.println("YES");
                    }
                }
            }
        }
    }
}
