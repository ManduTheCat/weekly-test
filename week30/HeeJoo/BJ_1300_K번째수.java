package saturday.sat230701;

/*
 * NxN 배열 A
 * A[i][j] = i * j
 * 일차원 배열 B에 넣으면 B의 크기는 NxN
 * B를 오름차순 정렬했을 때, B[k] 구하기
 * 해설 참고 : https://st-lab.tistory.com/281
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 이분 탐색 사용
 * 배열 A는 i행의 배수 배열 ~ i행은 오름차순
 * B[K] ~ 앞에 자신보다 작은 K-1개의 수 존재
 * 각 행에서 임의의 수 X보다 작은 수의 개수 구하기 -> 총 개수를 K와 비교
 * 총 개수가 K보다 작은 경우 ... B[K]는 X보다 크다.
 * 총 개수가 K와 같은 경우 ... B[K]는 X이다.
 * 총 개수가 K보다 큰 경우 ... B[K]는 X보다 작다.
 */
public class BJ_1300_K번째수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        // 이분 탐색으로 임의의 수 X 선택
        int lo = 1;
        int hi = K;

        while(lo < hi) {
            int mid = (lo + hi) / 2; // 임의의 수 X
            int count = 0; // X보다 작은 수의 개수

            for(int i = 1; i <= N; i++) {
                // 각 행은 행 index의 배수 배열 ...
                // X를 행 index로 나눈 몫만큼 X보다 작은 수 존재
                // BUT N개를 초과할 수 없음
                // ex) N = 4, X = 13인 경우 ~ 1행에서 X보다 작은 수는 13/1 = 13이 아닌 N개가 최대이다.
                count += Math.min(mid / i, N);
            }

            // Lower-Bound 사용
            // count > K : 임의의 수 X보다 작은 수가 B[K]보다 많다는 뜻
            if(K <= count) { // count가 더 크면 hi를 낮춰 K 줄이기
                hi = mid;
            } else { // count가 더 작으면 lo를 높여 K 늘리기
                lo = mid + 1;
            }
        }
        System.out.println(lo);
    }
}
