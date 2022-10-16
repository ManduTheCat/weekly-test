package 새터데이.sat221016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N개의 물건 ~ 각 물건은 무게 W와 가치 V
 * 최대 K만큼의 무게 제한
 * 배낭에 넣을 수 있는 물건들의 가치 최댓값을 구하는 프로그램
 */

public class BJ_12865_평범한배낭_문희주 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] goods = new int[N+1][2];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			goods[i][0] = W;
			goods[i][1] = V;
		}
		
		int[][] dp = new int[N+1][K+1];
		
		for(int i = 1; i <= N; i++) { // i번째 물건 고려
			for(int j = 1; j <= K; j++) {
				if(goods[i][0] > j) dp[i][j] = dp[i-1][j]; // 무게 초과
				else dp[i][j] = Math.max(goods[i][1] + dp[i-1][j-goods[i][0]], dp[i-1][j]);
			}
			
		}
		System.out.println(dp[N][K]);
	}

}
