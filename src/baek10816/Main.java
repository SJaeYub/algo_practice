package baek10816;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        int n = sc.nextInt();
        int n = bf.read();

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
//            int temp = sc.nextInt();
//            map.put(temp, map.getOrDefault(temp, 0) + 1);
            int temp = bf.read();
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }



        int m = bf.read();;
        int[] find_deck = new int[m];
        Set<Integer> integers = map.keySet();

        for (int i = 0; i < m; i++) {
            boolean flag = false;
            find_deck[i] = bf.read();;
            for (Integer key : map.keySet()) {
                if(key == find_deck[i]) {
//                    System.out.print(map.get(key) + " ");
                    bw.write(map.get(key) + " ");
                    flag = true;
                    break;
                }
            }
            if(flag == false) {
//                System.out.print(0 + " ");
                bw.write(0 + " ");
            }

        }

    }
}
