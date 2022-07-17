package swea5658;

import java.util.*;
import java.io.*;

/*
0회전부터 N회전까지 각변에서 만들어지는 모든 수를 배열리스트에 담아 정렬한 다음에 반복문에 넣고 같은 값이 있으면 지운다 -> 이후 k 인덱스의 값 출력
0회전부터 3회전까지
 */
public class Main {

    static char[] arr;
    static int n, k;
    static ArrayList<Integer> arrayList = new ArrayList<>();
    static int numOfBox;
    static String hexNum;

    public static void main(String[] args) throws Exception {

//        System.setIn(new FileInputStream("src/swea5658/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            arrayList = new ArrayList<>();
            n = sc.nextInt();
            numOfBox = n / 4;
            k = sc.nextInt();
            arr = new char[n];
            hexNum = sc.next();

            for (int i = 0; i < n; i++) {
                arr[i] = hexNum.charAt(i);
            }

//            한변을 구성하는 문자의 수 -> n/4;

//n/4 - 1 만큼 회전하면됨
            tranforming(arr);      //0회전일때
            for (int i = 0; i < numOfBox - 1; i++) {
                rotate();
                tranforming(arr);
//                16진수 10진수로 바꿔서 배열에 저장 완료
//                System.out.println(String.valueOf(arr));

//                if (hexNum.equals(String.valueOf(arr))) {
//                    break;
//                }
            }

            Collections.sort(arrayList);

            for (int i = 0; i < arrayList.size(); i++) {
                if (i < arrayList.size() - 1) {
                    if (arrayList.get(i).equals(arrayList.get(i + 1))) {
//                        System.out.println("!!");
                        arrayList.remove(i + 1);
                    }
                }
            }


            System.out.println("#" + test_case + " " + arrayList.get(arrayList.size() - k));
        }

    }

    static void rotate() {
        char[] temp = new char[n];
        for (int i = 0; i < temp.length; i++) {
            if (i != 0) {
                temp[i] = arr[i - 1];
            }
            if (i == 0) {
                temp[0] = arr[temp.length - 1];
            }
        }

        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i];
        }
        return;
    }

    static void tranforming(char[] args) {
//        System.out.println("--------------------");
        String temp2 = String.valueOf(args);
        for (int i = 0; i < 4; i++) {
            String temp = temp2.substring(i * numOfBox, i * numOfBox + numOfBox);
            int temp_int = Integer.parseInt(temp, 16);
            arrayList.add(temp_int);
//            System.out.println("temp_int = " + temp_int + " temp = " + temp);
        }
        return;
    }
}
