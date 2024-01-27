import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1890_점프 {
	
	static int N;
	static int[][] Map;
	static long[][] DP;
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		Map = new int[N+1][N+1];
		DP = new long[N+1][N+1];
		
		for (int row = 1; row <= N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col <= N; col++) {
				Map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		DP[1][1] = 1;

		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= N; col++) {
				if (row == N && col == N) break;
				if (DP[row][col] > 0) {
					// East
					if (col + Map[row][col] <= N) {
						DP[row][col + Map[row][col]] += DP[row][col];
					}
					
					// South
					if (row + Map[row][col] <= N) {
						DP[row + Map[row][col]][col] += DP[row][col];
					}
				}
			}
		}
		
		System.out.println(DP[N][N]);
	}
}
