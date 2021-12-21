package baek14888;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    //주어진 연산자로 만들수있는 경우의 수를 다 만들고 그다음에 숫자를 넣어서 연산

    static int n;
    static int[] arr;
    static char[] operator;
    static int[] numOfOperator = new int[4];
    static int sumOfOperator = 0;
    static char[] vacuum;
    static boolean[] visited;
    static int answer = 0;
    static int start_index;
    static int min_ans = Integer.MAX_VALUE;
    static int max_ans = Integer.MIN_VALUE;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < 4; i++) {
            numOfOperator[i] = sc.nextInt();
            sumOfOperator += numOfOperator[i];

        }
        operator = new char[sumOfOperator];
        visited = new boolean[sumOfOperator];
        vacuum = new char[sumOfOperator];

        int oper_index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < numOfOperator[i]; j++) {
                if (i == 0) {
                    operator[oper_index] = '+';
                    oper_index++;
                }
                if (i == 1) {
                    operator[oper_index] = '-';
                    oper_index++;
                }
                if (i == 2) {
                    operator[oper_index] = '*';
                    oper_index++;
                }
                if (i == 3) {
                    operator[oper_index] = '/';
                    oper_index++;
                }
            }
        }

        start_index = 0;
        dfs(arr[0], 0, start_index);

        System.out.println(max_ans);
        System.out.println(min_ans);
    }

    static void dfs(int value, int cnt, int index) {
        if (cnt == sumOfOperator) {
            int temp = arr[0];
            for (int i = 0; i < vacuum.length; i++) {
                if (vacuum[i] == '+') {
                    temp += arr[i + 1];
                }
                if (vacuum[i] == '-') {
                    temp -= arr[i + 1];
                }
                if (vacuum[i] == '*') {
                    temp *= arr[i + 1];
                }
                if (vacuum[i] == '/') {
                    if (temp < 0) {
                        temp = Math.abs(temp);
                        temp /= arr[i + 1];
                        temp = 0 - temp;
                    } else {
                        temp /= arr[i + 1];
                    }
                }
            }
            max_ans = Math.max(max_ans, temp);
            min_ans = Math.min(min_ans, temp);
//            System.out.println("temp = " + temp);
//            System.out.println(Arrays.toString(vacuum));
//            System.out.println("-----------------");
            return;
        }

        for (int i = 0; i < sumOfOperator; i++) {
            if (!visited[i]) {
                vacuum[cnt] = operator[i];
                visited[i] = true;
                dfs(value, cnt + 1, index+1);
                visited[i] = false;
            }
        }

    }
}
