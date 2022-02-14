package skPlanetTest2;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class MaxSum {
    public static int findMaxSum(List<Integer> list) {
        int result = 0;

        if(list.size() >= 2) {
            Collections.sort(list);
            int final_index = list.size() - 1;

            result = list.get(final_index) + list.get(final_index - 1);
        } else {
            result = list.get(0);
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5, 9, 7, 11);
        // Should return 20, since 9 and 11 are the largest elements
        // and their sum is 20
        System.out.println(findMaxSum(list));
    }
}