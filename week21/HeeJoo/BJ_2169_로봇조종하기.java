package saturday.sat230325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * NxM 지형
 * 왼쪽, 오른쪽, 아래쪽으로만 이동 가능
 * 한 번 탐사한 지역은 탐사 x
 * 로봇을 배열의 왼쪽 위(1, 1)에서 출발시켜, 오른쪽 아래(N, M)으로 보내려고 한다.
 * 이 때 탐사한 지역들의 가치의 합이 최대가 되도록 하는 프로그램 작성
 * 1 <= N, M <= 1,000
 * 배열의 각 수는 절댓값이 100을 넘지 않는 정수
 */
public class BJ_2169_로봇조종하기 {
    public static void main(String[] args) throws IOException {
        String str = "5 5\n" +
                "10 25 7 8 13\n" +
                "68 24 -78 63 32\n" +
                "12 -69 100 -29 -25\n" +
                "-16 -22 -57 -33 99\n" +
                "7 -76 -11 77 15";
//        BufferedReader br = new BufferedReader(new StringReader(str));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N + 1][M + 1];
        // 가치는 절댓값이 100을 넘지않는 정수 ... 최소 -100
        // 0으로 초기화하면 0을 더하는 경우가 존재하기 때문에 -100으로 설정
        for(int i = 0; i <= N; i++) Arrays.fill(dp[i], -100);

        // 첫 줄은 경로 고정
        dp[1][1] = map[1][1];
        for(int k = 2; k <= M; k++) {
            dp[1][k] = dp[1][k-1] + map[1][k];
        }

        /* 두 번째 줄부터 한 줄씩 DP 만들기
         * 1. 왼 -> 오
         * 2. 오 -> 왼
         */
        for(int i = 2; i <= N; i++) {
            int[][] tmp = new int[2][M+1]; // 임시 DP
            // 1. 왼 -> 오 : 왼쪽, 위쪽만 비교
            tmp[0][1] = dp[i-1][1] + map[i][1];
            for(int j = 2; j <= M; j++) {
                tmp[0][j] = map[i][j] + Math.max(dp[i-1][j], tmp[0][j-1]);
            }

            // 2. 오 -> 왼 : 오른쪽, 위쪽만 비교
            tmp[1][M] = dp[i-1][M] + map[i][M]; // 맨 오른쪽은 위에서만 올 수 있음
            for(int j = M-1; j > 0; j--) {
                tmp[1][j] = map[i][j] + Math.max(dp[i-1][j], tmp[1][j+1]);
            }

            for(int j = 1; j <= M; j++) {
                dp[i][j] = Math.max(tmp[0][j], tmp[1][j]);
            }
        }
//        print(dp);
        System.out.println(dp[N][M]);
    }

    public static void print(int[][] map) {
        for (int[] ints : map) {
            if(ints == null) continue;
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
