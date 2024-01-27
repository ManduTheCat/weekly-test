package 새터데이.sat221008;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * NxN 격자 지역
 * 각 칸에는 카페에서 팔고 있는 디저트의 종류
 * 카페들 사이에는 대각선으로 움직일 수 있는 길 존재
 * 어느 한 카페에서 출발하여, 대각선 방향으로 움직이고 사각형 모양을 그리며 출발한 카페로 돌아오기
 * 격자를 벗어나면 안됨. 같은 종류 xxx
 * 같은 숫자의 디저트 xxx
 * 하나의 카페 xxx
 * 되돌아가기 xxx
 * 디저트를 가장 많이 먹을 수 있는 경로를 찾고, 그 때의 디저트 수를 구하는 프로그램
 * 먹을 수 없는 경우 -1
 * 
 * 1. 대각선 이동
 * 2. 사각형을 그리며 출발지로 돌아오기
 * 3. 경로에 숫자 중복 xxx
 * 4. 2개 이상의 카페 방문
 * 5. 중복 방문 xxx
 */

public class SW_2105_디저트카페_문희주 {
	static int N, sx, sy, result;
	static int[] selected;
	static int[][] map, dir = new int[][] {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		//File file = new File("res/input.txt");
		//BufferedReader br = new BufferedReader(new FileReader(file));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수 T
		for(int tc = 1; tc <= T; tc++) {
			//if(tc != 10) continue;
			N = Integer.parseInt(br.readLine()); // 지역 크기 N
			
			// 지역 정보 입력
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = -1;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					visited = new boolean[N][N];
					selected = new int[101];
					
					if((i == 0 || i == N) && (j == 0 || j == N)) continue;
					sx = i; sy = j;
					
					selected[map[i][j]] = 1;
					DFS(i, j, 0, 1, 0);
				}
			}
			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.println(sb);
	}
	
	// 방문 좌표(x, y), 먹은 양, 방향, 디저트 종류
	public static void DFS(int x, int y, int d, int count, int change) {	
		if(change == 4) return;
		if(count == 1) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
			
			if(!isRange(nx, ny)) return;
			if(selected[map[nx][ny]] == 1) return;
			
			visited[nx][ny] = true;
			selected[map[nx][ny]] = 1;
			DFS(nx, ny, d, count+1, change);
		}
		else {
			int[] ds = getDir(d);
			
			for(int tmp : ds) {
				int nx = x + dir[tmp][0];
				int ny = y + dir[tmp][1];
				
				if(nx == sx && ny == sy) {
					result = Math.max(result, count);
					return;
				}
				
				if(!isRange(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				if(selected[map[nx][ny]] == 1) continue;
				
				visited[nx][ny] = true;
				selected[map[nx][ny]] = 1;
				if(tmp == d) DFS(nx, ny, tmp, count+1, change);
				else DFS(nx, ny, tmp, count+1, change+1);
				visited[nx][ny] = false;
				selected[map[nx][ny]] = 0;
			}
		}
	}
	// {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
	public static int[] getDir(int d) {
		if(d == 0) return new int[]{0, 1};
		if(d == 1) return new int[]{1, 2};
		if(d == 2) return new int[]{2, 3};
		if(d == 3) return new int[]{3, 0};
		
		return null;
	}
	public static boolean isRange(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= N) return false;
		return true;
	}
}
