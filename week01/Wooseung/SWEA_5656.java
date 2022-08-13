package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_5656 {
	
	static int n, w, h, ans;
	static int[][] bricks;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int[] dropPos;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			ans = Integer.MAX_VALUE;
			
			bricks = new int[h][w];
			dropPos = new int[n];
			
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					bricks[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			dropPoint(0);
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb);
		
		
	}
	static void dropPoint(int cnt) {	// 중복순열로 공을 넣는 위치 n만큼 정하기
		if(cnt == n) {
			drop();
			return;
		}
		for(int i = 0; i < w; i++) {
			dropPos[cnt] = i;
			dropPoint(cnt + 1);
		}
		
	}	
	
	static void drop() {	// 구슬 투하 시작
		int[][] bCopy = new int[h][w];
		
		for(int i = 0; i < h; i++) {	// 새로운 배열에 bricks의 값을 옮긴다
			for(int j = 0; j < w; j++) {
				bCopy[i][j] = bricks[i][j];
			}
		}
		
		for(int i = 0; i < n; i++) {	//	구슬 개수만큼 투하
			int x = -1; 
			int y = dropPos[i];
			for(int j = 0; j < h; j++) {	// 깰 벽돌이 있는지 체크 없으면 x값 변경없이 -1
				if(bCopy[j][y] != 0) {
					x = j;
					break;
				}
			}
			if(x == -1) {	// -1이 나오면 다음 구슬 투하
				continue;
			}
			
			crash(x, y, bCopy);
			bricks_sort(bCopy);	// 벽돌 재정렬
		}
		
		int cnt = 0;	// 남아있는 벽돌 개수 파악
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(bCopy[i][j] != 0) {
					cnt++;
				}
			}
			
		}
		ans = Math.min(cnt, ans);	// 더 작은값을 ans에 저장			
	}
	
	static void crash(int x, int y, int[][] bCopy) {
		int n = bCopy[x][y];
		
		bCopy[x][y] = 0;
		if(n == 1) {
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nx = x;
			int ny = y;
			for(int j = 0; j < n - 1; j++) {
				nx += dx[i];
				ny += dy[i];
				if(0 > nx || 0 > ny || h <= nx || w <= ny) {
					break;
				}
				if(bCopy[nx][ny] == 0) {
					continue;
				}
				if(bCopy[nx][ny] == 1) {
					bCopy[nx][ny] = 0;
				}else {
					crash(nx, ny, bCopy);
				}
			}
		}
	}
	
	static void bricks_sort(int[][] bCopy) {
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				if(bCopy[j][i] != 0) {
					stack.add(bCopy[j][i]);
					bCopy[j][i] = 0;
				}
			}
			int x = h - 1;
			while(!stack.isEmpty()) {
				bCopy[x--][i] = stack.pop();
			}
		}
	}

}
