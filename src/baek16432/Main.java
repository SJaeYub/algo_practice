package baek16432;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static ArrayList<Integer>[] arr_list;
    static boolean[][] visited;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr_list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            arr_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");
            int numOfDDuk = Integer.parseInt(st.nextToken());
            for (int j = 0; j < numOfDDuk; j++) {
                arr_list[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        result = new int[n + 1];
        visited = new boolean[n + 2][10]; //[n번째 날][떡의 종류]
        dfs(0, 0);
        System.out.println(-1);

    }

    static void dfs(int day, int prev) {
        if (day == n) {
            for (int i = 0; i < n; i++) {
                System.out.println(result[i]);
            }
            System.exit(0);
            return;
        }

        for (int i = 1; i <= 9; i++) {      //visited[day + 1][i] = false 작업을 해주지 않는 이유는
                                            //다시 false를 하고 그 떡을 선택해봤자 밑의 결과는 똑같기 때문이다! 노드라고 생각하자
            if (i != prev && !visited[day + 1][i] && arr_list[day].contains(i)) {
                visited[day + 1][i] = true;
                result[day] = i;
                dfs(day + 1, i);
            }
        }

    }
}
