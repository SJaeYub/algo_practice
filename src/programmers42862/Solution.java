package programmers42862;

import java.util.*;

public class Solution {
    static int[] student;

    public static void main(String[] args) {
        int n = 5;
        int[] lost = {2, 4};
        int[] reserve = {1, 3, 5};
        System.out.println(solution(n, lost, reserve));
    }

    static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        student = new int[n + 1];
        Arrays.fill(student, 1);

        for(int curr : lost) {
            student[curr]--;
        }

        for(int curr : reserve) {
            student[curr]++;
        }

        Arrays.sort(lost);

        for(int curr : lost) {
            if(check_rent(curr, n)) {              //양 옆에 빌려줄 수 있는 학생이 있다면 true
                rent(curr, n);
            }
        }

        for(int i = 1; i <= n; i++) {
            if(student[i] != 0) {
                answer++;
            }
        }

        return answer;
    }

    static void rent(int lost_stu, int n) {
        int friend1 = lost_stu - 1;
        int friend2 = lost_stu + 1;

        if(friend1 < 0) {
            return;
        }
        if(friend2 > n) {
            return;
        }

        if(student[friend1] >= 2 ) {
            student[lost_stu]++;
            student[friend1]--;
        } else if(student[friend2] >= 2) {
            student[lost_stu]++;
            student[friend2]--;
        }

        return;
    }

    static boolean check_rent(int lost_stu, int n) {
        int friend1 = lost_stu - 1;
        int friend2 = lost_stu + 1;

        if(friend1 < 0) {
            return false;
        }
        if(friend2 > n) {
            return false;
        }

        if(student[friend1] >= 2 || student[friend2] >= 2) {
            return true;
        }

        return false;
    }
}
