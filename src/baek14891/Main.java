package baek14891;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] gear_arr = new int[4][8];
    static int[] check_flag = new int[4];
    static int[] check_dir = new int[4];
    static Queue<Gear> q = new LinkedList<>();
    static boolean[] visited;
    static int[] x = {-1, 1};
    static int[] state_pole = new int[6];

    static class Gear {
        int rotateDir;
        int checkRotate;
        int gearNum;

        Gear(int a, int b, int c) {
            this.gearNum = a;
            this.rotateDir = b;
            this.checkRotate = c;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 4; i++) {
            String state_gear = sc.next();
            for (int j = 0; j < 8; j++) {
                gear_arr[i][j] = state_gear.charAt(j) - '0';
            }
        }

        int numOfRotate = sc.nextInt();
        for (int i = 0; i < numOfRotate; i++) {
            visited = new boolean[4];
            int gearNum = sc.nextInt() - 1;
            int rotate_dir = sc.nextInt();

            Gear gear = new Gear(gearNum, rotate_dir, 1);
            q.clear();
            q.add(gear);
            check_pole();
            bfs();                                      //회전 여부만 체크한다
            turning(0, 1);
        }
        System.out.println(sum());
    }

    static int sum() {
        int answer = 0;
        if (gear_arr[0][0] == 0) {
            answer += 0;
        } else {
            answer += 1;
        }
        if (gear_arr[1][0] == 0) {
            answer += 0;
        } else {
            answer += 2;
        }
        if (gear_arr[2][0] == 0) {
            answer += 0;
        } else {
            answer += 4;
        }
        if (gear_arr[3][0] == 0) {
            answer += 0;
        } else {
            answer += 8;
        }

        return answer;
    }
