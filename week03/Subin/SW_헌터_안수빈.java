import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_헌터_안수빈 {

	static int T, N, M, map[][], ans, selected[];
	static Point[] points;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			M = 0;
			for (int i = 0; i< N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 0) ++M; // 몬스터의 수
				}
			}

			points = new Point[M * 2]; // 몬스터와 집의 좌표 저장
			for (int i = 0; i< N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > 0) points[map[i][j] - 1] = new Point(j, i); // 0 ~ M: 몬스터 좌표
					if (map[i][j] < 0) points[M - map[i][j] - 1] = new Point(j, i); // M ~ 2M: 집 좌표
				}
			}
			
			selected = new int[M * 2];
			ans = Integer.MAX_VALUE;
			perm(0, 0); // 방문 순서 순열
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void perm(int cnt, int flag) {
		if (cnt == 2 * M) { // 방문 순서를 다 구하면
			// 이동 거리 계산
			int x = 0, y = 0;
			int sum = 0;
			
			for (int i = 0; i < 2 * M; i++) {
				sum += Math.abs(points[selected[i]].x - x) + Math.abs(points[selected[i]].y - y);
				if(sum > ans) return; // sum이 ans보다 크면 최소값을 구해야 하므로 더이상 고려하지 않아도 괜찮다.
				x = points[selected[i]].x;
				y = points[selected[i]].y;
			}
			ans = sum; // 최소값 갱신
		}
		
		for (int i = 0; i < 2 * M; i++) {
			if ((flag & 1 << i) != 0) continue ; // 방문 체크
			if (i > M - 1 && (flag & 1 << (i - M)) == 0) continue ; // 집에 방문하기 위해서는 몬스터를 먼저 처리해야 한다.
			
			selected[cnt] = i;
			perm(cnt + 1, flag | 1 << i);
		}
	}
	
}
