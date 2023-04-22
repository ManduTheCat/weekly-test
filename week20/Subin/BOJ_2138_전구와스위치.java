import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2138_전구와스위치 {
	
	static int N;
	static char[] before, after;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		before = br.readLine().toCharArray();
		after = br.readLine().toCharArray();
		
		// 첫번째 전구를 켜지 않는 경우
		int ans = solve(Arrays.copyOf(before, N));
		
		// 첫번째 전구를 켜는 경우
		char[] newBefore = Arrays.copyOf(before, N);
		newBefore[0] = newBefore[0] == '0' ? '1' : '0';
		newBefore[1] = newBefore[1] == '0' ? '1' : '0';
		
		int cnt = solve(newBefore);
		cnt = cnt == -1 ? -1 : cnt + 1;
		
		if (ans == -1) ans = cnt;
		else if (cnt != -1) ans = Math.min(ans, cnt);
		
		System.out.println(ans);
	}
	
	private static int solve(char[] before) {
		int cnt = 0;
		for (int i = 1; i < N; i++) {
			if (before[i - 1] != after[i - 1]) {
				++cnt;
				before[i - 1] = before[i - 1] == '0' ? '1' : '0';
				if (i < N) before[i] = before[i] == '0' ? '1' : '0';
				if (i + 1 < N) before[i + 1] = before[i + 1] == '0' ? '1' : '0';
			}
		}
		
		if (String.copyValueOf(before).equals(String.copyValueOf(after))) return cnt;
		else return -1;
	}
	
}
