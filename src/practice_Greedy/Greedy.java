package practice_Greedy;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Greedy {

    static class Action implements Comparable<Action> {
        String name;
        int startTime;
        int endTime;

        public Action(String name, int startTime, int endTime) {
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Action action) {
            return this.endTime - action.endTime;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public static void main(String[] args) {
        ArrayList<Action> list = new ArrayList<>();
        list.add(new Action("A", 7, 8));
        list.add(new Action("B", 5, 7));
        list.add(new Action("C", 3, 6));
        list.add(new Action("D", 1, 2));
        list.add(new Action("E", 6, 9));
        list.add(new Action("F", 10, 11));
        Collections.sort(list);

        ArrayList<Action> ans = greedy(list);
        for (Action an : ans) {
            System.out.println(an.name + ", ");
        }
    }

    private static ArrayList<Action> greedy(ArrayList<Action> list) {
        int time = 0;
        ArrayList<Action> ans = new ArrayList<>();

        for (Action act : list) {
            if (time <= act.startTime) {
                time = act.endTime;
                ans.add(act);
            }
        }
        return ans;
    }
}