//옆 기어가 회전하지 않는 경우 -> 회전안함 인자 전송, 옆 기어가 회전하는경우 -> 극이 같은지 다른지 판별, 극이 다르면 -> 회전 방향, 회전함 인자 전송, 극이 같으면 -> 회전 안함
    static void bfs() {
        while (!q.isEmpty()) {
            Gear curr = q.poll();
            visited[curr.gearNum] = true;
            check_flag[curr.gearNum] = curr.checkRotate;
            check_dir[curr.gearNum] = curr.rotateDir;

            for (int i = 0; i < 2; i++) {
                int mx = curr.gearNum + x[i];

                if (mx >= 0 && mx < 4) {
                    if (!visited[mx]) {
                        if (curr.checkRotate == 0) {
                            Gear temp = new Gear(mx, curr.rotateDir * -1, 0);
                            visited[mx] = true;
                            q.add(temp);
                        }
                        if (curr.checkRotate == 1) {
                            if (curr.gearNum == 0 && mx == 1) {                                       //1번 기어 -- 2번 기어
                                if (state_pole[0] == state_pole[1]) {                   //회전하지 않는다
                                    Gear temp = new Gear(mx, curr.rotateDir, 0);
                                    visited[mx] = true;
                                    q.add(temp);
                                } else {                                                //반대방향으로 회전한다
                                    Gear temp = new Gear(mx, curr.rotateDir * -1, 1);
                                    visited[mx] = true;
                                    q.add(temp);
                                }
                            }
                            if (curr.gearNum == 1 && mx == 0) {                                       //1번 기어 -- 2번 기어
                                if (state_pole[0] == state_pole[1]) {
                                    Gear temp = new Gear(mx, curr.rotateDir, 0);
                                    visited[mx] = true;
                                    q.add(temp);
                                } else {
                                    Gear temp = new Gear(mx, curr.rotateDir * -1, 1);
                                    visited[mx] = true;
                                    q.add(temp);
                                }
                            }
                            if (curr.gearNum == 1 && mx == 2) {                                         //2번 기어 -- 3번기어
                                if (state_pole[2] == state_pole[3]) {
                                    Gear temp = new Gear(mx, curr.rotateDir, 0);
                                    visited[mx] = true;
                                    q.add(temp);
                                } else {
                                    Gear temp = new Gear(mx, curr.rotateDir * -1, 1);
                                    visited[mx] = true;
                                    q.add(temp);
                                }
                            }
                            if (curr.gearNum == 2 && mx == 1) {                                                //2번 기어 -- 3번 기어
                                if (state_pole[2] == state_pole[3]) {
                                    Gear temp = new Gear(mx, curr.rotateDir, 0);
                                    visited[mx] = true;
                                    q.add(temp);
                                } else {
                                    Gear temp = new Gear(mx, curr.rotateDir * -1, 1);
                                    visited[mx] = true;
                                    q.add(temp);
                                }
                            }
                            if (curr.gearNum == 2 && mx == 3) {                                                //3번 기어 -- 4번 기어
                                if (state_pole[4] == state_pole[5]) {
                                    Gear temp = new Gear(mx, curr.rotateDir, 0);
                                    visited[mx] = true;
                                    q.add(temp);
                                } else {
                                    Gear temp = new Gear(mx, curr.rotateDir * -1, 1);
                                    visited[mx] = true;
                                    q.add(temp);
                                }
                            }
                            if (curr.gearNum == 3 && mx == 2) {                                                 //3번기어 -- 4번 기어
                                if (state_pole[4] == state_pole[5]) {
                                    Gear temp = new Gear(mx, curr.rotateDir, 0);
                                    visited[mx] = true;
                                    q.add(temp);
                                } else {
                                    Gear temp = new Gear(mx, curr.rotateDir * -1, 1);
                                    visited[mx] = true;
                                    q.add(temp);
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    static void check_pole() {
        state_pole[0] = gear_arr[0][2];
        state_pole[1] = gear_arr[1][6];
        state_pole[2] = gear_arr[1][2];
        state_pole[3] = gear_arr[2][6];
        state_pole[4] = gear_arr[2][2];
        state_pole[5] = gear_arr[3][6];
    }

    static void turning(int num, int cnt) {
        if (cnt == 4) {
            if (check_flag[num] == 0) {
                return;
            } else {
                if (check_dir[num] == -1) {                         //반시계방향 회전
                    int[] temp = new int[8];

                    for (int i = 0; i < temp.length; i++) {
                        if (i != temp.length - 1) {
                            temp[i] = gear_arr[num][i + 1];
                        }
                        if (i == temp.length - 1) {
                            temp[i] = gear_arr[num][0];
                        }
                    }
                    for (int i = 0; i < temp.length; i++) {
                        gear_arr[num][i] = temp[i];
                    }
                } else {                                            //시계방향 회전
                    int[] temp = new int[8];

                    for (int i = 0; i < temp.length; i++) {
                        if (i != temp.length - 1) {
                            temp[i + 1] = gear_arr[num][i];
                        }
                        if (i == temp.length - 1) {
                            temp[0] = gear_arr[num][temp.length - 1];
                        }
                    }

                    for (int i = 0; i < temp.length; i++) {
                        gear_arr[num][i] = temp[i];
                    }
                }
                return;
            }
        } else {
            if (check_flag[num] == 0) {
                turning(num + 1, cnt + 1);
                return;
            } else {
                if (check_dir[num] == -1) {                         //반시계방향 회전
                    int[] temp = new int[8];

                    for (int i = 0; i < temp.length; i++) {
                        if (i != temp.length - 1) {
                            temp[i] = gear_arr[num][i + 1];
                        }
                        if (i == temp.length - 1) {
                            temp[i] = gear_arr[num][0];
                        }
                    }
                    for (int i = 0; i < temp.length; i++) {
                        gear_arr[num][i] = temp[i];
                    }
                } else {                                            //시계방향 회전
                    int[] temp = new int[8];

                    for (int i = 0; i < temp.length; i++) {
                        if (i != temp.length - 1) {
                            temp[i + 1] = gear_arr[num][i];
                        }
                        if (i == temp.length - 1) {
                            temp[0] = gear_arr[num][temp.length - 1];
                        }
                    }

                    for (int i = 0; i < temp.length; i++) {
                        gear_arr[num][i] = temp[i];
                    }
                }
                turning(num + 1, cnt + 1);
            }
        }







    }
}



