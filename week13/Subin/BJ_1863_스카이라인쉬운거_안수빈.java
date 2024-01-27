import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_1863_스카이라인쉬운거_안수빈 {

	private static int stoi(String s) { return Integer.parseInt(s); } // parse

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = stoi(br.readLine());
		
		int ans = 0;
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = stoi(st.nextToken());
			int y = stoi(st.nextToken());

			if (y > 0 && (s.isEmpty() || s.peek() < y)) s.push(y);
			else {
				while (!s.isEmpty() && s.peek() > y) {
					s.pop();
					++ans;
				}
				if (y > 0 && (s.isEmpty() || s.peek() < y)) s.push(y);
			}
		}
		ans += s.size();
		
		System.out.println(ans);
	}
	
}
