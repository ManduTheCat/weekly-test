import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_19637_IF문좀대신써줘_안수빈 {

	private static int stoi(String s) { return Integer.parseInt(s); } // parse
	
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		String[] titles = new String[N];
		int[] scores = new int[N];
		
		// input
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			titles[i] = st.nextToken();
			scores[i] = stoi(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int score = stoi(br.readLine());
			int idx = lowerBound(scores, score);
			
			sb.append(titles[idx]).append("\n");
		}
		System.out.print(sb);
	}

	private static int lowerBound(int[] scores, int score) {
		int start = 0;
		int end = N - 1;
		
		while (start < end) {
			int mid = (start + end) / 2;
			
			if (scores[mid] >= score) end = mid;
			else start = mid + 1;
		}
		
		return end;
	}
}
