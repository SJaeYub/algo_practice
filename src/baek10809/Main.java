package baek10809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[] arr = {'a', 'b', 'c', 'd', 'e', 'f'
            , 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'
            , 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        for (int i = 0; i < arr.length; i++) {
            int index = s.indexOf(arr[i]);
            if (index >= 0) {
                System.out.print(index + " ");
            } else {
                System.out.print(-1 + " ");
            }
        }
    }
}
