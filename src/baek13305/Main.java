package baek13305;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int[] price;
    static int[] length;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        price = new int[n];
        length = new int[n - 1];

        for (int i = 0; i < n - 1; i++) {
            length[i] = sc.nextInt();
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            price[i] = sc.nextInt();
            sum += price[i];
        }

        int avg_price = sum / n;

        ArrayList<Integer> station_list = select_gas(avg_price);

        int ans = 0;
        int curr = 0;
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

            Integer station_idx = station_list.remove(0);

            for (int i = 0; i < station_idx; i++) {
                road += length[i];
            }

            price_mid = road * price[curr];

            ans += price_mid;

            curr = station_idx;
        }
    }

    private static ArrayList<Integer> select_gas(int avg) {
        ArrayList<Integer> gas_station = new ArrayList<>();

        for (int i = 0; i < price.length; i++) {
            if (price[i] < avg && i != 0 && i != n - 1) {
                gas_station.add(i);
            }
        }

        return gas_station;
    }
}
