package baek14425;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            map.put(sc.next(), i);
        }

        for (int i = 0; i < m; i++) {
            if (map.containsKey(sc.next())) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}
