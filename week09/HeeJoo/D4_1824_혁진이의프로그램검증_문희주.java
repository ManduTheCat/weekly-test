package 새터데이.sat221112;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 프로그램은 현재 위치에 있는 문자가 나타내는 명령 처리. 이동 방향에 따라 다음 문자로 이동
 * 가장 처음 위치는 (0, 0), 이동 방향은 오른쪽
 * 명령을 처리하다 보면 이동 방향이 상하좌우로 바뀜
 * 만약 범위를 벗어난다면, 반대편에 있는 위치로 이동 ex) 첫 줄 오른쪽 격자 밖 -> 첫 줄 가장 왼쪽 칸
 * 메모리에는 0 ~ 15 사이의 정수를 하나 저장. 초기값 = 0.
 * 
 * 명령어
 * 1. 이동 명령 : 문자에 따라 이동방향 전환
 * 2. 메모리 명령 : 메모리 저장값에 따라 이동방향 전환
 * 3. ? : 랜덤 이동방향 전환. 확률 동일
 * 4. . : 명령 x
 * 5. @ : 수행 종료
 * 6. 숫자 : 메모리에 값 저장
 * 7. +, - : 메모리 값 +-. 끝값 도달시 순환 ex) 15+ = 0, 0- = 15
 */

public class D4_1824_혁진이의프로그램검증_문희주 {
	static int R, C;
	static char[][] map;
	static int[][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
	static boolean[][][][] path;
	public static void main(String[] args) throws IOException {
		File file = new File("res/input2.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new char[R][C];
			for(int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();
			
			path = new boolean[R][C][4][16]; // (x, y)를 방향d, 메모리m일 때 방문했는지 여부
			if(BFS()) sb.append("#" + tc + " YES\n");
			else sb.append("#" + tc + " NO\n");
		}
		System.out.println(sb);
	}
	
	public static boolean BFS() {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {0, 0, 3, 0}); // x, y, 방향, 메모리
		path[0][0][3][0] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			int x = now[0];
			int y = now[1];
			int d = now[2];
			int memo = now[3];
			
			//System.out.printf("%d > (%d, %d) : %d\n", d, x, y, memo);
			int dx = x;
			int dy = y;
			if(map[x][y] == '?') { // 랜덤 방향
				for(int i = 0; i < 4; i++) {
					d = i;
					
					dx = x + dir[d][0];
					dy = y + dir[d][1];
					
					// 범위를 벗어나는 경우
					if(dx < 0) dx = R-1;
					else if(dx >= R) dx = 0;
					if(dy < 0) dy = C-1;
					else if (dy >= C) dy = 0;
					
					if(path[dx][dy][d][memo]) continue; // 이미 해당 경로를 방문한 경우
					q.offer(new int[] {dx, dy, d, memo});
					path[dx][dy][d][memo] = true;
				}
			} else {
				// 명령에 따라 수행
				if(map[x][y] == '@') {
					return true;
				} else if(map[x][y] == '<' || map[x][y] == '>' ||map[x][y] == '^' || map[x][y] == 'v') {
					d = getDir(map[x][y]);
				} else if(map[x][y] == '+') {
					memo += 1;
				} else if(map[x][y] == '-') {
					memo -= 1;
				} else if(map[dx][dy] == '_') { // 0이면 우, 아니면 좌
					d = (memo == 0 ? 3 : 2);
				} else if(map[x][y] == '|') { // 0이면 하, 아니면 상
					d = (memo == 0 ? 1 : 0);
				} else if(map[x][y] == '.') {
				} else { // 0 ~ 9
					memo = map[x][y] - '0';
				}
				
				dx += dir[d][0];
				dy += dir[d][1]; 
				
				// 범위를 벗어나는 경우
				if(dx < 0) dx = R-1;
				else if(dx >= R) dx = 0;
				if(dy < 0) dy = C-1;
				else if (dy >= C) dy = 0;
				
				// 메모리가 범위를 벗어나는 경우
				if(memo < 0) memo = 15;
				else if(memo > 15) memo = 0;
				
				if(path[dx][dy][d][memo]) continue; // 이미 해당 경로를 방문한 경우
				q.offer(new int[] {dx, dy, d, memo});
				path[dx][dy][d][memo] = true;
			}
		}
		return false;
	}

	public static int getDir(char c) {
		if(c == '<') return 2;
		if(c == '>') return 3;
		if(c == '^') return 0;
		if(c == 'v') return 1;
		
		return 3;
	}
	
	public static void print(char[][] map) {
		for(char[] arr : map) {
			for(char c : arr) {
				System.out.print(c + "");
			}
			System.out.println();
		}
		System.out.println();
	}

}
