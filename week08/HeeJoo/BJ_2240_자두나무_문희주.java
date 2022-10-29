package 새터데이.sat221029;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 매 초마다, 두 개의 나무 중 하나의 나무에서 열매 떨어짐
 * 열매가 떨어지는 순간, 그 아래 서 있으면 열매먹기 가능
 * 나무간 이동 시간은 x but 이동 횟수 제한 있음
 * T초 동안 자두가 떨어질 때, W번 움직임이 가능
 * 받을 수 있는 자두의 개수를 구하는 프로그램
 * 
 * 시작은 항상 1번 나무
 */

public class BJ_2240_자두나무_문희주 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken()); // 떨어지는 시간
		int W = Integer.parseInt(st.nextToken()); // 움직임 횟수
		
		int[] arr = new int[T+1]; // idx초에 자두가 떨어지는 나무의 번호. 0인덱스 x
		for(int i = 1; i <= T; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 행 : 점프 횟수
		// 열 : 시간
		// 기본틀 : dp[i][j] = Math.max(dp[i-1][j-1], dp[i][j-1]) ~ but 나무 상태에 따라 다름
		// dp[i-1][j-1] : 다른 나무에서 점프
		// dp[i][j-1] : 기존 나무
		// 점프횟수 홀수 ~ 2번나무, 짝수 ~ 1번나무
		int[][] dp = new int[W+1][T+1];
		
		// 초기 테이블 : 0번 뛴 경우
		// 시작 나무 : 1번
		for(int i = 1; i <= T; i++) {
			if(arr[i] == 1) dp[0][i] = dp[0][i-1] + 1; // 1번 나무
			else dp[0][i] = dp[0][i-1]; // 2번 나무
		}
		
		for(int i = 1; i <= W; i++) {
			for(int j = 1; j <= T; j++) {
				if(i % 2 == 0) { // 1번 나무
					if(arr[j] == 1) {
						dp[i][j] = Math.max(dp[i-1][j-1], dp[i][j-1]) + 1;
					} else {
						dp[i][j] = Math.max(dp[i-1][j-1], dp[i][j-1]);
					}
				} else { // 2번 나무
					if(arr[j] == 2) {
						dp[i][j] = Math.max(dp[i-1][j-1], dp[i][j-1]) + 1;
					} else {
						dp[i][j] = Math.max(dp[i-1][j-1], dp[i][j-1]);
					}
				}
			}
		}
		
		// 꼭 T번 안뛰어도 됨 ~ 최대 횟수가 T번일 뿐...
		// N번 뛰었을 때의 결과들만 비교
		int result = 0;
		for(int i = 0; i <= W; i++) result = Math.max(result, dp[i][T]);
		System.out.println(result);
	}
	
	public static void print(int[][] array) {
		for(int[] arr : array) {
			for(int k : arr) {
				System.out.print(k + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
