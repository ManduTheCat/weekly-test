package 새터데이.sat221022;

import java.awt.Point;
import java.io.*;
import java.util.*;

/*
 * NxN 정사각형
 * 점심을 먹기 위해 최대한 빠른 시간 내에 아래 층으로 내려가기
 * P : 방 안의 사람들
 * S : 계단 입구
 * 이동 완료 시간 = 모든 사람들이 계단을 내려가 아래 층으로 이동을 완료한 시간
 * 이동 시간 = |x1 - x2| + |y1 - y2|
 * 계단을 내려가는 시간 !!! 계단 입구에 도착하면 1분 후 아래 칸으로 내려가기
 * 계단 위에는 동시에 최대 3명까지만 올라갈 수 있음
 * 이미 계단을 3명이 내려가고 있는 경우, 그 중 한 명이 계단을 완전히 내려갈 때까지 계단 입구에서 대기
 * 계단마다 길이 K가 주어지며, 계단에 올라간 후 완전히 내려가는데 K분 소모
 * 모든 사람들이 계단을 내려가 이동이 완료되는 시간이 최소가 되는 경우를 구하는 프로그램
 * 
 * 계단의 입구는 반드시 2개
 * Reference is Good ;)
 */

class Person implements Comparable<Person>{
	int x, y;
	int s = -1; // 선택된 계단
	int arrivalTime = 0; // 선택된 계단에 도착하는 시간
	int stairTime = 0; // 내려가는데 소요되는 시간
	
	public Person(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// 계단에 도착하는 시간을 기준으로 오름차순
	public int compareTo(Person o) {
		return this.arrivalTime - o.arrivalTime;
	}
	
}

class Stair{
	int x, y;
	int k; // 해단 계단을 내려가는데 걸리는 시간
	
	public Stair(int x, int y, int k) {
		this.x = x;
		this.y = y;
		this.k = k;
	}
}

public class SW_2383_점심식사시간 {
	static int N, count, ans;
	static int[][] map, dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
	static List<Person> list;
	static Stair[] stairs = new Stair[2]; // 계단 2개 고정
	
	public static void main(String[] args) throws IOException {
		File file = new File("res/input.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			count = 0; // 총 인원 수
			
			int[][] map = new int[N][N];
			list = new ArrayList<>(); // 사람들의 좌표
			list.add(null); // 0인덱스 안씀
			
			int idx = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					// 사람
					if(map[i][j] == 1) {
						list.add(new Person(i, j));
						count++;
					}
					
					// 계단
					if(map[i][j] > 1) stairs[idx++] = new Stair(i, j, map[i][j]);
				}
			}
			
			ans = (int)1e9;
			perm(0, 1);
			
			sb.append("#" + tc + " " + ans + "\n");
		}
		
		System.out.println(sb);
	}
	
	public static void perm(int cnt, int idx) {
		if(cnt == count) {
			getTime();
			//System.out.println(Arrays.toString(selected));
			return;
		}
		
		if(idx > count) return;
		
		list.get(idx).s = 0;
		perm(cnt+1, idx+1); // idx번째 사람이 1번 계단으로 가는 경우
		list.get(idx).s = 1;
		perm(cnt+1, idx+1); // idx번째 사람이 2번 계단으로 가는 경우
	}
	
	/*
	 * 계단 진입과 탈출은 동시간대 가능 ~ 탈출 먼저하고 진입하기
	 */
	public static void getTime() {
		int time = 1; // 행동을 먼저하기 위해 1초부터 시작
		int cnt = 0; // 내려가기를 완료한 인원
		
		// 계단 도착 시간을 기준으로 오름차순 정렬
		PriorityQueue<Person> pq = new PriorityQueue<>(); // 1번 계단
		PriorityQueue<Person> pq2 = new PriorityQueue<>(); // 2번 계단
		for(int idx = 1; idx <= count; idx++) {
			Person p = list.get(idx);
			if(p.s == 0) {
				p.arrivalTime = getDist(p.x, p.y, stairs[0].x, stairs[0].y);
				p.stairTime = stairs[0].k;
				pq.offer(p);
			} else {
				p.arrivalTime = getDist(p.x, p.y, stairs[1].x, stairs[1].y);
				p.stairTime = stairs[1].k;
				pq2.offer(p);
			}
		}
		
		
		Queue<Person>[] qs = new ArrayDeque[2]; // 계단들의 상태
		qs[0] = new ArrayDeque<>(); // 1번 계단
		qs[1] = new ArrayDeque<>(); // 2번 계단
		while(true) {
			if(ans < time) return; // 최소 시간을 넘는 경우 return
			
			// 1. 내려가기
			for(Queue<Person> q : qs) {
				int size = q.size(); // 현재 계단에 진입한 사람 수만큼
				
				for(int i = 0; i < size; i++) {
					Person p = q.poll();
					
					p.stairTime -= 1; // 한 칸 내려가기
					if(p.stairTime == 0) { // 정해진 시간만큼 내려왔으면
						cnt++; // 완료 인원 추가
						continue; // 탈출
					}
					
					q.offer(p); // 아직 덜 내려갔으면 다시 큐에 삽입
					
				}
			}
			
			if(cnt == count) { // 모든 인원이 내려온 경우
				ans = Math.min(ans, time); // 최소 시간 갱신
				return;
			}
			
			// 2. 계단 진입 따로따로
			// 2-1. 1번 계단 진입
			while(!pq.isEmpty()) {
				Person p = pq.poll();
				int idx = p.s;
				// 1. 계단 입구에 도착하고 1분 후 내려갈 수 있음
				// => 3명이 차있어서 못들어간 경우, 도착시간 + 1보다 현재 시간이 커야 함
				// 2. 동시에 계단을 이용할 수 있는 최대 인원 수는 3명
				// => 큐 사이즈 3 미만
				if(p.arrivalTime + 1 <= time && qs[idx].size() < 3) {
					qs[idx].offer(p); // 큐 삽입
				} else {
					pq.offer(p); // 다시 pq에 저장
					break; // 더 이상 들어갈 수 없으므로 break
				}
			}
			
			// 2-2. 2번 계단 진입
			while(!pq2.isEmpty()) {
				Person p = pq2.poll();
				int idx = p.s;
				if(p.arrivalTime + 1 <= time && qs[idx].size() < 3) {
					qs[idx].offer(p);
				} else {
					pq2.offer(p);
					break;
				}
			}
			
			time++;
		}
	}
	
	public static int getDist(int x, int y, int a, int b) {
		return Math.abs(x - a) + Math.abs(y - b);
	}
}
