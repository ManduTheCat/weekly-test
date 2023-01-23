import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_17615_볼모으기_안수빈 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		char[] balls = br.readLine().toCharArray();
		
		// 빨간 공과 파란 공 개수 세서 더 작은 값으로 답 갱신
		int rCnt = 0, bCnt = 0;
		for (int i = 0; i < N; i++) {
			if (balls[i] == 'R') ++rCnt;
			else ++bCnt;
		}
		int ans = Math.min(rCnt, bCnt);
		
		// 왼쪽에서 연속적으로 나오는 색의 공의 개수 뺀 뒤 답 갱신
		// 왼쪽에 있으면 이동할 필요가 없다!!
		char prev = balls[0];
		int cnt = balls[0] == 'R' ? rCnt - 1 : bCnt - 1;
		for (int i = 1; i < N; i++) {
			if (prev == balls[i]) --cnt;
			else break;
		}
		ans = Math.min(ans, cnt);
		
		// 오른쪽에서 연속적으로 나오는 색의 공의 개수 뺀 뒤 답 갱신
		// 오른쪽에 있으면 이동할 필요가 없다!! 
		prev = balls[N - 1];
		cnt = balls[N - 1] == 'R' ? rCnt - 1 : bCnt - 1;
		for (int i = N - 2; i >= 0; i--) {
			if (prev == balls[i]) --cnt;
			else break;
		}
		ans = Math.min(ans, cnt);
		
		System.out.println(ans);
	}
	
}
