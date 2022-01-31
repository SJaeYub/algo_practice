package programmers12922;

public class Solution {

    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    static String solution(int n) {
        String answer = "";

        String watermelon = "수박";
        String water = "수";

        StringBuilder sb = new StringBuilder();

        if(n % 2 == 0) {
            for(int i = 0; i < n/2; i++) {
                sb.append(watermelon);
            }
        } else {
            for(int i = 0; i < n/2; i++) {
                sb.append(watermelon);
            }
            sb.append(water);
        }


        answer = sb.toString();
        return answer;
    }
}
