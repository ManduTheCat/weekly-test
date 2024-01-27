import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_12865_평범한배낭 {
	
	static int N, K;
	static int[][] Items;
	static int[][] Table;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Items = new int[N+1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			Items[i][0] = Integer.parseInt(st.nextToken());
			Items[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Table = new int[N+1][K+1];
		for (int i = 0; i <= N; i++) {
			for (int w = 0; w <= K; w++) {
				if (i == 0 || w == 0) {
					Table[i][w] = 0;
					continue;
				}
				
				if (w < Items[i][0]) {
					Table[i][w] = Table[i-1][w];
				}
				else if (w >= Items[i][0]) {
					Table[i][w] = Math.max(Table[i-1][w], Table[i-1][w-Items[i][0]] + Items[i][1]);
				}
			}
		}
		
		System.out.println(Table[N][K]);
	}

}
