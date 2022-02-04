package programmers42888;

import java.util.*;

public class Solution {

    static StringTokenizer st;
    static ArrayList<String> arr_list = new ArrayList<>();

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] answers = solution(record);
        for (String answer : answers) {
            System.out.println(answer);
        }
    }

    static String[] solution(String[] record) {
        String[] answer;

        HashMap<String, String> map = new HashMap<>();
        for(int i = 0; i < record.length; i++) {
            st = new StringTokenizer(record[i], " ");
            String cmd = st.nextToken();
            String id = st.nextToken();

            if(cmd.equals("Enter")) {
                arr_list.add(id + " 님이 들어왔습니다.");
                check_name(map, id, st.nextToken());
            } else if (cmd.equals("Leave")) {
                arr_list.add(id + " 님이 나갔습니다.");
            } else if (cmd.equals("Change")) {
                map.replace(id, st.nextToken());
            }
        }

        answer = new String[arr_list.size()];

        for(int i = 0; i < arr_list.size(); i++) {
            st = new StringTokenizer(arr_list.get(i), " ");
            String id = st.nextToken();
            String new_name = map.get(id);
            answer[i] = new_name + st.nextToken() + " " + st.nextToken();
        }



        return answer;
    }

    static void check_name(HashMap<String, String> map, String id, String name) {
        if(!map.containsKey(id)) {
            map.put(id, name);
        } else {
            map.replace(id, name);
        }
    }
}