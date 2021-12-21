package baek1946;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int n, t, ans;
    static int[][] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine());

        for (int testNum = 0; testNum < t; testNum++) {
            n = Integer.parseInt(br.readLine());
            score = new int[n][2];
            ans = n;

            for (int i = 0; i < n; i++) {
                String temp = br.readLine();
                StringTokenizer st = new StringTokenizer(temp, " ");
                for (int j = 0; j < 2; j++) {
                    score[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            greedy();
            bw.write(Integer.toString(ans));
            bw.newLine();
        }
        bw.flush();

    }

    private static void greedy() {
        Arrays.sort(score, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int temp = score[0][1];

        for (int i = 1; i < n; i++) {
            if (score[i][1] > temp) {
                ans--;
                continue;
            }
            temp = score[i][1];
        }
    }
}
