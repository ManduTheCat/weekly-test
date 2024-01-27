package 새터데이.sat221001;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * NxN 격자
 * 초기에 격자의 모든 칸에는 블록이 하나씩 ,,, 검정, 무지개, 일반
 * 일반 블록은 M가지 색상이 있고, 색은 M 이하의 자연수 .. 1 ~ M
 * 검정(-1), 무지개(0)
 * 사방향만 인접한 칸
 * 블록 그룹은 연결된 블록의 집합
 * 그룹에는 일반 블록이 적어도 하나 있어야 하며, 일반 블록의 색은 모두 같아야 함
 * 검은색 블록은 포함 xxx 무지개 블록은 자유
 * 블록의 개수는 2 이상, 임의의 한 블록에서 그룹에 속한 인접 칸으로 이동해서 그룹에 속한 다른 모든 칸으로 이동 가능해야 함
 * 기준 블록 : 무지개 블록이 아닌 블록 중에서, 행의 번호가 가장 작은 블록 ~ 여러 개면 열의 번호가 가장 작은 블록
 * 블록 그룹이 존재하는 동안 다음과 같은 오토플레이 계속 진행
 * 1. 크기가 가장 큰 블록 찾기 .. 여러 그룹이라면 무지개 블록의 수가 가장 많은 블록 그룹 -> 기준 블록의 행이 가장 큰 그룹 -> 열이 가장 큰 그룹
 * 2. 해당 블록 그룹의 블록 모두 제거 ~ 개수^2만큼 점수 획득
 * 3. 격자 중력 작용 ~ 끌어내리기
 * 4. 격자 90도 반시계 회전
 * 5. 격자 중력 작용
 * 격자에 중력이 작용하면 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동. 이동은 다른 블록이나 격자의 경계를 만날 때까지,,,
 * = 아래로 내리기
 */

// 그룹
class Group implements Comparable<Group>{
	int x, y; // 기준 블럭의 좌표
	List<Point> list; // 그룹에 속하는 블럭들의 좌표 리스트
	int rainbow; // 무지개 블럭 개수
	
	public Group(int x, int y, List<Point> list, int rainbow) {
		this.x = x;
		this.y = y;
		this.list = list;
		this.rainbow = rainbow;
	}

	@Override
	public int compareTo(Group o) {
		// 1. 그룹 크기
		if(this.list.size() - o.list.size() > 0 ) return -1;
		else if(this.list.size() - o.list.size() < 0) return 1;
		else { // 2. 무지개 블럭 개수
			if(this.rainbow - o.rainbow > 0) return -1;
			else if(this.rainbow - o.rainbow < 0) return 1;
			else { // 3. 행
				if(this.x - o.x > 0) return -1;
				else if(this.x - o.x < 0) return 1;
				else { // 4. 열
					if(this.y - o.y > 0) return -1;
					else return 1;
				}
			}
		}
	}
	
	
	
}
public class BJ_21609_상어중학교_문희주 {
	static int N;
	static int[][] map, dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 격자 크기 N
		int M = Integer.parseInt(st.nextToken()); // 색상 개수 M
		
		// 격자 정보 입력
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		boolean flag = true;
		visited = new boolean[N][N];
		
		// 블록 그룹이 존재하지 않을 때까지 반복
		while(flag) {
			int result = BFS();
			
			if(result == 0) flag = false;
			else ans += result;
		}
		
