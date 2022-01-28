package programmers42587;

import java.util.*;

public class Solution {

    static class Status {
        int num;
        boolean valid;

        Status(int num, boolean valid) {
            this.num = num;
            this.valid = valid;
        }
    }

    static Queue<Status> q = new LinkedList<>();
    static ArrayList<Integer> priority_val;

    public static void main(String[] args) {

        System.out.println(solution(new int[]{2, 1, 3, 2}, 2));

    }

    static int solution(int[] priorities, int location) {


        priority_val = new ArrayList<>();

        for(int i = 0; i < priorities.length; i++) {
            priority_val.add(priorities[i]);

            if(location == i) {
                q.add(new Status(priorities[i], true));
            } else {
                q.add(new Status(priorities[i], false));
            }

        }

        Collections.sort(priority_val);

        int answer = checking_print(location);

        return answer;
    }

    static int checking_print(int objective) {
        int cnt = 0;
        int idx = priority_val.size() - 1;
        int priority = priority_val.get(idx);
        while(!q.isEmpty()) {
            Status curr = q.poll();

            if(curr.valid && curr.num == priority) {
                return ++cnt;
            }

            if(curr.num >= priority) {
                cnt++;
                if(idx != 0) {
                    idx--;
                    priority = priority_val.get(idx);
                } else {
                    priority = priority;
                }

                continue;
            } else {
                q.add(curr);
            }
        }

        return 0;
    }
}
