package programmers92334;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String[] id_list = new String[]{"muzi", "frodo", "apeach", "neo"};
        String[] report = new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;
        int[] solution = solution(id_list, report, k);

        for (int i : solution) {
            System.out.println(i);
        }
    }

    static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        HashMap<String, Integer> ans_map = new HashMap<>();
        HashMap<String, Integer> mem_index = new HashMap<>();

        for (int i = 0; i < id_list.length; i++) {
            map.put(id_list[i], new ArrayList<>());
        }

        for (int i = 0; i < id_list.length; i++) {
            mem_index.put(id_list[i], i);
        }

        for (int i = 0; i < id_list.length; i++) {
            ans_map.put(id_list[i], 0);
        }

        for (int i = 0; i < report.length; i++) {
            String[] from_to = report[i].split(" ");

            String from = from_to[0];
            String to = from_to[1];

            if (!map.get(to).contains(from)) {
                map.get(to).add(from);
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            if (map.get(id_list[i]).size() >= k) {
                for (int j = 0; j < map.get(id_list[i]).size(); j++) {
                    String target = map.get(id_list[i]).get(j);

                    int val = ans_map.get(target);
                    val += 1;

                    ans_map.put(target, val);
                }
            }
        }

        answer = new int[id_list.length];
        for (String curr : ans_map.keySet()) {
            int position = mem_index.get(curr);
            answer[position] = ans_map.get(curr);
        }

        return answer;
    }
}
