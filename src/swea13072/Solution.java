package swea13072;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static class UserSolution{

        class Soldier implements Comparable<Soldier>{
            int mId;
            int mTeam;
            int mScore;

            Soldier(int id, int team, int score) {
                this.mId = id;
                this.mTeam = team;
                this.mScore = score;
            }

            @Override
            public int compareTo(Soldier s1) {
                if (this.mScore > s1.mScore) {
                    return -1;
                } else if (this.mScore == s1.mScore) {
                    if (this.mId > s1.mId) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            }
        }


        HashMap<Integer, HashMap<Integer, Soldier>> hMap_teams;

        public void init() {
            hMap_teams = new HashMap<>();
        }

        public void hire(int mID, int mTeam, int mScore) {
            Soldier soldier = new Soldier(mID, mTeam, mScore);

            if (!hMap_teams.containsKey(mTeam)) {
                hMap_teams.put(mTeam, new HashMap<>());
            }

            HashMap<Integer, Soldier> teamMembers = hMap_teams.get(mTeam);

            teamMembers.put(mID, soldier);
        }

        public void fire(int mID) {
            for (int i = 1; i <= 5; i++) {
                if (!hMap_teams.containsKey(i)) {
                    continue;
                }
                HashMap<Integer, Soldier> teamMembers = hMap_teams.get(i);
                if (teamMembers.containsKey(mID)) {
                    teamMembers.remove(mID);
                }
            }
        }

        public void updateSoldier(int mID, int mScore) {
            for (int i = 1; i <= 5; i++) {
                if (!hMap_teams.containsKey(i)) {
                    continue;
                }
                HashMap<Integer, Soldier> teamMembers = hMap_teams.get(i);
                if (teamMembers.containsKey(mID)) {
                    teamMembers.get(mID).mScore = mScore;
                }
            }
        }

        public void updateTeam(int mTeam, int mChangeScore) {
            HashMap<Integer, Soldier> teamMembers = hMap_teams.get(mTeam);

            for (int mId : teamMembers.keySet()) {
                Soldier soldier = teamMembers.get(mId);
                if (soldier.mScore + mChangeScore > 5) {
                    teamMembers.get(mId).mScore = 5;
                    continue;
                }
                if (soldier.mScore + mChangeScore < 1) {
                    teamMembers.get(mId).mScore = 1;
                    continue;
                }
            }
        }

        public int bestSoldier(int mTeam) {
            return 0;
        }
    }

    private final static int CMD_INIT = 1;
    private final static int CMD_HIRE = 2;
    private final static int CMD_FIRE = 3;
    private final static int CMD_UPDATE_SOLDIER = 4;
    private final static int CMD_UPDATE_TEAM = 5;
    private final static int CMD_BEST_SOLDIER = 6;

    private final static UserSolution usersolution = new UserSolution();

    private static boolean run(BufferedReader br) throws Exception {
        StringTokenizer st;

        int numQuery;

        int mID, mTeam, mScore, mChangeScore;

        int userAns, ans;

        boolean isCorrect = false;

        numQuery = Integer.parseInt(br.readLine());

        for (int q = 0; q < numQuery; ++q) {
            st = new StringTokenizer(br.readLine(), " ");

            int cmd;
            cmd = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case CMD_INIT:
                    usersolution.init();
                    isCorrect = true;
                    break;
                case CMD_HIRE:
                    mID = Integer.parseInt(st.nextToken());
                    mTeam = Integer.parseInt(st.nextToken());
                    mScore = Integer.parseInt(st.nextToken());
                    usersolution.hire(mID, mTeam, mScore);
                    break;
                case CMD_FIRE:
                    mID = Integer.parseInt(st.nextToken());
                    usersolution.fire(mID);
                    break;
                case CMD_UPDATE_SOLDIER:
                    mID = Integer.parseInt(st.nextToken());
                    mScore = Integer.parseInt(st.nextToken());
                    usersolution.updateSoldier(mID, mScore);
                    break;
                case CMD_UPDATE_TEAM:
                    mTeam = Integer.parseInt(st.nextToken());
                    mChangeScore = Integer.parseInt(st.nextToken());
                    usersolution.updateTeam(mTeam, mChangeScore);
                    break;
                case CMD_BEST_SOLDIER:
                    mTeam = Integer.parseInt(st.nextToken());
                    userAns = usersolution.bestSoldier(mTeam);
                    ans = Integer.parseInt(st.nextToken());
                    if (userAns != ans) {
                        isCorrect = false;
                    }
                    break;
                default:
                    isCorrect = false;
                    break;
            }
        }

        return isCorrect;
    }

    public static void main(String[] args) throws Exception {
        int TC, MARK;

        System.setIn(new java.io.FileInputStream("src/swea13072/sample_25_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        long startTime = System.currentTimeMillis();
        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime));

        br.close();
    }
}