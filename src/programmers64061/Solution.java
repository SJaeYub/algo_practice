package programmers64061;

import java.util.*;

public class Solution {

    static Stack<Integer> stack = new Stack<>();
    static int[] arr;

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3},
                {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        int solution = solution(board, moves);
        System.out.println(solution);
    }

    static int solution(int[][] board, int[] moves) {
        int answer = 0;

        arr = new int[board.length + 1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] != 0) {
                    arr[i + 1] = j;
                    break;
                }
            }
        }

        for (int i = 0; i < moves.length; i++) {
            int doll = pick_up(board, moves[i]);
            if (doll == 0) {
                continue;
            }

            if (check_boom(doll)) {
                boom();
                answer += 2;
            } else {
                stack.push(doll);
            }
        }

        return answer;
    }

    static int boom() {
        stack.pop();
        return 1;
    }

    static boolean check_boom(int doll) {
        boolean result = false;

        if (!stack.isEmpty()) {
            int top_doll = stack.peek();

            if (top_doll == doll) {
                result = true;
            }
        }

        return result;
    }

    static int pick_up(int[][] board, int num) {
        int result = 0;

        result = board[arr[num]][num - 1];
        board[arr[num]][num - 1] = 0;
        if (arr[num] < board.length - 1) {
            arr[num]++;
        }

        return result;
    }
}