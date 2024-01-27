import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_4179_불_안수빈 {

	static int R, C;
	static int[][] fire;
	static char[][] map;
	
	static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];

		Queue<int[]> fQueue = new ArrayDeque<>();
		Queue<int[]> jQueue = new ArrayDeque<>();
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'F') fQueue.add(new int[] {i, j});
				else if (map[i][j] == 'J') {
					jQueue.add(new int[] {i, j});
					map[i][j] = '.';
				}
			}
		}

		int ans = 0;
		
		while (!jQueue.isEmpty()) {
			++ans;
			
			int size = fQueue.size();
			while (size-- > 0) {
				int[] cur = fQueue.poll();
				
				for (int i = 0; i < 4; i++) {
					int nr = cur[0] + dir[i][0];
					int nc = cur[1] + dir[i][1];
					
					// 유효성 체크
					if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == '#' || map[nr][nc] == 'F') continue;
					
					map[nr][nc] = 'F';
					fQueue.add(new int[] {nr, nc});
				}
			}
			
			size = jQueue.size();
			while (size -- > 0) {
				int[] cur = jQueue.poll();
				
				if (cur[0] == 0 || cur[0] ==  R - 1 || cur[1] == 0 || cur[1] == C - 1) {
					System.out.println(ans);
					return ;
				}

				for (int i = 0; i < 4; i++) {
					int nr = cur[0] + dir[i][0];
					int nc = cur[1] + dir[i][1];
					
					// 유효성 체크
					if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == '#' || map[nr][nc] == 'F' || map[nr][nc] == 'J') continue;
					
					map[nr][nc] = 'J';
					jQueue.add(new int[] {nr, nc});
				}
			}
			
		}
		
		// 탈출 불가능
		System.out.println("IMPOSSIBLE");
	}
	
}
