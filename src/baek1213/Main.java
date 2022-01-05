package baek1213;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int[] abc = new int[26];
        for (int i = 0; i < str.length(); i++) {
            abc[str.charAt(i) - 'A']++;
        }

        int oddCnt = 0;
        int oddIdx = 0;
        for (int i = 0; i < abc.length; i++) {
            if (abc[i] % 2 == 1) {
                oddCnt++;
                oddIdx = i;
            }
        }

        if (str.length() % 2 == 0 && oddCnt > 0) {
            System.out.println("I'm Sorry Hansoo");
            return;
        } else if (str.length() % 2 == 1 && oddCnt != 1) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        for (int i = 0; i < abc.length; i++) {
            for (int j = 0; j < abc[i] / 2; j++) {
                System.out.printf("%c", i + 'A');
            }
        }

        if (str.length() % 2 == 1) {
            System.out.printf("%c", oddIdx + 'A');
        }

        for (int i = 25; i >= 0; i--) {
            for (int j = 0; j < abc[i] / 2; j++) {
                System.out.printf("%c", i + 'A');
            }
        }
    }
}
