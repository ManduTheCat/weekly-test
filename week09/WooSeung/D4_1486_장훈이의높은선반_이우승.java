package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1486_장훈이의높은선반_이우승 {
	
	static int n, b, ans;
	static int[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			arr = new int[n];
			visited = new boolean[n];
			ans = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0);
			
			System.out.println("#" + t +" " + ans);
		}
		
		
	}
	
	static void dfs(int cnt) {
		
		if(cnt == n) {
			int sum = 0;
			for(int i = 0; i < n; i++) {
				if(visited[i]) {
					sum += arr[i];
				}
			}
			if(sum >= b) {
				sum -= b;
//				System.out.println("sum : " + sum);
				if(ans > sum) {
					ans = sum;
//					System.out.println("ans : " + ans);
				}
			}
			return;
		}
		
		visited[cnt] = true;
		dfs(cnt + 1);
		visited[cnt] = false;
		dfs(cnt + 1);
		
	}

}
