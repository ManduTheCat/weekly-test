package saturday.sat230311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/*
블로그 시작 N일차..
X일 동안 가장 많이 들어온 방문자 수와 기간이 몇 개 있는지 .. 알아보자 :^)
 */
public class BJ_21921_블로그 {
    public static void main(String[] args) throws IOException {
//        String src = "5 2\n" +
//                "1 4 2 5 1";
//
//        BufferedReader br = new BufferedReader(new StringReader(src));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 블로그 N일차
        int X = Integer.parseInt(st.nextToken()); // 기간 X

        // 방문자 수 기록
        int[] history = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            history[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        int cnt = 0;
        int sum = 0;

        for(int i = 0; i < X; i++){
            sum += history[i];

            if(sum > max) {
                max = sum;
                cnt = 1;
            }
        }

        for(int i = 0; i < N-X; i++){
            sum -= history[i];
            sum += history[i+X];

            if(sum > max) {
                max = sum;
                cnt = 1;
            } else if(sum == max) cnt++;
        }

        if(max == 0) sb.append("SAD");
        else {
            sb.append(max + "\n" + cnt);
        }

        System.out.println(sb);
    }
}
