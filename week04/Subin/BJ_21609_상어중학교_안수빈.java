import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_21609_상어중학교_안수빈 {
	
	static int N, M, map[][], maxSize, maxRainbow, R, C;
	static boolean[][] visited;
	
	static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(solve());
	}
	
	private static int solve() {
		int ans = 0;

		while (true) {
			R = -1;
			maxSize = 0;
			maxRainbow = 0;
			
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > 0 && !visited[i][j]) {
						bfs(i, j, map[i][j]);
					}
					// 무지개 블록은 모든 일반 블록 그룹에 들어갈 수 있기 때문에 다시 사용할 수 있도록 방문 체크를 해제한다.
					for (int r = 0; r < N; r++) {
						for (int c = 0; c < N; c++) {
							if (map[r][c] == 0) visited[r][c] = false;
						}
					}
				}
			}

			if (R == -1) break ;
			
			visited = new boolean[N][N];
			removeBlocks(R, C, map[R][C]);
			ans += maxSize * maxSize;

			// 3. 중력 작용
			gravity();
			// 4. 90도 반시계 회전
			rotate();
			// 5. 중력 작용
			gravity();
		}
		
		return ans;
	}

	// 1. BFS로 블록 그룹 찾기
	private static void bfs(int r, int c, int b) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		
		int size = 0;
		int rainbow = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			++size;
			if (map[cur[0]][cur[1]] == 0) ++rainbow;
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
				if (map[nr][nc] == b || map[nr][nc] == 0) {
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			} 
		}
		
		// 블록 그룹 갱신
		if (size > 1 && size > maxSize || (size == maxSize && rainbow >= maxRainbow)) {
			maxSize = size;
			maxRainbow = rainbow;
			R = r;
			C = c;
		}
	}

	// 2. 블록그룹 제거 -> -2로
	private static void removeBlocks(int r, int c, int b) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			map[cur[0]][cur[1]] = -2;
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
				if (map[nr][nc] == b || map[nr][nc] == 0) {
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			} 
		}
	}
	
	private static void gravity() {
		Stack<Integer> stack = new Stack<>();
		
		for (int c = 0; c < N; c++) {
			int r = 0;
			while (r < N) {
				for (; r < N; r++) {
					if (map[r][c] >= 0) {
						stack.add(map[r][c]);
						map[r][c] = -2;
					}
					if (map[r][c] == -1) break;
				}
				for (int rr = r - 1; rr >= 0; rr--) {
					if (!stack.isEmpty()) map[rr][c] = stack.pop();
				}
				++r;
			}
		}
	}
	
	private static void rotate() {
		int[][] newMap = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				newMap[i][j] = map[j][N - i - 1];
			}
		}
		map = newMap;
	}
}