		System.out.println(ans);
	}
	
	public static int BFS() {
		int score = 0;
		
		PriorityQueue<Group> pq = new PriorityQueue<>(); // 그룹 우선순위 큐
		
		// (0, 0)부터 (N-1, N-1)까지 탐색
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++){
				if(visited[i][j]) continue;
				// 빈 칸(-2), 검정 블록(-1), 무지개 블록(0) 패스
				// 무지개 블록은 기준 블록이 될 수 없음
				if(map[i][j] == -2 || map[i][j] == -1 || map[i][j] == 0) continue;
				
				int cur = map[i][j]; // 선택된 기준 블록의 색상
				int rainbow = 0; // 해당 그룹 무지개 블록 개수
				List<Point> zeros = new ArrayList<>(); // 해당 그룹에 속하는 무지개 블록들의 좌표 리스트

				
				List<Point> list = new ArrayList<>(); // 해당 그룹에 속하는 모든 블록들의 좌표 리스트
				list.add(new Point(i, j)); // 기준 블록 리스트 삽입
				
				// BFS
				Queue<Point> q = new LinkedList<>();
				visited[i][j] = true;
				
				q.offer(new Point(i, j));
				
				while(!q.isEmpty()) {
					Point now = q.poll();
				
					int x = now.x;
					int y = now.y;
					
					// 인접 칸 ~ 사방향 탐색
					for(int k = 0; k < 4; k++) {
						int dx = x + dir[k][0];
						int dy = y + dir[k][1];
						
						if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
						if(visited[dx][dy]) continue;
						if(map[dx][dy] == cur || map[dx][dy] == 0) { // 같은 색상 or 무지개 블록만 그룹에 추가
							visited[dx][dy] = true;
							list.add(new Point(dx, dy));
							q.offer(new Point(dx, dy));
							
							// 인접 블록이 무지개 블록인 경우
							if(map[dx][dy] == 0) { 
								rainbow++; // 비교 조건 2를 위해 무지개 블록 개수 기록
								zeros.add(new Point(dx, dy)); // 무지개 블록은 다른 그룹에도 속할 가능성이 있기에 false 처리를 위해 zeros에 넣어줌
							}
						}
					}
				}
				pq.offer(new Group(i, j, list, rainbow));
				
				// 무지개 블록 방문 처리 해제
				for(int k = 0; k < zeros.size(); k++) {
					Point zero = zeros.get(k);
					visited[zero.x][zero.y] = false;
				}
				
			}
		}
		
		if(pq.isEmpty()) return 0; // 그룹 x ~ 수행 종료
		
		Group g = pq.poll();
		if(g.list.size() < 2) return 0; // 그룹의 크기는 최소 2 이상
		
		// 그룹의 블록 카운트
		for(int i = 0; i < g.list.size(); i++) {
			Point now = g.list.get(i);
			map[now.x][now.y] = -2;
			score++;
		}
		grav(); // 3. 중력
		map = rotate(); // 4. 반시계 회전
		grav(); // 5. 중력
		
		// 다음 BFS 대비 방문 배열 초기화
		for(int i = 0; i < N; i++) {
			Arrays.fill(visited[i],  false);
		}
		
		return score * score;
	}
	
	// 중력 작용
	public static void grav() {
		// 스택을 이용하여 끌어내리기
		Stack<Integer> stack = new Stack<>();
		
		// 열 기준 탐색
		for(int i = 0; i < N; i++) {
			int row = N-1;
			for(int j = 0; j < N; j++) {
				if(map[j][i] == -1) { // 검정 블럭을 만난 경우 그 위로 중력 작용
					row = j;
					stack.add(map[j][i]);
					map[j][i] = -2;
					while(!stack.isEmpty()) {
						map[row--][i] = stack.pop();
					}
					row = N-1;
				} else if(map[j][i] >= 0) {
					stack.add(map[j][i]);
					map[j][i] = -2;
				}
			}
			// N-1열까지 탐색한 후 스택 비우기
			while(!stack.isEmpty()) {
				map[row--][i] = stack.pop();
			}
		}
	}
	
	// 4. 90도 반시계 회전
	public static int[][] rotate() {
		int[][] Map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				Map[i][j] = map[j][N-1-i];
			}
		}
		return Map;
	}
	
	// 확인용 배열 출력 함수
	public static void print(int[][] array) {
		for(int[] arr : array) {
			for(int k : arr) {
				System.out.print(k + " ");
			}
			System.out.println();
		}
	}

}
