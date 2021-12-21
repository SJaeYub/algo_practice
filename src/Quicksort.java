import java.util.Arrays;
import java.util.Random;

public class Quicksort {

    static Random rd = new Random();
    static int n = 10000;
    static int[] s = new int[n];
    static int pivotpoint = 0;
    static int amountOfComputation = 0;

    public static void main(String[] args) {

        long beforeTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
//            s[i] = rd.nextInt(n);
            s[i] = i;
        }

//        int temp2 = s[0];
//        s[0] = s[n/2];
//        s[n/2] = s[0];
        int[] temp = {s[0], s[n/2], s[n-1]};
        Arrays.sort(temp);
        for(int i = 0; i < 3; i++) {
            if(temp[1] == s[0]) {
                pivotpoint = 0;
                break;
            }
            else if(temp[1] == s[n/2]) {
                pivotpoint = n/2;
                break;
            }
            else if(temp[1] == n-1) {
                pivotpoint = n-1;
                break;
            }
        }
        System.out.println("pivotpoint = " + pivotpoint);

        int sum = 0;
        System.out.println("beforeTime = " + beforeTime + " ms");
        quicksort(0, s.length - 1);

        long afterTime = System.currentTimeMillis();
        System.out.println("totalTime = " + (afterTime - beforeTime) + " ms");
        System.out.println("amountOfComputation = " + amountOfComputation);
        long totalTime = afterTime - beforeTime;
    }

    static void partition(int low, int high) {
        int i, j;
        int pivotitem;

        pivotitem = s[low];
        j = low;
        for (i = low + 1; i <= high; i++) {
            if (s[i] < pivotitem) {
                amountOfComputation++;
                j++;
                int temp = s[i];
                s[i] = s[j];
                s[j] = temp;
            }
        }
        pivotpoint = j;
        int temp = s[low];
        s[low] = s[pivotpoint];
        s[pivotpoint] = temp;
    }

    static void quicksort(int low, int high) {

        if (high > low) {
            partition(low, high);
            quicksort(low, pivotpoint - 1);
            quicksort(pivotpoint + 1, high);
        }
    }

    static float getMedian(int[] arrayInt) {
        // TODO Auto-generated method stub
        Arrays.sort(arrayInt);//오름차순 정렬
        int size = arrayInt.length;
        float result;
        if (size % 2 == 0) { //배열크기가 짝수일경우
            int m = size / 2;
            int n = size / 2 - 1;
            result = (float) (arrayInt[m] + arrayInt[n]) / 2; //중앙값 2개의  평균
        } else { //배열크기가 홀수인경우
            int m = size / 2;
            result = arrayInt[m]; //중앙값
        }

        return result;

    }
}
