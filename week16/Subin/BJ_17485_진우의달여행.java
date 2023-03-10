import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17485_진우의달여행 {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][M + 1];
        int[][][] dp = new int[N + 2][M + 2][3];

        // dp 초기화
        for (int j = 0; j <= M + 1; j++) {
            Arrays.fill(dp[1][j], (int) 1e9);
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                // dp 초기화
                if (i == 1) {
                    for (int k = 0; k < 3; k++) {
                        dp[i][j][k] = arr[i][j];
                    }
                }
            }

            // dp 초기화
            for (int k = 0; k < 3; k++) {
                dp[i][0][k] = (int) 1e9;
                dp[i][M + 1][k] = (int) 1e9;
            }
        }

        // dp에 값 넣기
        for(int i = 2; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j][0] = arr[i][j] + Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]);
                dp[i][j][1] = arr[i][j] + Math.min(dp[i - 1][j][0], dp[i - 1][j][2]);
                dp[i][j][2] = arr[i][j] + Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]);
            }
        }

        // 최솟값 구하기
        int ans = (int) 1e9;
        for (int j = 1; j <= M; j++) {
            for (int k = 0; k < 3; k++) {
                ans = Math.min(ans, dp[N][j][k]);
            }
        }
        System.out.println(ans);
    }

}
