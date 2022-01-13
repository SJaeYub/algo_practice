package baek15658;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static char[] operator = {'+', '-', '*', '/'};
    static int[] operator_num = new int[4];
    static int n;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] operand;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        operand = new int[n];

        for (int i = 0; i < n; i++) {
            operand[i] = sc.nextInt();
        }

        for (int i = 0; i < 4; i++) {
            operator_num[i] = sc.nextInt();
        }

        dfs(0, operand[0], new char[n-1]);

        System.out.println(max);
        System.out.println(min);

        return;
    }

    static void dfs(int idx, int temp_operand, char[] temp_arr) {
        if (idx == n - 1) {
            max = Math.max(max, temp_operand);
            min = Math.min(min, temp_operand);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator_num[i] > 0) {
                operator_num[i]--;
                temp_arr[idx] = operator[i];
                int mid = operate(operator[i], temp_operand, operand[idx + 1]);
                dfs(idx + 1, mid, temp_arr);
                operator_num[i]++;
            }
        }
    }

    static int operate(char rator, int curr_operand, int next) {
        int result = 0;

        if (rator == '+') {
            result = curr_operand + next;
        } else if (rator == '-') {
            result = curr_operand - next;
        } else if (rator == '*') {
            result = curr_operand * next;
        } else {
            if (curr_operand < 0) {
                curr_operand *= -1;
                result = curr_operand / next;
                result *= -1;
            } else {
                result = curr_operand / next;
            }
        }

        return result;
    }
}
