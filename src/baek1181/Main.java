package baek1181;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    static int n = 0;
    static ArrayList<String> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();

            if(!arr.contains(tmp)) {
                arr.add(tmp);
            }
        }

        Collections.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() < s2.length()) {
                    return -1;
                } else if (s1.length() == s2.length()) {
                    return s1.compareTo(s2);
                } else {
                    return 1;
                }
            }
        });

        for (String s : arr) {
            System.out.println(s);
        }
    }
}
