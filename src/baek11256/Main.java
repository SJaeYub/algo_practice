package baek11256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int j, n;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < t; test_case++) {
            st = new StringTokenizer(br.readLine(), " ");
            j = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            int[] box = new int[n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                box[i] = x * y;
            }

            Arrays.sort(box);

            int answer = 0;

            for (int i = box.length - 1; j > 0; i--) {
                j -= box[i];
                answer++;
            }

            System.out.println(answer);
        }
    }
}
