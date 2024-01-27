import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_20437_문자열게임2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {

            String str = br.readLine();
            int K = Integer.parseInt(br.readLine());

            // K가 1이면 어떤 문자열이 와도 최대, 최소 길이는 1
            if(K == 1) {
                System.out.println(1 + " " + 1);
                continue;
            }
            // 알파벳을 카운팅할 배열 생성
            int[] alpha = new int[26];
            
            // 알파벳 카운트
           for(int j = 0; j < str.length(); j++) {
               alpha[str.charAt(j) - 'a']++;
           }
            
           int max = Integer.MIN_VALUE;
           int min = Integer.MAX_VALUE;
            for(int j = 0; j < str.length(); j++) {
                
               int count = 1;
               // 알파벳 개수가 K보다 작으면 continue
               if(alpha[str.charAt(j) - 'a'] < K) {
                   continue;
               }
               
               for(int c = j + 1; c < str.length(); c++) {
                   // 시작지점과 같은 알파벳이면 count 증가
                   if(str.charAt(j) == str.charAt(c)) {
                       count++;
                   }
                   // count가 K와 같다는것은 조건을 만족한 경우이기 때문에 최대, 최소 문자열 갱신
                   if(count == K) {
                       min = Math.min(min, c - j + 1);
                       max = Math.max(max, c - j + 1);
                       break;
                   }
               }

           }
           if(min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
               System.out.println(-1);
           }else {
               System.out.println(min + " " + max);
           }
        }


    }

}
