import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Practice {

    static class Test {
        int row;
        int col;
        int cnt = 0;

        Test(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }

    public static void main(String[] args) {
        ArrayList<Test> arrayList = new ArrayList<>();
        Queue<Test> q = new LinkedList<>();

        Test test = new Test(1, 1);
        arrayList.add(test);
        q.add(test);

        System.out.println(arrayList.get(0).cnt);
        System.out.println(q.peek().cnt);
        q.peek().cnt++;
        System.out.println(arrayList.get(0).cnt);
    }

    static void changeTest(Test test) {
        System.out.println(test.cnt);

        System.out.println(test.cnt);
    }


}
