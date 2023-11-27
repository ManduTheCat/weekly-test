package saturday.sat230107;

/* 대소문자 구분없이 가장 많이 사용된 알파벳 찾기
 * 가장 많이 사용된 알파벳이 여러 개 존재하는 경우 "?" 출력
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1157_단어공부 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 출력을 대문자로 해서 전부 대문자로 바꿔줌 .. 아니면 아래 for문에서 소문자, 대문자 다르게 로직처리하면 됨
        String str = br.readLine().toUpperCase();
        int[] count = new int[26];

        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            count[(int)(c - 'A')]++;
        }

        int ans = 0; // 알파벳 번호
        int max = 0; // 최대 출현 횟수
        for(int i = 0; i < 26; i++) {
            if(max < count[i]) { // 최대 출현 횟수 갱신
                ans = i;
                max = count[i];
            } else if(max == count[i]) { // 가장 많이 사용된 알파벳이 여러 개 존재
                ans = -1;
            }
        }

        if(ans == -1) {
            System.out.println("?");
        } else {
            System.out.println((char)('A' + ans));
        }
    }
}
