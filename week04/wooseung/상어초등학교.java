package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class BJ_21608_상어초등학교 {

	static int N;
	static int[][] map;
	static int[] number;
	static List<Integer>[] list;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		number = new int[N * N + 1];
		list = new ArrayList[N * N + 1];
		
		for(int i = 0; i < N * N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		
		
		for(int i = 1; i <= N * N; i++) {
			st = new StringTokenizer(br.readLine());
			number[i] = Integer.parseInt(st.nextToken());
			for(int j = 0; j < 4; j++) {
				list[number[i]].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i = 1; i <= N * N; i++) {	
			step(number[i]);	
		}
		
		System.out.println(ans());
		
	}
	
	static void step(int num) {
		
		int x = 0, y = 0, nearCount = -1, likeCount = -1;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				
				if(map[i][j] != 0) {
					continue;
				}
				
				int near = 0;
				int like = 0;
				
				for(int d = 0 ; d  < 4; d++) {
					
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(check(nx, ny)) {
						if(map[nx][ny] == 0) {
							near++;
						}
						if(list[num].contains(map[nx][ny])) {
							like++;
						}
						
					}
					if(like > likeCount || (like == likeCount && near > nearCount)) {
						x = i;
						y = j;
						nearCount = near;
						likeCount = like;
					}
					
				}			 
			}
		}
		map[x][y] = num;
	}
	
	static int ans() {
		
		int ans = 0;
		int arr[] = {0, 1, 10, 100, 1000};
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				
				int num = map[i][j];
				int like = 0;
				
				for(int d = 0; d < 4; d++) {
					
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if(check(nx, ny)) {
						if(list[num].contains(map[nx][ny])) {
							like++;
						}		
					}
				}
				ans += arr[like];
			}
			
		}
		
		
		return ans;
	}
	
	static boolean check(int x, int y) {
		
		if(x > 0 && x <= N && y >0 && y <= N) {
			return true;
		}
		
		return false;
	}

}
