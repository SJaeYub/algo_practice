package swea1461;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileInputStream;


public class Solution {

    static class Processor {
        int row;
        int col;
        boolean visited;
        boolean processor;
        int key;

        Processor(int r, int c, boolean p) {
            this.row = r;
            this.col = c;
            this.visited = false;
            this.processor = p;
//            this.key = k;
        }

        @Override
        public int hashCode() {
            return key;
        }

        @Override
        public boolean equals(Object obj) {
            Processor p1;
            if (obj instanceof Processor) {
                p1 = (Processor) obj;
                if (this.row == p1.row) {
                    if (this.col == p1.col) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    static ArrayList<Processor> arr_list = new ArrayList<>();
    static HashMap<Integer, Processor> hSet;
    static int[][] map;
    static boolean[][] visited;
    static int result;

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("src/swea1461/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            result = Integer.MAX_VALUE;
            map = new int[n][n];
            visited = new boolean[n][n];
            arr_list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int val = sc.nextInt();
                    if (val == 1) {
                        arr_list.add(new Processor(i, j, true));
                        System.out.println("프로세서 위치를 저장합니다  row : " + i + " col : " + j);

                        visited[i][j] = true;
                        map[i][j] = 2;
                        continue;
                    }
                    map[i][j] = val;
                }
            }

            for (int i = 0; i < arr_list.size(); i++) {
                int forward = 5;
                Processor curr = arr_list.get(i);
                for (int j = 0; j < 4; j++) {
                    forward = j;
                    int cost = wireSetting(curr, n, forward);

                    curr.visited = true;
                    dfs(i, cost, arr_list.size());

                    int resell = wireRemove(curr, n, forward);

                    if (resell != cost) {
                        System.out.println("해제가 제대로 되지 않았습니다 cost : " + cost + " resell : " + resell);
                        System.exit(0);
                    }

                    curr.visited = false;
                }

            }

            System.out.println(result);
            return;
        }
    }

    /**
     * @param curr_idx 현재 넘어가는 인덱스
     * @param sum      전선 길이 합을 넘기는 인자
     * @param size     프로세서 리스트가 저장된 배열리스트 길이
     */
    static void dfs(int curr_idx, int sum, int size) {

        for (int i = 0; i < size; i++) {
            Processor next = arr_list.get(i);
            if (!next.visited) {
                /*여기서 전선 설정 메서드 실행*/
                int forward = 5;
                for (int j = 0; j < 4; j++) {
                    forward = j;
                    int cost = wireSetting(next, size, forward);

                    next.visited = true;
                    sum += cost;
                    dfs(i, sum, size);

                    int resell = wireRemove(next, size, forward);

                    if (resell != cost) {
                        System.out.println("해제가 제대로 되지 않았습니다 cost : " + cost + " resell : " + resell);
                        System.exit(0);
                    }

                    next.visited = false;
                    sum -= cost;
                }
            }
        }

        result = Math.min(result, sum);
        System.out.println("리프 노드 입니다. result : " + result);
        return;
    }


