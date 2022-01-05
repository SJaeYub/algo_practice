package programmers42746;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        int[] numbers = {6, 10, 2};
        System.out.println(solution(numbers));
    }

    static String solution(int[] numbers) {
        String[] temp = new String[numbers.length];
        boolean flag = false;

        for(int i = 0; i < numbers.length; i++) {
            temp[i] = String.valueOf(numbers[i]);           //String으로 변환후 배열에 저장
            if(!temp[i].equals("0")) {
                flag= true;
            }
        }

        Arrays.sort(temp, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2);
            }

        });

        StringBuilder sb = new StringBuilder();

        for(String curr : temp) {
            sb.append(curr);
        }

        if(!flag) {
            sb.setLength(0);
            sb.append("0");
        }

        String answer = sb.toString();
        return answer;
    }
}
