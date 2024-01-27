package 새터데이.sat221022;

import java.io.*;
import java.util.*;

/*
 * NxN 게임판
 * 가장 왼쪽 위 칸에서 가장오른쪽 아래 칸으로 규칙에 맞게 점프를 해서 가기
 * 각 칸에 적혀있는 수 : 현재 칸에서 갈 수 있는 거리
 * 반드시 오른쪽 or 아래쪽으로만 이동
 * 0 : 종착점
 * 항상 현재 칸에 적혀있는 수만큼 오른쪽이라 아래로 가야 함
 * 한 번 점프를 할 때, 방향 바꾸기 x
 * 가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 규칙에 맞게 이동할 수 있는 경로의 개수를 구하는 프로그램
 */

public class BJ_1890_점프_문희주 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int[][] dir = new int[][] {{0, 1}, {1, 0}}; // 하우
		long[][] dp = new long[N][N]; // 조건 : 경로의 개수는 263-1보다 작거나 같다.
		dp[0][0] = 1L; // 시작지점
		
		// 좌>우, 상>하 순서대로 dp 테이블 갱신
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(dp[i][j] == 0) continue; // 해당 칸에 가는 방법이 0인 경우 continue
				if(i == N-1 && j == N-1) break; // 종착지 도착
				
				// 우, 하 방향으로 dp 테이블 갱신
				for(int k = 0; k < 2; k++) {
					int dx = i + dir[k][0] * map[i][j];
					int dy = j + dir[k][1] * map[i][j];
					
					if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue; // 범위 체크
					dp[dx][dy] += dp[i][j]; // (dx, dy)에 갈 수 있는 경우의 수 더하기
				}
			}
		}
		
		System.out.println(dp[N-1][N-1]); // 종착지까지 갈 수 있는 경우의 수
		
	}
	
	// 디버깅용
	public static void print(int[][] array) {
		for(int[] arr : array) {
			for(int k : arr) {
				System.out.print(k + " ");
			}
			System.out.println();
		}
	}
}
