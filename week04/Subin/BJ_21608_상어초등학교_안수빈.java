import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_21608_상어초등학교_안수빈 {

	static int N, map[][];
	static List<Integer> order, students[];
	
	static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		order = new ArrayList<>();
		students = new ArrayList[N * N];
		for (int i = 0; i < N * N; i++) {
			students[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(st.nextToken());
			order.add(s1);
			for (int j = 0; j < 4; j++) {
				int s2 = Integer.parseInt(st.nextToken());
				students[s1 - 1].add(s2);
			}
		}

		map = new int[N][N];
		setup();
		System.out.println(getAns());
	}

	private static void setup() {
		for (int o: order) {
			int setR = 0, setC = 0;
			// https://www.acmicpc.net/board/view/88909
			int maxLikes = -1, maxEmptys = -1;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] != 0) continue;
					int likes = 0;
					int empty = 0;
					for (int d = 0; d < 4; d++) {
						int nr = r + dir[d][0];
						int nc = c + dir[d][1];
						if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						if (map[nr][nc] == 0) ++empty;
						if (students[o - 1].contains(map[nr][nc])) ++likes;
					}
					if (likes > maxLikes || (likes == maxLikes && empty > maxEmptys)) {
						setR = r;
						setC = c;
						maxLikes = likes;
						maxEmptys = empty;
					}
				}
			}
			map[setR][setC] = o;
		}
	}

	private static int getAns() {
		int ans = 0;
		int[] satisfy = {0, 1, 10, 100, 1000};
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int s1 = map[r][c];
				int likes = 0;
				for (int d = 0; d < 4; d++) {
					int nr = r + dir[d][0];
					int nc = c + dir[d][1];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if (students[s1 - 1].contains(map[nr][nc])) ++likes;
				}
				ans += satisfy[likes];
			}
		}
		return ans;
	}
	
}
