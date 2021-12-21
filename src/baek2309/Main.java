package baek2309;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] dwarf_height;
    static int[] result = {0,0,0,0,0,0,0};
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        dwarf_height = new int[9];
        visited = new boolean[9];

        for (int i = 0; i < 9; i++) {
            dwarf_height[i] = sc.nextInt();
        }

        dfs(0, 1);

        for (int i = 0; i < 7; i++) {
            if (result[i] != 0) {
                System.out.println(result[i]);
            }
        }
    }

    static void dfs(int height_index, int cnt) {
        if(cnt == 8) {
            cal();
            return;
        }

        for(int i = height_index; i < 9; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(i + 1, cnt + 1);
                visited[i] = false;
            }
        }
    }

    static void cal() {
        int total = 0;
        int[] result_visited = new int[7];

        for(int i = 0; i < 9; i++) {
            if(visited[i]) {
                total += dwarf_height[i];
            }
        }

        if (total == 100) {
                for (int j = 0; j < 9; j++) {
                    if(visited[j]) {
                        for(int i = 0; i < 7; i++) {
                            if(result[i] == 0) {
                                result [i] = dwarf_height[j];
                                break;
                            }
                        }

                    }

                }
//            System.out.println('-');
            Arrays.sort(result);
            return;
        }
        else {
            return;
        }

    }
}
