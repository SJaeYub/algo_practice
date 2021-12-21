package baek9655;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        System.out.println((n % 2 == 1) ? "SK" : "CY");
    }
}
