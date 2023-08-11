package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1300_K번째수 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        long left = 1;
        long right = K;
        long ans = 0;
        // 이분 탐색 시작
        while(left <= right) {
            
            // 이분 탐색을 위한 중간값 설정
            long mid = (left + right) / 2;
            long cnt = 0;
            
            
            // N X N을 이차원 배열로 한다면 1행은 1단, 2행은 2단 ...
            // 각 행에 mid보다 작거나 같은 수의 개수를 모두 cnt에 더 해준다.
            for(int i = 1; i <= N; i++) {
                cnt += Math.min(mid/i, N);
            }

            if(cnt < K) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }

        }

        System.out.println(ans);

    }


}
