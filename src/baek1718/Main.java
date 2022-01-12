package baek1718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'
            , 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q'
            , 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};     //26개

    static StringTokenizer st;
    static char[] pwd_arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String normal_str = br.readLine();
        String password = br.readLine();

        pwd_arr = new char[normal_str.length()];
        for (int i = 0; i < pwd_arr.length; i++) {
            pwd_arr[i] = password.charAt(i % password.length());
        }

        char[] answer = new char[normal_str.length()];

        for (int i = 0; i < normal_str.length(); i++) {
            if (normal_str.charAt(i) != ' ') {
                answer[i] = passwarding(normal_str.charAt(i), i);
            } else {
                answer[i] = ' ';
            }
        }

        System.out.println(new String(answer));
    }

    static char passwarding(char temp, int idx) {
        int key = 0;
        int temp_key = 0;
        char keyChar = pwd_arr[idx];

        for (int i = 0; i < alphabet.length; i++) {
            if (keyChar == alphabet[i]) {
                key = i + 1;
            }
            if (alphabet[i] == temp) {
                temp_key = i + 1;
            }
        }

        if (temp_key > key) {
            return alphabet[temp_key - key - 1];
        } else if (temp_key == key) {
            return alphabet[25];
        } else {
            int minus_remain = key - temp_key;
            return alphabet[26 - minus_remain - 1];
        }
    }
}
