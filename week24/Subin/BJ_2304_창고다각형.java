import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[][] column = new int[n][2];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			column[i][0] = Integer.parseInt(st.nextToken());
			column[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(column, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});

		Stack<int[]> s = new Stack<>();
		int ans = 0;
		int[] prev;

		// 왼 -> 오, 커지는 구간 탐색
		for (int i = 0; i < n; i++) {
			if (s.isEmpty() || s.peek()[1] <= column[i][1]) {
				s.add(column[i]);
			}
		}

		ans += s.peek()[1];
		prev = s.pop();
		while (!s.isEmpty()) {
			int[] pop = s.pop();
			ans += (prev[0] - pop[0]) * pop[1];
			prev = pop;
		}

		// 오 -> 왼, 작아지는 구간 탐색
		for (int i = n - 1; i >= 0; i--) {
			if (s.isEmpty() || s.peek()[1] < column[i][1]) {
				s.add(column[i]);
			}
		}

		prev = s.pop();
		while (!s.isEmpty()) {
			int[] pop = s.pop();
			ans += (pop[0] - prev[0]) * pop[1];
			prev = pop;
		}

		System.out.println(ans);
	}

}
