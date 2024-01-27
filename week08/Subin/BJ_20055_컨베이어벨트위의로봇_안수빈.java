import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_20055_컨베이어벨트위의로봇_안수빈 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] A = new int[N * 2];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N * 2; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Queue<Integer> robot = new ArrayDeque<>();
		boolean[] isRobot = new boolean[N * 2];
		
		int start = 0, end;
		int cnt = 0;
		int ans = 0;
		
		while (cnt < K) {
			// 단계 증가
			++ans;
			// 벨트 한 칸 회전
			start += N * 2 - 1;
			start %= N * 2;
			end = start + N - 1;
			end %= N * 2;
			// 로봇 이동
			int size = robot.size();
			for (int i = 0; i < size; i++) {
				int r = robot.poll();
				if (r == end) { // 내리는 위치라면
					isRobot[r] = false; // 로봇 지우기
				} else { // 내리는 위치가 아니라면
					// 로봇 한 칸 이동
					int nr = r + 1; 
					nr %= N * 2;
					if (!isRobot[nr] && A[nr] > 0) { // 갈 수 있다면 로봇 이동
						isRobot[r] = false;
						if (--A[nr] == 0) ++cnt; // 내구도 감소
						if (nr != end) { // 내리는 위치가 아니라면
							robot.add(nr); // 큐에 넣기
							isRobot[nr] = true; // 로봇 이동
						}
					} else { // 갈 수 없다면 이동 x
						robot.add(r);
					}
				}
			}
			
			if (!isRobot[start] && A[start] > 0) { // 로봇 올리기
				robot.add(start);
				isRobot[start] = true;
				if (--A[start] == 0) ++cnt; // 내구도 감소
			}
		}
		
		System.out.println(ans);
	}
	
}
