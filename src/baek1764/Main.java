package baek1764;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfDubleNo = 0;

        HashMap<String, Integer> map = new HashMap<>();
        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String no_listen = sc.next();
            map.put(no_listen, map.getOrDefault(no_listen, 0) + 1);
        }

        for (int i = 0; i < m; i++) {
            String no_seeing = sc.next();
            map.put(no_seeing, map.getOrDefault(no_seeing, 0) + 1);
        }

        for (String key : map.keySet()) {
            if(map.get(key) == 2) {
                numOfDubleNo++;
            }
        }

        System.out.println(numOfDubleNo);



        Object[] answer = map.keySet().toArray();
        Arrays.sort(answer);

        for (Object key : answer) {
            if (map.get(key) == 2) {
                System.out.println(key);
            }
        }
    }

}