    static int wireRemove(Processor proc, int wall, int forward) {
        int recall_cost = 0;

        int curr_row = proc.row;
        int curr_col = proc.col;

        if (proc.row == 0 || proc.row == wall - 1 || proc.col == 0 || proc.col == wall - 1) {     //프로세서가 전원에 붙어있는 경우 -> 해제할 비용이 없다
            System.out.println("해제할 프로세서의 위치  row : " + curr_row + " col : " + curr_col + " 이미 전원에 연결되어있습니다. 비용 : 0");
            return 0;
        }

        int key_generator = 0;

        if (forward == 0) {             //왼쪽
            for (int i = curr_col - 1; i >= 0; i--) {
                Processor temp = hSet.get(key_generator++);
                if (visited[curr_row][i] && map[curr_row][i] != 2) {
                    if (temp.row == curr_row && temp.col == i) {
                        visited[curr_row][i] = false;
                        recall_cost += 1;
                        continue;
                    }
                }

                //조건문을 충족하지 않는다면 전선이 겹친다는 의미
                return 0;
            }

        } else if (forward == 1) {      //아래
            for (int i = curr_row + 1; i < wall; i++) {
                Processor temp = hSet.get(key_generator++);
                if (visited[i][curr_col] && map[i][curr_col] != 2) {
                    if (temp.row == i && temp.col == curr_col) {
                        visited[i][curr_col] = false;
                        recall_cost += 1;
                        continue;
                    }
                }

                //조건문을 충족하지 않는다면 전선이 겹친다는 의미
                return 0;
            }

        } else if (forward == 2) {      //오른쪽
            for (int i = curr_col + 1; i < wall; i++) {
                Processor temp = hSet.get(key_generator++);
                if (visited[curr_row][i] && map[curr_row][i] != 2) {
                    if (temp.row == curr_row && temp.col == i) {
                        visited[curr_row][i] = false;
                        recall_cost += 1;
                        continue;
                    }
                }

                //조건문을 충족하지 않는다면 전선이 겹친다는 의미
                return 0;
            }
        } else {                        //위
            for (int i = curr_row - 1; i >= 0; i--) {
                Processor temp = hSet.get(key_generator++);
                if (visited[i][curr_col] && map[i][curr_col] != 2) {
                    if (temp.row == i && temp.col == curr_col) {
                        visited[i][curr_col] = false;
                        recall_cost += 1;
                        continue;
                    }
                }

                //조건문을 충족하지 않는다면 전선이 겹친다는 의미
                return 0;
            }

        }

        System.out.println("해제할 프로세서의 위치  row : " + curr_row + " col : " + curr_col + " 비용 : " + recall_cost);
        return recall_cost;
    }

    /**
     * @param proc    전선을 설치할 프로세서 정보
     * @param wall    벽 사이즈 정보
     * @param forward 방향 정보
     * @return
     */
    static int wireSetting(Processor proc, int wall, int forward) {
        int result_cost = 0;

        int curr_row = proc.row;
        int curr_col = proc.col;

        hSet = new HashMap<>();

        if (proc.row == 0 || proc.row == wall - 1 || proc.col == 0 || proc.col == wall - 1) {     //프로세서가 전원에 붙어있는 경우
            System.out.println("현재 프로세서의 위치  row : " + curr_row + " col : " + curr_col + " 이미 전원에 연결되어있습니다. 비용 : 0");

            return result_cost;
        }

        int key_generator = 0;

        if (forward == 0) {             //왼쪽
            for (int i = curr_col - 1; i >= 0; i--) {
                if (!visited[curr_row][i]) {
                    visited[curr_row][i] = true;
                    hSet.put(key_generator++, new Processor(curr_row, i, false));
                    result_cost += 1;
                    continue;
                }
                //조건문을 충족하지 않는다면 전선이 겹친다는 의미
                return 0;
            }

        } else if (forward == 1) {      //아래
            for (int i = curr_row + 1; i < wall; i++) {
                if (!visited[i][curr_col]) {
                    visited[i][curr_col] = true;
                    hSet.put(key_generator++, new Processor(curr_row, i, false));
                    result_cost += 1;
                    continue;
                }
                //조건문을 충족하지 않는다면 전선이 겹친다는 의미
                return 0;
            }

        } else if (forward == 2) {      //오른쪽
            for (int i = curr_col + 1; i < wall; i++) {
                if (!visited[curr_row][i]) {
                    visited[curr_row][i] = true;
                    hSet.put(key_generator++, new Processor(curr_row, i, false));
                    result_cost += 1;
                    continue;
                }
                //조건문을 충족하지 않는다면 전선이 겹친다는 의미
                return 0;
            }
        } else {                        //위
            for (int i = curr_row - 1; i >= 0; i--) {
                if (!visited[i][curr_col]) {
                    visited[i][curr_col] = true;
                    hSet.put(key_generator++, new Processor(curr_row, i, false));
                    result_cost += 1;
                    continue;
                }
                //조건문을 충족하지 않는다면 전선이 겹친다는 의미
                return 0;
            }

        }

        System.out.println("현재 프로세서의 위치  row : " + curr_row + " col : " + curr_col + "  비용 : " + result_cost);
        return result_cost;
    }
}