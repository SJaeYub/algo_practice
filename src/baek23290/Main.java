package baek23290;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Fish {
        int row;
        int col;
        int dist;

        Fish(int r, int c, int d) {
            row = r;
            col = c;
            dist = d;
        }
    }

    static class Shark {
        int row;
        int col;

        Shark(int r, int c) {
            row = r;
            col = c;
        }
    }

    static class SharkCourse implements Comparable<SharkCourse> {
        String course_str;
        int course_int;
        int fishCnt;

        SharkCourse(String str, int ci, int cnt) {
            course_str = str;
            course_int = ci;
            fishCnt = cnt;
        }

        @Override
        public int compareTo(SharkCourse sc1) {
            if (this.fishCnt == sc1.fishCnt) {
                return Integer.compare(this.course_int, sc1.course_int);
            } else {
                return -Integer.compare(this.fishCnt, sc1.fishCnt);
            }
        }
    }

    static class FishSmell {
        int row;
        int col;
        int turn;

        FishSmell(int r, int c, int t) {
            row = r;
            col = c;
            turn = t;
        }
    }

    static class MapInfo {
        Fish fish;

        MapInfo(Fish target) {
            fish = target;
        }
    }

    static ArrayList<FishSmell> fishSmellList;
    static ArrayList<Fish> fishCloneInfoList;
    static ArrayList<Fish> fishInfoList;
    static ArrayList<MapInfo>[][] map;
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dr_s = {0, -1, 0, 1, 0};
    static int[] dc_s = {0, 0, -1, 0, 1};
    static boolean[][] visited_fish;
    static boolean[][] visited_shark;

    static int M, S;
    static Shark shark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        map = new ArrayList[5][5];
        visited_fish = new boolean[5][5];
        visited_shark = new boolean[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        fishSmellList = new ArrayList<>();
        fishCloneInfoList = new ArrayList<>();
        fishInfoList = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int fishRow = Integer.parseInt(st.nextToken());
            int fishCol = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            Fish curr = new Fish(fishRow, fishCol, dist);

            map[fishRow][fishCol].add(new MapInfo(curr));
            fishInfoList.add(curr);
        }

        st = new StringTokenizer(br.readLine(), " ");
        int sharkRow = Integer.parseInt(st.nextToken());
        int sharkCol = Integer.parseInt(st.nextToken());
        shark = new Shark(sharkRow, sharkCol);
        visited_shark[sharkRow][sharkCol] = true;

        int cnt = 0;
        while (cnt < S) {
            cloneFish();

            moveFish();

            moveShark();

            clearSmell();

            for (Fish curr : fishCloneInfoList) {
                Fish newFish = new Fish(curr.row, curr.col, curr.dist);
                map[curr.row][curr.col].add(new MapInfo(newFish));
                fishInfoList.add(newFish);
            }

            fishCloneInfoList = new ArrayList<>();
            cnt++;
        }

        System.out.println(fishInfoList.size());

    }

    static void cloneFish() {
        fishCloneInfoList.addAll(fishInfoList);
    }

    static void clearSmell() {
        for (int i = 0; i < fishSmellList.size(); i++) {
            FishSmell curr = fishSmellList.get(i);
            curr.turn++;
            if (curr.turn == 2) {
                visited_fish[curr.row][curr.col] = false;
                fishSmellList.remove(i);
            }
        }
    }

    static void moveShark() {

        PriorityQueue<SharkCourse> pq = new PriorityQueue<>();

        dfs_searchCourse(pq, 0, 0, shark.row, shark.col, 0);

        SharkCourse target_course = pq.poll();
        String target_str = target_course.course_str;

        for (int i = 0; i < 3; i++) {

            int next_row = shark.row + dr_s[target_str.charAt(i) - '0'];
            int next_col = shark.col + dc_s[target_str.charAt(i) - '0'];

            for (MapInfo curr : map[next_row][next_col]) {
                Fish fish = curr.fish;
                fishInfoList.remove(fish);
            }
            map[next_row][next_col].clear();

            fishSmellList.add(new FishSmell(next_row, next_col, -1));
            visited_fish[next_row][next_col] = true;

            if (i == 2) {
                visited_shark[next_row][next_col] = true;
                visited_shark[shark.row][shark.col] = false;
            }
        }
    }

    static void dfs_searchCourse(PriorityQueue<SharkCourse> pq, int idx, int dep,
                                 int row, int col, int cnt) {

        if (dep == 3) {
            SharkCourse sc = new SharkCourse(String.valueOf(idx), idx, cnt);
            pq.add(sc);
            return;
        }

        for (int i = 1; i <= 4; i++) {
            int next_row = row + dr_s[i];
            int next_col = col + dr_s[i];

            if (next_row >= 1 && next_row <= 4 &&
                    next_col >= 1 && next_col <= 4) {

                int next_idx = 0;
                if (idx == 0) {
                    next_idx = i;
                } else {
                    String str = String.valueOf(idx);
                    StringBuilder sb = new StringBuilder(str);
                    String temp = String.valueOf(i);
                    sb.append(temp);
                    String next_str = new String(sb);
                    next_idx = Integer.parseInt(next_str);
                }

                cnt += map[next_row][next_col].size();
                dfs_searchCourse(pq, next_idx, dep + 1, next_row, next_col, cnt);
                cnt -= map[next_row][next_col].size();
            }
        }
    }

    static void moveFish() {

        for (Fish curr : fishInfoList) {
            int distOrigin = curr.dist;
            int distNext = -1;

            int distNext_row = curr.row + dr[curr.dist];
            int distNext_col = curr.col + dc[curr.dist];
            //반복문 들어가야함
            while (distOrigin != distNext) {
                distNext_row = curr.row + dr[curr.dist];
                distNext_col = curr.col + dc[curr.dist];

                if (distNext_row >= 1 && distNext_row <= 4 &&
                        distNext_col >= 1 && distNext_col <= 4) {
                    if (!visited_shark[distNext_row][distNext_col] &&
                            !visited_fish[distNext_row][distNext_col]) {

                        map[distNext_row][distNext_col].add(new MapInfo(curr));
                        curr.row = distNext_row;
                        curr.col = distNext_col;

//                        물고기 하나 제거
//                        for (int i = 0; i < map[curr.row][curr.col].size(); i++) {
//                            if (map[curr.row][curr.col].get(i). == 1) {
//                                map[curr.row][curr.col].remove(i);
//                                break;
//                            }
//                        }
                        map[curr.row][curr.col].remove(curr);

                        break;
                    }
                }

//                이동 방향 회전
                curr.dist = (curr.dist + 1) % 9;
                if (curr.dist == 0) {
                    curr.dist = (curr.dist + 1) % 9;
                }
                distNext = curr.dist;

            }
//            이동 못하는 경우
        }
    }
}
