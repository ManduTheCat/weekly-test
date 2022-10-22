import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SW_점심식사시간_안수빈 {

	static int N, stairTime[], min;
	static Point[] stair;
	static List<Point> person;
	static List<Integer> distanceA, distanceB; // 1번 계단, 2번 계단까지의 거리
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			stairTime = new int[2]; // 계단을 내려가는데 소모되는 시간을 저장할 배열
			stair = new Point[2]; // 계단의 위치를 저장할 배열
			person = new ArrayList<>(); // 사람의 위치를 저장할 리스트
			int idx = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int input = Integer.parseInt(st.nextToken());
					if (input > 1) { // 계단일 경우
						stairTime[idx] = input; // 소모 시간을 저장한다.
						stair[idx++] = new Point(j, i); // 계단의 위치를 저장한다.
					}
					else if (input == 1) person.add(new Point(j, i)); // 사람일 경우 사람의 위치를 저장한다.
				}
			}
			
			// 모든 경우의 수를 돌며 최소 시간을 구한다.
			min = Integer.MAX_VALUE;
			for (int flag = 0; flag < (1 << N); flag++) { // 계단 별 인원 나누기
				simul(flag);
			}
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void simul(int flag) {
		// 계단까지의 거리를 구한다.
		distanceA = new ArrayList<>();
		distanceB = new ArrayList<>();
		for (int i = 0; i < person.size(); i++) {
			Point p = person.get(i);
			if ((flag & (1 << i)) != 0) distanceA.add(getDistance(stair[0], p));
			else distanceB.add(getDistance(stair[1], p));
		}

		// 계단에 도착하는 시간 순으로 정렬
		Collections.sort(distanceA);
		Collections.sort(distanceB);
		
		// 각 계단의 이동 시간 구하기
		min = Math.min(min, Math.max(getTime(distanceA, stairTime[0]), getTime(distanceB, stairTime[1])));
	}

	// 계단의 이동 시간은 dp 사용 => 어려워서 댓글 참고함..
	private static int getTime(List<Integer> distance, int time) {
		int size = distance.size();
		if (size == 0) return 0;
		
		int[] dp = new int[size];
		
		// dp
		for (int i = 0; i < size; i++) {
			if (i < 3 || dp[i - 3] <= distance.get(i)) dp[i] = distance.get(i) + time + 1; // 계단를 내려갈 수 있다면 바로 내려간다. 도착 시간 + 계단을 내려가는 시간 + 대기 시간(1)
			else dp[i] = dp[i - 3] + time; // 계단을 내려갈 수 없다면 (i - 3)번째 사람을 먼저 내려보낸 뒤 계단을 내려간다. (i-3)번째 사람 소요 시간 + 계단을 내려가는 시간
		}
		return dp[size - 1];
	}


	private static int getDistance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
	
}
