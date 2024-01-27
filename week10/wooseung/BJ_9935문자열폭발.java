package baekjoon;

import java.io.*;

public class BJ_9935문자열폭발 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        String boom = br.readLine();

        char[] result = new char[str.length()];
        int idx = 0;    // 폭탄과 비교할 인덱스
        for(int i = 0; i < str.length(); i++) {
            result[idx] = str.charAt(i);
            if(check(result, idx, boom)) {
                idx -= boom.length();   // 터진 폭탄의 문자열 길이만큼 인덱스 감소
            }
            idx++;  // 인덱스 증가
        }

        String ans = String.valueOf(result, 0, idx);    // 남은 문자열 ans에 저장
        System.out.println(ans.length() == 0 ? "FRULA" : ans);  // 문자열의 길이가 0이면 FRULA 출력, 아니면 ans 출력
    }

    static boolean check(char[] result, int idx, String boom) {
        if(idx < boom.length() - 1) { // 폭탄 길이보다 작은면 false 리턴
            return false;
        }
        for(int i = 0; i < boom.length(); i++) {    // 아니면 검사 시작
            if(boom.charAt(i) != result[idx - boom.length() + 1 + i]) { //  폭탄 문자열과 틀리면 false리턴
                return false;
            }
        }
        return true;    // 폭탄문자열과 같으면 true 리턴
    }
}
