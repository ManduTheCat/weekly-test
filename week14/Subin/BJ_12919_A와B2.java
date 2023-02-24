import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_12919_A와B2 {

    static String S, T;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();

        backtrack(T);

        if (flag) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

    public static void backtrack(String t) {
        if (S.length() == t.length()) {
            if (S.equals(t)) {
                flag = true;
            }
            return;
        }

        if (t.charAt(t.length() - 1) == 'A') {
            // 1. 문자열 뒤에 A를 추가한다. -> 문자열 뒤에서 A를 제거한다.
            backtrack(new StringBuilder(t).deleteCharAt(t.length() - 1).toString());
        }
        if (t.charAt(0) == 'B') {
            // 2. 문자열 뒤에 B를 추가하고 문자열을 뒤집는다.
            // -> 문자열을 뒤집고 문자열 뒤에서 B를 제거한다.
            // -> 문자열 앞에서 B를 제거하고 문자열을 뒤집는다.
            backtrack(new StringBuilder(t).deleteCharAt(0).reverse().toString());
        }
    }
}
