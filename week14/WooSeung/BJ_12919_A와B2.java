import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_12919_A와B2 {
    static String S, T;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();

        if(dfs(T)) {
            System.out.println("1");
        }else {
            System.out.println("0");
        }

    }

    static boolean dfs(String str) {
        // 길이가 같으면 더 이상 연산을 할 필요가 없으므로 문자열 확인 후 종료
        if(str.length() == S.length()) {
            if(str.equals(S)) {
                return true;
            }
            return false;
        }
        // 맨 뒤가 A라면 A를 제거
        if(str.charAt(str.length() - 1) == 'A') {
            if(dfs(str.substring(0, str.length() - 1))) {
                return true;
            }
        }
        // 맨 앞이 B라면 B를 빼고 역순으로 변경
        if(str.charAt(0) == 'B') {
            StringBuilder reverse = new StringBuilder();
            reverse.append(str.substring(1, str.length()));
            if(dfs(reverse.reverse().toString())) {
                return true;
            }
        }
        return false;
    }

}
