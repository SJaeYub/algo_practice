package baek1541;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String tempS = sc.next();
        int sum = Integer.MAX_VALUE;
        StringTokenizer st_sub = new StringTokenizer(tempS, "-");

        while (st_sub.hasMoreTokens()) {
            int temp = 0;

            StringTokenizer st_add = new StringTokenizer(st_sub.nextToken(), "+");

            while (st_add.hasMoreTokens()) {
                temp += Integer.parseInt(st_add.nextToken());
            }

            if (sum == Integer.MAX_VALUE) {
                sum = temp;
            } else {
                sum -= temp;
            }
        }
        System.out.println(sum);
    }

}
