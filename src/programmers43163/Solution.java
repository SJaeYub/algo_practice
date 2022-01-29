package programmers43163;

import java.util.*;

public class Solution {

    static class Word {
        String str;
        int cnt;

        Word(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }
    }

    static Queue<Word> q = new LinkedList<>();
    static boolean[] visited;
    static ArrayList<Character> arr_list = new ArrayList<>();


    //    "hit"	"cog"	["hot", "dot", "dog", "lot", "log", "cog"]	4
    public static void main(String[] args) {
        System.out.println(solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
    }

    static int solution(String begin, String target, String[] words) {
        int answer = 0;

        visited = new boolean[words.length];

        for (int i = 0; i < words.length; i++) {
            if (!words[i].contains(target)) {
                answer = 0;
            }
        }


        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (!arr_list.contains(words[i].charAt(j))) {
                    arr_list.add(words[i].charAt(j));
                } else {
                    continue;
                }
            }
        }


        q.add(new Word(begin, 0));

        answer = bfs(target, words);

        return answer;
    }

    static String backtracking(String begin, String target, String word) {
        String tmp_begin = begin;
        char[] char_tmp = new char[begin.length()];

        for (int i = 0; i < begin.length(); i++) {
            char_tmp[i] = begin.charAt(i);
        }

        for (int i = 0; i < char_tmp.length; i++) {
            for (int j = 0; j < arr_list.size(); j++) {
                char tmp_char = char_tmp[i];

                char_tmp[i] = arr_list.get(j);
                String tmp = new String(char_tmp);
                if (tmp.equals(word) && !tmp.equals(tmp_begin)) {
                    // System.out.println(tmp);
                    return tmp;
                }
                char_tmp[i] = tmp_char;
            }
        }
        return "";
    }


    static int bfs(String target, String[] words) {
        while (!q.isEmpty()) {
            Word curr = q.poll();

            if (curr.str.equals(target)) {
                return curr.cnt;
            }

            for (int l = 0; l < words.length; l++) {
                String tmp = backtracking(curr.str, target, words[l]);
                if (!visited[l] && !tmp.equals("")) {
                    visited[l] = true;
                    q.add(new Word(tmp, curr.cnt + 1));
                }
            }


        }

        return 0;
    }
}