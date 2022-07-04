package baek20291;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static HashMap<String, Integer> hMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), ".");
            st.nextToken();
            String str = st.nextToken();
            if(hMap.containsKey(str)) {
                int cnt = hMap.get(str);
                cnt++;
                hMap.put(str, cnt);
            } else {
                hMap.put(str, 1);
            }
        }

        String[] ans = new String[hMap.size()];
        Set<String> strings = hMap.keySet();
        int cnt = 0;
        for (String string : strings) {
            int integer = hMap.get(string);
            ans[cnt] = string + " " + integer;
            cnt++;
        }

        Arrays.sort(ans);

        for (String an : ans) {
            System.out.println(an);
        }
    }
}
