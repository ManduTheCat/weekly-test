import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17281_야구_안수빈 {
	
	static int N, M = 9, ans;
	static int[][] inning;
	static int[] order;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		inning = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		order = new int[M];
		perm(0, 0);
		System.out.println(ans);
	}

	// 선수 순서 순열
	public static void perm(int cnt, int flag) {
		if (cnt == 3) {
			perm(cnt + 1, flag); // 1번 선수는 4번 타자로 미리 결정됨
			return ;
		}
		
		if (cnt == M) {
			baseball();
			return ;
		}
		
		for (int i = 1; i < M; i++) { // 1번 선수는 순열에 넣지 않음
			if ((flag & 1 << i) != 0) continue;
			order[cnt] = i;
			perm(cnt + 1, flag | 1 << i);
		}
	}

	public static void baseball() {
		int score = 0;
		int j = 0; // 현재 타자
		
		for (int i = 0; i < N; i++) {
			int base = 0; // 주루 진출 상황
			int out = 0; // 아웃 수
			
			while (out < 3) {
				int cur = inning[i][order[j]];
				j = (j + 1) % M;
				
				if (cur == 0) { // 아웃
					out++;
					continue;
				} else { // 1루타, 2루타, 3루타, 홈런
					base <<= cur; // 주자 이동
					base |= 1 << (cur - 1); // 현재 타자 이동
				}
				
				// 3루 이상 나간 경우 score 증가시킨 뒤 해당 비트 0으로 바꿔주기
				for (int k = 3; k < 7; k++) { 
					if ((base & 1 << k) != 0) {
						base = ~base ^ ~(1 << k);
						/* 1000, 1000 => 0001, 0001 => 0000
						 * 1001, 1000 => 0110, 0111 => 0001 */
						score++;
					}
				}
			}
		}
		ans = Math.max(ans, score);
	}
}
