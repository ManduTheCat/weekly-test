package Sat_230223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

// A와 B로만 이루어진 영어 단어
// 두 문자열 S와 T가 주어졌을 때, S를 T로 바꾸는 게임
// 연산 1. 문자열의 뒤에 A 추가
// 연산 2. 문자열의 뒤에 B를 추가하고 문자열 뒤집기
// 주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 판별
public class BJ_12919_A와B2 {
    static boolean ans = false;
//    static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
//        String src = "A\n" +
//                "BABA";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(src));

        String S = br.readLine();
        String T = br.readLine();

        dfs(S, T);

        if(ans) System.out.println(1);
        else System.out.println(0);

    }

    public static void dfs(String S, String T){
        if(ans || S.length() > T.length()) return;
        if(T.equals(S)) {
            ans = true;
            return;
        }

        if(T.charAt(T.length()-1) == 'A') dfs(S, func1(T));
        if(T.charAt(0) == 'B') dfs(S, func2(T));

    }
    // 연산 1
    public static String func1(String t){
        return t.substring(0, t.length()-1);
    }

    // 연산 2
    public static String func2(String t){
        String result = "";
        for(int i = t.length() - 1; i > 0; i--){
            result += t.charAt(i);
        }
        return result;
    }
}
