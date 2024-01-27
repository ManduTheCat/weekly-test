import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1486_장훈이의높은선반_안수빈 {
	
	static int N, B, arr[], ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = Integer.MAX_VALUE;
			subset(0, 0);
			sb.append("#").append(t).append(" ").append(ans - B).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void subset(int idx, int sum) {
		if (sum > ans) return ;
		
		if (idx == N) {
			if (sum >= B) ans = sum;
			return ;
		}

		subset(idx + 1, sum);
		subset(idx + 1, sum + arr[idx]);
	}
	
}
