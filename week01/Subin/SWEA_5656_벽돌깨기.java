import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {
	
	static int N, W, H, min;
	static int[][] Map; 
	static int[] selected;
	
	static final int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			Map = new int[H][W];
			
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					Map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			selected = new int[N];
			min = Integer.MAX_VALUE;
			perm(0);
			
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		System.out.print(sb);
	}

	// 모든 경우의 수 체크
	public static void perm(int cnt) {
		if (cnt == N) {
			// 기존 맵 저장
			int[][] tmp = new int[H][W];
			for (int r = 0; r < H; r++) tmp[r] = Arrays.copyOf(Map[r], W);
			// 선택된 줄에 벽돌 떨어트리기
			for (int i = 0; i < cnt; i++) brickOut(selected[i]);
			count(); // 정답 갱신
			Map = tmp; // 기존 맵 불러오기
			return ;
		}
		
		for (int i = 0; i < W; i++) {
			selected[cnt] = i;
			perm(cnt + 1);
		}
	}
	
	// c번째 줄에 공 떨어트리기
	public static void brickOut(int c) {
		for (int r = 0; r < H; r++) {
			if (Map[r][c] > 0) {
				breakBricks(r, c); // 벽돌 깨고
				dropBricks(); // 벽돌 떨어트리기
				return;
			}
		}
	}
	
	// r, c번째 벽돌이 깨졌을때 상하좌우 벽돌 깨기
	public static void breakBricks(int r, int c) {
		int cnt = Map[r][c] - 1;
		Map[r][c] = 0;
		
		for (int i = 0; i < 4; i++) {
			int dr = r, dc = c;
			for (int j = 0; j < cnt; j++) {
				dr += dir[i][0];
				dc += dir[i][1];
				
				if (dr < 0 || dr >= H || dc < 0 || dc >= W) break ;
				
				if (Map[dr][dc] != 0) {
					breakBricks(dr, dc);
				}
			}
		}
	}
	
	// 벽돌 밑으로 떨어트리기
	public static void dropBricks() {
		Stack<Integer> s = new Stack<>();
		
		for (int c = 0; c < W; c++) {
			for (int r = 0; r < H; r++) {
				if (Map[r][c] != 0) {
					s.push(Map[r][c]); // 남은 벽돌을 스택에 넣는다.
					Map[r][c] = 0;
				}
			}
			
			int idx = H - 1;
			while (!s.empty()) Map[idx--][c] = s.pop(); // 스택의 벽돌을 넣어준다.
		}
	}
	
	// 남은 벽돌 수 세고 최솟값 갱신하기
	public static void count() {
		int cnt = 0;
		for(int r = 0; r < H; r++) { 
			for(int c = 0;c < W; c++) {
				if (Map[r][c] > 0) cnt++;
			}
		}
		min = Math.min(min, cnt);
	}
	
}
