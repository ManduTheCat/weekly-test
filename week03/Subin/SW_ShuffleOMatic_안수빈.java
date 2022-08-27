import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_ShuffleOMatic_안수빈 {

	static int T, N, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			int[] cards = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = 6;
			dfs(0, cards);
			sb.append("#").append(t).append(" ");
			if (ans == 6) sb.append("-1\n");
			else sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void dfs(int depth, int[] cards) {
		if (isSorted(cards)) {
			ans = Math.min(ans, depth);
			return ;
		}
		
		int n2 = N / 2;
		int[] r = new int[n2];
		
		// 셔플할 때 오른쪽 카드의 위치를 이동할 것이므로 오른쪽 카드임을 확인하기 위해 미리 저장
		for (int i = n2; i < N; i++) r[i - n2] = cards[i];

		// 카드가 [1, 2, 3, 4, 5, 6] 6개일 때
		// 0 [1, 2, 3, 4, 5, 6]: 섞이지 않음
		// 1 [1, 2, 4, 3, 5, 6]: 4 이동
		// 2 [1, 4, 2, 5, 3, 6]: 4, 5 이동
		// 3 [4, 1, 5, 2, 6, 3]: 4, 5, 6 이동
		// 4 [4, 5, 1, 6, 2, 3]: 5, 6 이동
		// 5 [4, 5, 6, 1, 2, 3]: 6 이동
		for (int i = 1; i < N; i++) { // 1부터 N-1까지 (0은 카드 이동x)
			int ridx = 0; // 오른쪽 카드 인덱스
			if (i > n2) ridx = i - n2; // i>n2이면 이동을 시작하는 카드가 달라진다.
			for (int j = 0; j < N; j++) { // 모든 카드를 돌며 확인
				if (cards[j] == r[ridx]) { // 오른쪽 카드라면
					swap(cards, j - 1, j); // swap
					if (++ridx >= i || ridx == n2) break; // 이동 범위 체크
				}
			}
			if (depth + 1 < ans)
				dfs(depth + 1, Arrays.copyOf(cards, N));
		}
		
	}
	
	// 카드 교환
	public static void swap(int[] cards, int i, int j) {
		int temp = cards[i];
		cards[i] = cards[j];
		cards[j] = temp;
	}
	
	// 오름차순, 내림차순 정렬 체크
	public static boolean isSorted(int[] cards) {
		return (isSortedAsc(cards) || isSortedDesc(cards));
	}
	
	// 오름차순 정렬 체크
	public static boolean isSortedAsc(int[] cards) {
		for (int i = 1; i < N; i++) {
			if (cards[i - 1] > cards[i]) return false;
		}
		
		return true;
	}

	// 내림차순 정렬 체크
	public static boolean isSortedDesc(int[] cards) {
		for (int i = 1; i < N; i++) {
			if (cards[i - 1] < cards[i]) return false;
		}
		
		return true;
	}
}
