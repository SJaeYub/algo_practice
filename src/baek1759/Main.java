package baek1759;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int l, c;
    static ArrayList<Character> arr_list = new ArrayList<>();
    static char[] ans;
    static char[] arr;
    static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        l = sc.nextInt();
        c = sc.nextInt();

        arr = new char[c];
        ans = new char[l];
        visited = new boolean[c];

        for (int i = 0; i < c; i++) {
            arr[i] = sc.next().charAt(0);
        }

        Arrays.sort(arr);

        dfs(0, 0);

    }

    private static void dfs(int start, int idx) {

        if (idx == l) {
            arr_list.clear();
            int vowels_cnt = 0;
            int consonants_cnt = 0;

            for (int i = 0; i < c; i++) {
                if (visited[i]) {
                    if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                        vowels_cnt++;
                    } else {
                        consonants_cnt++;
                    }
                    if (arr[i] != ' ') {
                        arr_list.add(arr[i]);
                    }
                }
            }

            if (vowels_cnt >= 1 && consonants_cnt >= 2) {

                for (Character character : arr_list) {
                    System.out.print(character);
                }
                System.out.println();
            }

            return;
        }

        for (int i = start; i < c; i++) {
            visited[i] = true;
            dfs(i + 1, idx + 1);
            visited[i] = false;

        }
    }


}
