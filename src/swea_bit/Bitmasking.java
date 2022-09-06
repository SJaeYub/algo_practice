package swea_bit;

public class Bitmasking {

    public static void main(String[] args) {
        int[] a_friends = {0, 3, 6, 7, 10, 13, 28};
        int[] b_friends = {0, 1, 4, 5, 6, 17, 21, 28};

        int[] a_bit_arr = new int[29];
        int[] b_bit_arr = new int[29];

        for (int i = 0; i < a_friends.length; i++) {
            a_bit_arr[a_friends[i]] = 1;
        }
        for (int i = 0; i < b_friends.length; i++) {
            b_bit_arr[b_friends[i]] = 1;
        }

        int[] bitmasking = bitmasking(a_bit_arr, b_bit_arr);
        for (int i = 0; i < bitmasking.length;i++) {
            if (bitmasking[i] == 1) {
                System.out.println(i);
            }

        }
    }

    private static int[] bitmasking(int[] a_arr, int[] b_arr) {
        int[] result = new int[29];

        for (int i = 0; i <= 28; i++) {
            result[i] = a_arr[i] | b_arr[i];
        }

        return result;
    }
}
