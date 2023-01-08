package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1157단어공부 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int max = 0;
        int[] arr = new int[26];

        String str = br.readLine();
        for(int i = 0; i < str.length(); i++) {
            if('A' <= str.charAt(i) && 'Z' >= str.charAt(i)) {
                arr[str.charAt(i) - 65]++;
            }else {
                arr[str.charAt(i) - 97]++;
            }
        }
        char ch =  '?';
        for(int i= 0; i < arr.length; i++) {
            if(max < arr[i]) {
                max = arr[i];
                ch = (char) (i + 65);
            }else if(max == arr[i]) {
                ch = '?';
            }
        }

        System.out.println(ch);

    }

}
