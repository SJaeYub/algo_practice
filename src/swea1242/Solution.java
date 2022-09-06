package swea1242;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static ArrayList<String> hexPasswords;
    static String passwords[];
    static int sum[][];
    static ArrayList<String> sumResult;
    // 16진수
    static char hex[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    // 16진수에 매칭되는 2진수
    static String hexToBin[] = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000",
            "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
    // 0-9 값의 비율
    static String codes[] = {"3211", "2221", "2122", "1411", "1132", "1231", "1114", "1312", "1213", "3112"};
    static int result;

    // 암호코드 판별
    public static void Verification(int[] sum) {
        int n1 = 0, n2 = 0;

        for (int i = 1; i < sum.length; i++) {
            if (i % 2 == 1) n1 += sum[i];
            else if (i % 2 == 0) n2 += sum[i];
        }

        // 정상적인 암호코드라면 sumResult에 저장하고, 중복 필터
        if (((n1 * 3) + n2) % 10 == 0)
            if (!sumResult.contains(Arrays.toString(sum).replaceAll("[^0-9]", "")))
                sumResult.add(Arrays.toString(sum).replaceAll("[^0-9]", ""));
    }

    public static int Encode() {
        int cur = 0;

        // 오른쪽 끝에 채워져있는 0 제거
        for (int k = 0; k < passwords.length; k++) {
            for (int i = passwords[k].length() - 1; i > 0; i--) {
                if (passwords[k].charAt(i) != '0') {
                    passwords[k] = passwords[k].substring(0, i + 1);
                    break;
                }
            }
        }

        for (int k = 0; k < passwords.length; k++) {
            int r1 = 0, r2 = 0, r3 = 0, r4 = 0;
            int idx = 8;

            for (int i = passwords[k].length() - 1; i > 0; i--) {
                // 오른쪽부터 값 하나 추출
                char temp = passwords[k].charAt(i);

                // 값의 비율 측정
                if (temp == '1' && r3 == 0) r4++;
                else if (temp == '0' && r2 == 0) {
                    if (r4 != 0) r3++;
                    else passwords[k] = passwords[k].substring(0, i);
                }
                else if (temp == '1' && r1 == 0) r2++;
                else {
                    int mul = Math.min(Math.min(r2, r3), r4);

                    // 7의 배수로 r1 도출
                    r1 = (mul * 7) - (r2 + r3 + r4);
                    int rateSum = r1 + r2 + r3 + r4;

                    // codes에 저장된 비율로 맞춤
                    r1 /= mul;
                    r2 /= mul;
                    r3 /= mul;
                    r4 /= mul;

                    String code = "";
                    code += r1;
                    code += r2;
                    code += r3;
                    code += r4;

                    // 코드와 매칭되는 인덱스(값) 저장
                    sum[cur][idx--] = Arrays.asList(codes).indexOf(code);

                    if (idx == 0) {
                        cur++;
                        idx = 8;
                    }

                    r1 = 0; r2 = 0; r3 = 0; r4 = 0;

                    // 암호 찾은 구간까지 passwords 짜름
                    if (passwords[k].length() - rateSum > 0)
                        passwords[k] = passwords[k].substring(0, passwords[k].length() - rateSum);
                    else break;

                    i = passwords[k].length();
                }
            }
        }

        for (int i = 0; i < sum.length; i++)
            Verification(sum[i]);

        // sumResult에 들어있는 값 하나씩 모두 더함
        for (String sumStr: sumResult)
            for (int i = 0; i < 9; i++)
                result += sumStr.charAt(i) - '0';

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            hexPasswords = new ArrayList<>();
            sumResult = new ArrayList<>();
            result = 0;

            for (int i = 0; i < N; i++) {
                String str = br.readLine();

                // 동일 라인 필터
                if (hexPasswords.contains(str)) continue;

                // 0으로만 이루어진 라인 필터
                for (int k = 0; k < M; k++) {
                    if (str.charAt(k) != '0') {
                        hexPasswords.add(str);
                        break;
                    }
                }
            }

            sum = new int[10000][9];
            passwords = new String[hexPasswords.size()];

            for (int i = 0; i < passwords.length; i++) passwords[i] = "";

            // 16진수 -> 2진수
            for (int k = 0; k < hexPasswords.size(); k++) {
                for (int i = 0; i < hexPasswords.get(k).length(); i++) {
                    for (int j = 0; j < hex.length; j++) {
                        if (hexPasswords.get(k).charAt(i) == hex[j]) {
                            passwords[k] += hexToBin[j];
                        }
                    }
                }
            }

            System.out.println("#" + test_case + " " + Encode());
        }
    }
}