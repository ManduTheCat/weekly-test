import java.io.*;
import java.util.*;

class Main {
	public static int N, K;
	public static int[] W, V;
	public static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		W = new int[N + 1];
		V = new int[N + 1];
		map = new int[N + 1][K + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			
			for (int k = 1; k <= K; k++) {
				
				if (W[i] > k) {
					map[i][k] = map[i - 1][k];
					continue;
				}
				
				if (W[i] <= k) {
					map[i][k] = Math.max(map[i - 1][k], map[i - 1][k - W[i]] + V[i]);
					continue;
				}
				
			}
		}
		
		bw.write(map[N][K] + "\n");
		bw.flush();
		bw.close();

	}
}
