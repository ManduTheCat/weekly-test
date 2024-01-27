import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1890_점프_안수빈 {

	static int N, map[][];
	static long dp[][]; // 경로의 개수는 2^63-1보다 작거나 같다.
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new long[N][N];
		dp[0][0] = 1; // (0, 0)에서 시작
		// 모든 게임판을 돌며
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (dp[r][c] == 0 || map[r][c] == 0) continue; // 경로가 없거나 점프할 수 없다면 continue
				
				// 오른쪽이나 아래로 점프할 수 있음
				int nr = r + map[r][c];
				int nc = c + map[r][c];
				if (nr < N) dp[nr][c] += dp[r][c]; // 점프할 수 있다면 경로 추가
				if (nc < N) dp[r][nc] += dp[r][c]; // 점프할 수 있다면 경로 추가
			}
		}
		
		System.out.println(dp[N - 1][N - 1]); // 가장 오른쪽 칸으로 이동할 수 있는 경로의 개수 출력
	}
	
}
