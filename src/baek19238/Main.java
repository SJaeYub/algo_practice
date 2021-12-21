package baek19238;

import java.util.*;

/*
큐에 택시 시가 위치 저장 후, 가장 가까운 승객 위치로 가서
 */
public class Main {

    private static class Position {
        int x;
        int y;
        int dist;
        int pass_num;               //승객 도착정보 저장

        Position(int a, int b, int c, int d) {
            this.x = b;
            this.y = a;
            this.dist = c;
            this.pass_num = d;
        }

        private void setDist(int a) {
            this.dist = a;
        }
    }

    static int size;
    static int numOfPassenger;
    static int fuel;
    static int[][] map;
    static ArrayList<Position> nearPass = new ArrayList<>();
    static Queue<Position> q = new LinkedList<>();
    static boolean[][] visited;
    static int[] x = {-1, 1, 0, 0};
    static int[] y = {0, 0, -1, 1};
    static int dest_list_index = 0;
    static Position[] dest_list = new Position[410];
    static int dest_info;
    static Position taxi;
    static int cnt = 0;
    static int flag = 0;
    static int pass_check = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        size = sc.nextInt();
        map = new int[size][size];
        visited = new boolean[size][size];
        numOfPassenger = sc.nextInt();
        fuel = sc.nextInt();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int taxi_y = sc.nextInt() - 1;
        int taxi_x = sc.nextInt() - 1;
        map[taxi_y][taxi_x] = 0;            //택시 위치
        taxi = new Position(taxi_y, taxi_x, fuel, 0);
        q.add(taxi);

        for (int i = 0; i < numOfPassenger; i++) {
            map[sc.nextInt() - 1][sc.nextInt() - 1] = i + 2;        //승객 위치
            pass_check++;                                           //손님 들어올때마다 체크
            int Pass_dest_y = sc.nextInt() - 1;
            int Pass_dest_x = sc.nextInt() - 1;       //도착 위치를 변수로 저장해야함
            Position temp = new Position(Pass_dest_y, Pass_dest_x, 0, i + 2);
            dest_list[i + 2] = temp;
            dest_list_index++;                  // < dest_list_index로 해야함 이미 정렬되있음
        }

        while (cnt < dest_list_index) {
            bfs();
            if (flag == 0) {
                System.out.println("-1");
                return;
            }
            sending();
            cnt++;
        }
        if (pass_check != 0) {
            System.out.println("-1");
            return;
        }
        System.out.println(fuel);
        return;

    }

    static void bfs() {
        int cnt = 0;
        while (!q.isEmpty()) {
            cnt++;
            Position curr = q.poll();
            visited[curr.y][curr.x] = true;
            if (cnt == 1) {
                if (map[curr.y][curr.x] != 1 && map[curr.y][curr.x] != 0) {
                    flag = 1;
                    Position temp = new Position(curr.y, curr.x, curr.dist, map[curr.y][curr.x]);           //몇번 승객인지 위치 정보와 함께 클래스 객체에 저장, 꼭 -2 기억하기
                    nearPass.add(temp);                                                             //저장 될때 이미 소모된 연료량(dist)도 같이 저장된다
                    visited[curr.y][curr.x] = true;
                    q.add(temp);
                    continue;
                }
            }

            if (fuel < 0) {
                fuel = -1;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int mx = curr.x + x[i];
                int my = curr.y + y[i];

                if (mx >= 0 && mx < size && my >= 0 && my < size) {
                    if (!visited[my][mx]) {
                        if (map[my][mx] != 1 && map[my][mx] != 0) {
                            flag = 1;
                            if (curr.dist < -1) {
                                fuel = -1;
                                return;
                            }

                            Position temp = new Position(my, mx, curr.dist - 1, map[my][mx]);           //몇번 승객인지 위치 정보와 함께 클래스 객체에 저장, 꼭 -2 기억하기
                            nearPass.add(temp);                                                             //저장 될때 이미 소모된 연료량(dist)도 같이 저장된다
                            visited[my][mx] = true;
                            q.add(temp);
                        }

                        if (map[my][mx] != 1 && map[my][mx] == 0) {
                            Position temp = new Position(my, mx, curr.dist - 1, 0);
                            visited[my][mx] = true;
                            q.add(temp);
                        }
                    }
                }
            }
        }
        return;

    }

    static void sending() {
        if (fuel < 0) {
            return;
        }

        Collections.sort(nearPass, new Comparator<Position>() {
            @Override
            public int compare(Position o1, Position o2) {
                if (o1.dist == o2.dist) {
                    if (o1.y == o2.y) {
                        if (o1.x < o2.x) {
                            return -1;
                        } else {
                            return 1;
                        }
                    } else {
                        if (o1.y > o2.y) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                } else if (o1.dist < o2.dist) {
                    return 1;
                } else {
                    return -1;
                }

            }
        });

        Position passenger = nearPass.get(0);           //승객 번호 정보 저장됨 연료 이미 감소된 채로 있음
        Position destination = dest_list[passenger.pass_num];
        map[passenger.y][passenger.x] = 0;              //승객 태우면서 맵 값 0 초기화
        fuel = passenger.dist;                   //이동하고 남은 연료
        if (fuel < 0) {
            fuel = -1;
            return;
        }
        q.clear();
        visited = new boolean[size][size];
        passenger.setDist(0);
        q.add(passenger);
        dest_info = 0;
        int dest_num = 0;
        int flag2 = 0;


        while (!q.isEmpty()) {
            Position curr = q.poll();
            visited[curr.y][curr.x] = true;

            if (fuel < 0) {
                fuel = -1;
                return;
            }

            if (curr.y == destination.y && curr.x == destination.x) {
                flag2 = 1;
                if (fuel - curr.dist < 0) {
                    fuel = -1;
                    return;
                }
                fuel = fuel - curr.dist + (curr.dist * 2);
                if (fuel < 0) {
                    fuel = -1;
                    return;
                }
                pass_check--;                                                           //배송 완료할때마다 -1 -> 마지막엔 0이 되야함
                break;
            }

            for (int i = 0; i < 4; i++) {
                int mx = curr.x + x[i];
                int my = curr.y + y[i];


                if (mx >= 0 && mx < size && my >= 0 && my < size) {
                    if (!visited[my][mx]) {
                        if (map[my][mx] != 1) {
                            Position temp = new Position(my, mx, curr.dist + 1, 0);
                            visited[my][mx] = true;
                            q.add(temp);
                        }
                    }
                }

            }
        }
        if (flag2 == 0) {
            return;
        }

        taxi = new Position(destination.y, destination.x, fuel, 0);
        q.clear();
        visited = new boolean[size][size];
        nearPass = new ArrayList<>();
        flag = 0;
        q.add(taxi);
        return;

    }

}
