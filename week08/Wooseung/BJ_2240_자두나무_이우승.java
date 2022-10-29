package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2240_자두나무_이우승 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int ans = 0;
		
		int[][] dp = new int[T + 1][W + 1];
		
		for(int i = 1; i <= T; i++) {
			
			int tree = Integer.parseInt(br.readLine());
			
			for(int j = 0; j <= W; j++) {
				// 움직이지 않을 때
				if(j == 0) {
					if(tree == 1) {
						dp[i][j] = dp[i - 1][j] + 1;	
					}else {
						dp[i][j] = dp[i - 1][j];
					}
					continue;
				}
				// 자두의 초기 위치는 1번 나무에 있는데 홀수번 움직이게 된다면 자두의 위치는 2번 나무로 바뀐다.
				if(j%2 == 0) {
					if(tree == 1) {
						dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j] + 1);
					}else {
						dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i - 1][j]);
					}
				}else {	// 짝수 번 움직인다면 원래 위치인 1번 나무도 돌아온다.
					if(tree == 1) {
						dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i - 1][j]);
					}else {
						dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j] + 1);
					}
				}
				
			}
			
		}
		System.out.println(dp[T][W]);
	}

}
