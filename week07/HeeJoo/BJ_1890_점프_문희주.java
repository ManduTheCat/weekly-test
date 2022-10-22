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
		long[][] dp = new long[N][N];
		dp[0][0] = 1L;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(dp[i][j] == 0) continue;
				if(i == N-1 && j == N-1) break;
				
				for(int k = 0; k < 2; k++) {
					int dx = i + dir[k][0] * map[i][j];
					int dy = j + dir[k][1] * map[i][j];
					
					if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
					dp[dx][dy] += dp[i][j];
				}
			}
		}
		
		System.out.println(dp[N-1][N-1]);
		
	}
	
	public static void print(int[][] array) {
		for(int[] arr : array) {
			for(int k : arr) {
				System.out.print(k + " ");
			}
			System.out.println();
		}
	}
}
