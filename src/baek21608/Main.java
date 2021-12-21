package baek21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Main {

    static int n, result;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(bf.readLine());

        int size = (int) Math.pow(n, 2);

        ArrayList<Set<Integer>> like = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            like.add(new HashSet<Integer>());
        }

        int idx;
        int[] order = new int[size];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(bf.readLine());
            idx = Integer.parseInt(st.nextToken());
            order[i] = idx;
            for (int j = 0; j < 4; j++) {
                like.get(idx).add(Integer.parseInt(st.nextToken()));
            }
        }

        int[][] sit = new int[n][n];

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        int y, x, score = 0, maxS, ty = -1, tx = -1;

        for (idx = 0; idx < size; idx++) {
            maxS = -1;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (sit[i][j] != 0) {
                        continue;
                    }
                    score = 0;

                    for (int k = 0; k < 4; k++) {
                        y = i + dy[k];
                        x = j + dx[k];

                        if (isIn(y, x) && like.get(order[idx]).contains(sit[y][x])) {
                            score += 5;
                        } else if (isIn(y, x) && sit[y][x] == 0) {
                            score += 1;
                        }
                    }
                    if (score > maxS) {
                        ty = i;
                        tx = j;
                        maxS = score;
                    }
                }
            }
            sit[ty][tx] = order[idx];
        }

        int result = 0, cnt;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cnt = 0;

                for (int k = 0; k < 4; k++) {
                    y = i + dy[k];
                    x = j + dx[k];
                    if (isIn(y, x) && like.get(sit[i][j]).contains(sit[y][x])) {
                        cnt++;
                    }
                }

                if (cnt != 0) {
                    result += (int) Math.pow(10, cnt - 1);
                }
            }

        }
        System.out.println(result);

        bf.close();
    }

    private static boolean isIn(int y, int x) {
        if (y < 0 || y >= n || x < 0 || x >= n) {
            return false;
        }
        return true;
    }
}
