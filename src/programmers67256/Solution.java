package programmers67256;

import java.util.*;

public class Solution {

    static class Position {
        int row;
        int col;
        int dist;

        Position(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    static int[][] keyPad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -2}};
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int[] hand_position = new int[2];            //현재 손의 위치

    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";
        String answer = solution(numbers, hand);
        System.out.println(answer);
    }

    static String solution(int[] numbers, String hand) {
        String answer = "";

        hand_position[0] = -2;
        hand_position[1] = -1;

        StringBuilder sb = new StringBuilder();

        char[] ans_arr = new char[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            char finger = select_hand(num, hand_position[0], hand_position[1], hand);

            sb.append(finger);
            if (finger == 'L') {
                hand_position[0] = num;
            } else {
                hand_position[1] = num;
            }
        }
        answer = sb.toString();

        return answer;
    }

    static char select_hand(int num, int curr_left, int curr_right, String hand) {
        char result = ' ';
        HashSet<Integer> left_hand = new HashSet<>();
        left_hand.add(1);
        left_hand.add(4);
        left_hand.add(7);

        HashSet<Integer> right_hand = new HashSet<>();
        right_hand.add(3);
        right_hand.add(6);
        right_hand.add(9);

        HashSet<Integer> middle_col = new HashSet<>();
        middle_col.add(2);
        middle_col.add(5);
        middle_col.add(8);
        middle_col.add(0);

        if (right_hand.contains(num)) {
            result = 'R';
        } else if (left_hand.contains(num)) {
            result = 'L';
        } else {
            char finger = bfs(num, curr_left, curr_right, hand);
            result = finger;
        }

        return result;
    }

    static char bfs(int num, int curr_left, int curr_right, String hand) {
        char result = ' ';
        Queue<Position> q = new LinkedList<>();
        boolean[][] visited = new boolean[4][3];
        boolean flag_r = false, flag_l = false;
        int[] map = search_mapping(num);
        int[] arr = new int[2];
        q.add(new Position(map[0], map[1], 0));

        while (!q.isEmpty()) {
            Position curr = q.poll();

            if (!visited[curr.row][curr.col]) {
                visited[curr.row][curr.col] = true;
            }

            if (keyPad[curr.row][curr.col] == curr_left) {
                arr[0] = curr.dist;
                flag_l = true;
            } else if (keyPad[curr.row][curr.col] == curr_right) {
                arr[1] = curr.dist;
                flag_r = true;
            }

            if (flag_r && flag_l) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int mr = dr[i] + curr.row;
                int mc = dc[i] + curr.col;

                if (mr >= 0 && mr < 4 && mc >= 0 && mc < 3) {
                    if (!visited[mr][mc]) {
                        visited[mr][mc] = true;
                        q.add(new Position(mr, mc, curr.dist + 1));
                    }
                }
            }
        }

        if (arr[0] < arr[1]) {
            result = 'L';
        } else if (arr[0] > arr[1]) {
            result = 'R';
        } else {
            if (hand.equals("left")) {
                result = 'L';
            } else {
                result = 'R';
            }
        }

        return result;
    }

    static int[] search_mapping(int num) {
        int[] result = new int[2];
        if (num == 1) {
            result[0] = 0;
            result[1] = 0;
        } else if (num == 2) {
            result[0] = 0;
            result[1] = 1;
        } else if (num == 3) {
            result[0] = 0;
            result[1] = 2;
        } else if (num == 4) {
            result[0] = 1;
            result[1] = 0;
        } else if (num == 5) {
            result[0] = 1;
            result[1] = 1;
        } else if (num == 6) {
            result[0] = 1;
            result[1] = 2;
        } else if (num == 7) {
            result[0] = 2;
            result[1] = 0;
        } else if (num == 8) {
            result[0] = 2;
            result[1] = 1;
        } else if (num == 9) {
            result[0] = 2;
            result[1] = 2;
        } else if (num == 0) {
            result[0] = 3;
            result[1] = 1;
        }

        return result;
    }
}