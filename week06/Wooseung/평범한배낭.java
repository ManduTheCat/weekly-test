import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_12865_평범한배낭_이우승 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] weight = new int[N + 1];
        int[] value = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][K + 1];

        for(int k = 1; k <= K; k++) {
            for(int i = 1; i <= N; i++) {

                dp[i][k] = dp[i - 1][k];
                if (k - weight[i] >= 0) {
                    dp[i][k] = Math.max(dp[i - 1][k], value[i] + dp[i - 1][k - weight[i]]);
                }
            }
        }

        System.out.println(dp[N][K]);

    }

}
