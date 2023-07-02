import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
		
	static int N, before, min = Integer.MAX_VALUE;
	static int[] A, answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		answer = new int[2];
		search();
		
		System.out.println(answer[0] + " " + answer[1]);
	}
	
	private static void search() {
		int start = 0;
		int end = N - 1;

		while (start < end) {
			int sum = A[start] + A[end];
			
			if (min > Math.abs(sum)) {
				min = Math.abs(sum);
				answer[0] = A[start];
				answer[1] = A[end];
				
				if (sum == 0) break ;
			}
			
			if (sum < 0) {
				++start;
			} else {
				--end;
			}
		}
	}
		
}
