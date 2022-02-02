package programmers17681;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};
        String[] solution = solution(n, arr1, arr2);
        for (String s : solution) {
            System.out.println(s);
        }
    }

    static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for(int i = 0; i < n; i++) {
            String binaryCode1 = Integer.toBinaryString(arr1[i]);
            String binaryCode2 = Integer.toBinaryString(arr2[i]);
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();

            if(binaryCode1.length() != n) {
                int cnt = 0;
                while(cnt + binaryCode1.length() != n) {
                    sb1.append('0');
                    cnt++;
                }
            }

            if(binaryCode2.length() != n) {
                int cnt = 0;
                while(cnt + binaryCode2.length() != n) {
                    sb2.append('0');
                    cnt++;
                }
            }

            sb1.append(binaryCode1);
            sb2.append(binaryCode2);
            String result = add_two_code(sb1.toString(), sb2.toString(), n);

            answer[i] = result;

        }

        return answer;
    }

    static String add_two_code(String sb1, String sb2, int n) {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < n; i++) {
            String tmp = "";
            if(sb1.charAt(i) != sb2.charAt(i)) {
                tmp = "#";
            } else {
                if(sb1.charAt(i) == '1') {
                    tmp = "#";
                } else {
                    tmp = " ";
                }
            }
            result.append(tmp);
        }

        return result.toString();
    }


}