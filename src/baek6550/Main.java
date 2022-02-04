package baek6550;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str;

        while ((str = br.readLine()) != null) {
            st = new StringTokenizer(str, " ");
            Queue<Character> q = new LinkedList<>();

            String s1 = st.nextToken();
            String s2 = st.nextToken();

            for (int i = 0; i < s1.length(); i++) {
                q.add(s1.charAt(i));
            }

            for (int i = 0; i < s2.length(); i++) {
                if (s2.charAt(i) == q.peek()) {
                    q.poll();
                    if (q.isEmpty()) {
                        System.out.println("Yes");
                        break;
                    }
                }
            }
            if (!q.isEmpty()) {
                System.out.println("No");
            }
        }
    }
}
