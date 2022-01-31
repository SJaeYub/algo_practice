package programmers42889;

import java.util.*;

public class Solution {

    static class Stages implements Comparable<Stages> {
        int stage;
        double falls;

        Stages(int stage, double falls) {
            this.stage = stage;
            this.falls = falls;
        }

        @Override
        public int compareTo(Stages s1) {
            if(this.falls > s1.falls) {
                return -1;
            } else if(this.falls == s1.falls) {
                if(this.stage <= s1.stage) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[] stages = {2, 1, 2, 4, 2};
        int[] answer = solution(n, stages);

        for (int i : answer) {
            System.out.println(i + " ");
        }
    }

    static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        double[] tmp = new double[N+1];
        int[][] arr = new int[N+1][2];
        ArrayList<Stages> arr_list = new ArrayList<>();

        for(int i = 1; i < arr.length; i++) {
            for(int j = 0; j < stages.length; j++) {
                if(stages[j] > i) {
                    arr[i][1]++;
                }
                if(stages[j] == i) {
                    arr[i][1]++;
                    arr[i][0]++;
                }
            }

            // System.out.println(i + " " + arr[i][0] + " " + (double) arr[i][1]);
            double dval = 0.0;
            if(arr[i][0] == 0) {
                dval = 0.0;
            } else {
                dval = arr[i][0] / (double) arr[i][1];
            }
            arr_list.add(new Stages(i, dval));

        }

        Collections.sort(arr_list);

        int idx = 0;
        for(Stages curr : arr_list) {
            answer[idx] = curr.stage;
            // System.out.println(curr.falls);
            idx++;
        }

        return answer;
    }
}