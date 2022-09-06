package swea_bit;

public class Main {


    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D'};
        printSubsets(data, 4);
        return;
    }

    private static void printSubsets(char[] arr, int n) {
        for (int i = 0; i < (1 << n); ++i) {
            System.out.printf("{");
            for (int j = 0; j < n; ++j) {
                if ((i & (1 << j)) == (1 << j)) {
                    System.out.printf("%c", arr[j]);
                }
            }
            System.out.print("}\n");
        }
    }
}
