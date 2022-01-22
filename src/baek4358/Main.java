package baek4358;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean flag = true;
        int cnt = 0;
        while (flag) {
            String key = br.readLine();
            if (key == null || key.length() == 0) {
                break;
            }
            map.put(key, map.getOrDefault(key, 0) + 1);
            cnt++;
        }

        Object[] tree = map.keySet().toArray();
        Arrays.sort(tree);

        for (Object key : tree) {
            double percent = (double) map.get(key) * 100.0 / cnt;
            System.out.println(key + " " + String.format("%.4f", percent));
        }
    }

}
