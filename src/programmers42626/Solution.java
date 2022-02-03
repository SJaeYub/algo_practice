package programmers42626;

import java.util.*;

public class Solution {

    static class Food implements Comparable<Food> {
        int sco;

        Food(int sco) {
            this.sco = sco;
        }

        @Override
        public int compareTo(Food f1) {
            return Integer.compare(this.sco, f1.sco);
        }
    }

    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        int answer = solution(scoville, K);
        System.out.println(answer);
    }

    static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Food> pq = new PriorityQueue<>();
        for(int i = 0; i < scoville.length; i++) {
            pq.add(new Food(scoville[i]));
        }

        answer = mix(pq, K);

        return answer;
    }

    static int mix(PriorityQueue<Food> pq, int K) {
        int result = 0;
        int max_tmp = 0;
        while(pq.size() >= 2) {
            Food min1 = pq.poll();

            if(min1.sco >= K) {
                return result;
            }

            Food min2 = pq.poll();

            int new_food = min1.sco + (min2.sco * 2);
            max_tmp = new_food;
            result++;
            pq.add(new Food(new_food));
        }

        if(max_tmp >= K) {
            return result;
        } else {
            return -1;
        }
    }
}