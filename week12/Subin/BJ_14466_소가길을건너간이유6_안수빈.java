import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 1. 구역 나눠서 번호 붙이기
// 2. 모든 조합 구하기
public class BJ_14466_소가길을건너간이유6_안수빈 {

	static int N, K, R;
	static int[][] map;
	static boolean[][][] isBridge;
	
	// 시계방향 -> 상우하좌
	static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		isBridge = new boolean[N + 1][N + 1][4];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int rr = Integer.parseInt(st.nextToken());
			int cc = Integer.parseInt(st.nextToken());
			
			int dir = getDir(r, c, rr, cc);
			isBridge[r][c][dir] = true;
			isBridge[rr][cc][(dir + 2) % 4] = true;
		}
		
		map = new int[N + 1][N + 1];
		int cnt = 1; // 구역 번호
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == 0) bfs(i, j, cnt++);
			}
		} // 구역 나누기
		
		int[] cows = new int[cnt + 1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			++cows[map[r][c]];
		}
		
		int ans = 0;
		for (int i = 1; i < cnt; i++) {
			ans += cows[i] * (K - cows[i]);
		}
		ans /= 2;
		
		System.out.println(ans);
		
	}

	private static void bfs(int r, int c, int cnt) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.add(new int[] {r, c});
		map[r][c] = cnt;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				if (isBridge[cur[0]][cur[1]][i]) continue;
				
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];
				
				if (nr < 1 || nr > N || nc < 1 || nc > N || map[nr][nc] != 0) continue;
				q.add(new int[] {nr, nc});
				map[nr][nc] = cnt;
			}
		}
		
	}

	private static int getDir(int r, int c, int rr, int cc) {
		if (r == rr) {
			if (c > cc) return 3;
			else return 1;
		} else {
			if (r > rr) return 0;
			else return 2;
		}
	}
	
}
