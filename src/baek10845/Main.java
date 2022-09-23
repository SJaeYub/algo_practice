package baek10845;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static Queue<Integer> q = new LinkedList<>();

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {

//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            String cmd = br.readLine();

            StringTokenizer st = new StringTokenizer(cmd, " ");
            cmd = st.nextToken();

            int num = 0;
            if(st.hasMoreTokens()) {
                num = Integer.parseInt(st.nextToken());
            }

            if(cmd.equals("push")) {
                q.add(num);
            }
            if(cmd.equals("pop")) {
                if(q.size() >= 1) {
                    System.out.println(q.poll());
                } else {
                    System.out.println(-1);
                }
            }
            if(cmd.equals("size")) {
                System.out.println(q.size());
            }
            if(cmd.equals("empty")) {
                if(!q.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                }
            }
            if(cmd.equals("front")) {
                if(!q.isEmpty()) {
                    System.out.println(q.peek());
                } else {
                    System.out.println(-1);
                }
            }
            if(cmd.equals("back")) {
                if(!q.isEmpty()) {
                    Queue<Integer> temp = new LinkedList<>();
                    int ans = 0;
                    while(!q.isEmpty()) {
                        ans = q.poll();
                        temp.add(ans);
                    }

                    System.out.println(ans);
                    q = temp;

                } else {
                    System.out.println(-1);
                }
            }
        }
    }

}
