package baek20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static class Belt {
        int durability;
        int beltNum;
        boolean robotYN;

        Belt(int d, int b, boolean r) {
            durability = d;
            beltNum = b;
            robotYN = r;
        }
    }

    static int N, K, zero_cnt = 0;
    static Belt[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new Belt[2 * N + 1];

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i <= 2 * N; i++) {
            if (i == 0) {
                arr[i] = new Belt(0, 0, false);
                continue;
            }
            arr[i] = new Belt(Integer.parseInt(st.nextToken()), i, false);
        }

        int cnt = 0;

        while (true) {

            rotate();
            arr[N].robotYN = false;

            move();
            arr[N].robotYN = false;

            upload();

            cnt++;
            if (zero_cnt >= K) {
                break;
            }
        }

        System.out.println(cnt);
    }

    static void rotate() {
        Belt swap = arr[1];

        for (int i = 1; i <= 2 * N; i++) {
            if (i != 2 * N) {
                Belt temp = arr[i + 1];
                arr[i + 1] = swap;
                swap = temp;
                continue;
            }
            if (i == N - 1) {
                Belt temp = arr[i + 1];
                arr[i + 1] = swap;
                swap = temp;
                if (i + 1 == N) {
                    arr[i + 1].robotYN = false;
                }
                continue;
            }
            if (i == 2 * N) {
                arr[1] = swap;
            }
        }
    }

    static void upload() {
        if (arr[1].durability != 0) {
            arr[1].robotYN = true;
            arr[1].durability -= 1;
            if (arr[1].durability == 0) {
                zero_cnt++;
            }
        }
    }

    static void move() {
        for (int i = N - 1; i >= 1; i--) {
            if (arr[i].robotYN) {
                if (!arr[i + 1].robotYN && arr[i + 1].durability >= 1) {
                    arr[i + 1].robotYN = true;
                    arr[i + 1].durability -= 1;
                    if (arr[i + 1].durability == 0) {
                        zero_cnt++;
                    }
                    if (i + 1 == N) {
                        arr[i + 1].robotYN = false;
                    }
                    arr[i].robotYN = false;
                }
            }
        }
    }
}
