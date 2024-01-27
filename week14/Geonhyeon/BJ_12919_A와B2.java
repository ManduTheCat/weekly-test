package com.kim.algoStudy;

import java.io.*;

public class BJ_12919_A와B2 {
    static String S, T;
    static int N; //길이

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        S = br.readLine();
        T = br.readLine();
        N = S.length();

        System.out.println(dfs(T) ? "1" : "0");
    }

    /*
    A랑 B붙여가면서 탐색 -> 시간초과...
    뒤집어서 하나씩 줄여가면서 탐색
     */
    static boolean dfs(String str) {
        if (str.length() == N) {            // 종료조건
            if (str.equals(S)) return true;
            return false;
        }

        if (str.charAt(str.length() - 1) == 'A') {            // A라면 A뺴고 계속
            if (dfs(str.substring(0, str.length() - 1))) {
                return true;
            }
        }

        if (str.charAt(0) == 'B') {        // B라면 B빼고 뒤집고 계속
            StringBuilder reverse = new StringBuilder();
            reverse.append(str.substring(1, str.length()));
            if (dfs(reverse.reverse().toString())) {
                return true;
            }
        }
        return false;
    }
}
