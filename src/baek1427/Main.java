package baek1427;

import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String num = sc.next();

        char[] num_tmp = new char[num.length()];
        for (int i = 0; i < num.length(); i++) {
            num_tmp[i] = num.charAt(i);
        }

        Arrays.sort(num_tmp);
        for (int i = num.length() - 1; i >= 0; i--) {
            System.out.print(num_tmp[i]);
        }
    }
}
