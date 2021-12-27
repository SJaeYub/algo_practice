package baek13305;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int[] price;
    static int[] length;
    static long n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextLong();

        price = new int[(int) n];
        length = new int[(int) (n - 1)];

        for (int i = 0; i < n - 1; i++) {
            length[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            price[i] = sc.nextInt();
        }

        ArrayList<Integer> station_list = select_gas(price[0]);

        int curr = 0;
        int ans = 0;
        while (curr < n) {
            int road = 0;
            int price_mid = 0;

            if (station_list.isEmpty()) {
                for (int i = curr; i < n - 1; i++) {
                    road += length[i];
                }
                price_mid = road * price[curr];
                ans += price_mid;

                System.out.println(ans);
                break;
            }

            Integer next_station = station_list.remove(0);

            for (int i = curr; i < next_station; i++) {
                road += length[i];
            }
            price_mid = road * price[curr];
            ans += price_mid;

            curr = next_station;
        }
    }

    private static ArrayList<Integer> select_gas(int start_price) {
        ArrayList<Integer> gas_station = new ArrayList<>();

        for (int i = 0; i < price.length; i++) {
            if (price[i] < start_price && i != 0 && i != n - 1) {
                gas_station.add(i);
                start_price = price[i];
            }
        }

        return gas_station;
    }
}
