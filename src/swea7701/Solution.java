package swea7701;

import java.io.FileInputStream;
import java.util.*;

public class Solution {

    static class Dictionary {
        String str;
        int length;

        Dictionary(String str, int length) {
            this.str = str;
            this.length = length;
        }

    }

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("src/swea7701/s_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            HashSet<String> hSet = new HashSet<>();
            LinkedList<Dictionary> list = new LinkedList<Dictionary>();

            for (int i = 0; i < n; i++) {
                String str = sc.next();
                if (!hSet.contains(str)) {
                    hSet.add(str);
                    list.add(new Dictionary(str, str.length()));
                }
            }

            Collections.sort(list, new Comparator<Dictionary>() {
                @Override
                public int compare(Dictionary d1, Dictionary d2) {
                    if (d1.length < d2.length) {
                        return -1;
                    } else if (d1.length == d2.length) {
                        return d1.str.compareTo(d2.str);
                    } else {
                        return 1;
                    }
                }
            });

            System.out.println("#" + test_case);
            for (Dictionary dictionary : list) {
                System.out.println(dictionary.str);
            }
        }
    }
}