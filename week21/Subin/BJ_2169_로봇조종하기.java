import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2169_로봇조종하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] value = new int[n + 1][m + 2];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				value[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[n + 1][m + 2];
		// dp 초기화
		for (int i = 1; i <= m; i++) dp[1][i] = dp[1][i - 1] + value[1][i];
		
		for (int i = 2; i <= n; i++) {
			// 왼쪽에서 올 때의 최대 가치 구하기
			int[] left = new int[m + 2];
			left[0] = (int) -2e9;
			for (int j = 1; j <= m; j++) {
				left[j] = Math.max(dp[i - 1][j], left[j - 1]) + value[i][j];
			}
			
			// 오른쪽에서 올 때의 최대 가치 구하기
			int[] right = new int[m + 2];
			right[m + 1] = (int) -2e9;
			for (int j = m; j >= 1; j--) {
				right[j] = Math.max(dp[i - 1][j], right[j + 1]) + value[i][j];
			}
			
			// 위에서 올 때와 왼쪽에서 올 때, 오른쪽에서 올 때 중 최대 가치 구하기
			for (int j = 1; j <= m; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], Math.max(left[j - 1], right[j + 1])) + value[i][j];
			}
		}
		
		// (0, 0)에서 (n, m)으로 가는 최대 가치의 합
		System.out.println(dp[n][m]);
	}
	
}
