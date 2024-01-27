package 새터데이.sat230114;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 불에 타기 전에 탈출할 수 있는지의 여부, 그리고 얼마나 빨리 탈출할 수 있는지를 결정
 * 지훈이와 불은 매 분마다 한 칸씩 수평 또는 수직으로 이동
 * 불은 각 지점에서 네 방향으로 확산
 * 미로의 가장자리에 접한 공간에서 탈출 가능
 * 벽이 있는 공간은 통과 x
 */

public class BJ_4179_불 {
	static int R, C;
	static int[][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		
		map = new char[R][C];
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int result = BFS();
		
		if(result == -1) sb.append("IMPOSSIBLE");
		else sb.append(result);
		
		System.out.println(sb);
	}
	
	/*
	 * 1. 불 확산
	 * 2. 이동
	 */
	public static int BFS() {
		Queue<Point> fire_q = new ArrayDeque<>();
		Queue<Point> move = new ArrayDeque<>();
		int time = 0;
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == 'F') fire_q.offer(new Point(i, j));
				else if(map[i][j] == 'J') move.offer(new Point(i, j));
			}
		}
		
		while(fire_q.size() != 0 || move.size() != 0) {
			time++;
			// 1. 불 확산
			int size = fire_q.size();
			while(size-- > 0) {
				Point fire = fire_q.poll();
				int x = fire.x;
				int y = fire.y;
				
				for(int i = 0; i < 4; i++) {
					int dx = x + dir[i][0];
					int dy = y + dir[i][1];
					
					if(dx < 0 || dx >= R || dy < 0 || dy >= C) continue;
					if(map[dx][dy] == '.' || map[dx][dy] == 'J') {
						fire_q.offer(new Point(dx, dy));
						map[dx][dy] = 'F';
					}
				}
			}
			
			// 2. 이동
			size = move.size();
			if(size == 0) return -1;
			while(size-- > 0) {
				Point now = move.poll();
				int x = now.x;
				int y = now.y;
				
				for(int i = 0; i < 4; i++) {
					int dx = x + dir[i][0];
					int dy = y + dir[i][1];
					
					if(dx < 0 || dx >= R || dy < 0 || dy >= C) return time;
					if(map[dx][dy] == '.') {
						move.offer(new Point(dx, dy));
						map[dx][dy] = 'J';
					}
				}
			}
			print();
		}
		
		return -1;
	}
	
	public static void print() {
		for(char[] arr : map) {
			for(char c : arr) {
				System.out.print(c);
			}
			System.out.println();
		}
		System.out.println();
	}
}
