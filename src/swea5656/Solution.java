/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
package swea5656;

import java.util.Scanner;
import java.util.*;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
public class Solution {
    static int n;
    static int w;
    static int h;
    static int[][] map;
    static Queue<Position> q;
    static int[] x = {-1, 1, 0, 0};
    static int[] y = {0, 0, -1, 1};
    static boolean[][] visited;
    static int min_bricks = Integer.MAX_VALUE;

    static class Ball {
        int x;
        int y;

        Ball(int a, int b) {
            this.x = a;
            this.y = b;
        }
    }


    static class Position {
        int x;
        int y;
        int cnt;
        int box_info;

        Position(int a, int b, int c) {
            this.y = a;
            this.x = b;
            this.cnt = c;
        }
    }


    public static void main(String args[]) throws Exception {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        //System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for (int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();
            w = sc.nextInt();
            h = sc.nextInt();
            map = new int[h][w];

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            dfs(0, map);

            System.out.println("#" + test_case + " " + min_bricks);

        }
    }

    static void dfs(int cnt, int[][] map) {
        if (cnt == n) {
            int result = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] > 0) {
                        result++;
                    }
                }
            }
            min_bricks = Math.min(min_bricks, result);
            return;
        }

        int[][] newMap = new int[h][w];

        for (int i = 0; i < w; i++) {

            int temp_y = 0;
            while (temp_y < h && map[temp_y][i] == 0) {
                temp_y++;
            }
            if (temp_y == h) {
                dfs(cnt + 1, map);
            } else {
                copy(newMap, map);
                bfs(newMap, temp_y, i);
                downBricks(newMap);
                dfs(cnt + 1, newMap);
            }
        }
    }

    static void downBricks(int[][] newMap) {
        for (int i = 0; i < w; i++) {
            Queue<Integer> q_temp = new LinkedList<>();
            int temp = h - 1;
            while (temp >= 0) {
                if (newMap[temp][i] > 0) {
                    q_temp.add(newMap[temp][i]);
                    newMap[temp][i] = 0;
                }
                temp--;
            }
            temp = h - 1;
            while (!q_temp.isEmpty()) {
                newMap[temp][i] = q_temp.poll();
                temp--;
            }
        }
    }

    static void bfs(int[][] newMap, int r, int c) {
        Queue<Position> q = new LinkedList<>();
        if (newMap[r][c] > 1) {
            q.add(new Position(r, c, newMap[r][c]));
        }
        newMap[r][c] = 0;

        while (!q.isEmpty()) {
            Position curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int mx = curr.x;
                int my = curr.y;
                for (int j = 1; j < curr.cnt; j++) {
                    mx += x[i];
                    my += y[i];
                    if (mx >= 0 && mx < w && my >= 0 && my < h && newMap[my][mx] != 0) {
                        if (newMap[my][mx] > 1) {
                            q.add(new Position(my, mx, newMap[my][mx]));
                        }
                        newMap[my][mx] = 0;
                    }
                }
            }
        }
    }

    static void copy(int[][] a, int[][] b) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                a[i][j] = b[i][j];
            }
        }
    }


}