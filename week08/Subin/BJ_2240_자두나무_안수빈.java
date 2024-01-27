import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2240_자두나무_안수빈 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[T];
		for (int i = 0; i < T; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[][] dp = new int[T][W + 1];
		if (arr[0] == 1) dp[0][0] = 1;
		else dp[0][1] = 1;
		
		for (int i = 1; i < T; i++) {
			dp[i][0] = dp[i - 1][0];
			if (arr[i] == 1) dp[i][0] += 1;
			for (int j = 1; j <= W; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
				if (j % 2 == arr[i] - 1) dp[i][j] += 1;
			}
		}
		
		int ans = 0;
		for (int i = 0; i <= W; i++) {
			ans = Math.max(ans, dp[T - 1][i]);
		}
		System.out.println(ans);
	}

}
