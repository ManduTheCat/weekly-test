import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 슬라이딩 윈도우
// X일 동안 가장 많이 들어온 방문자 수 출력
// 0명이라면 SAD 출력
// 최대 방문자 수가 0명이 아닌 경우 둘째 줄에 기간이 몇 개 있는지 출력
public class BJ_21921_블로그 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int ans = Integer.MIN_VALUE;
        int count = 1;
        int sum = 0;
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < X; i++) {
            sum += arr[i];
        }

        ans = sum;
        // 슬라이딩 윈도우 
        // 첫번째 요소 제거 후 그 다음 요소 추가
        for(int i = X; i <N; i++) {
            sum -= arr[i - X];
            sum += arr[i];
            
            // 최대 방문자 수 갱신, 기간 횟수 1로 초기화
            if(ans < sum) {
                count = 1;
                ans = sum;
                // 기간 횟수 증가;
            }else if(ans == sum) {
                count++;
            }
        }
        // 최대 방문자 수가 0이면 SAD 출력
        if(ans == 0) {
            System.out.println("SAD");
            // 최대 방문자 수와 기간 횟수 출력
        }else {
            System.out.println(ans);
            System.out.println(count);
        }
    }

}
