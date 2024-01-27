package 새터데이;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

import org.omg.CORBA.INTERNAL;

/*
 * 1. 벽돌 선정
 * 2. 부수기
 * 3. 벽돌 내리기
 * 4. 다음 벽돌 선정
 * 5. ... 수행 후 벽돌 개수 계산
 * 
 * 배열복사의 세계는 복잡하고도 어렵다 ...
 */

class Brick{
	int x, y, range;
	
	public Brick(int x, int y, int range) {
		this.x = x;
		this.y = y;
		this.range = range;
		
	}
}
public class SW_5656_벽돌깨기_문희주 {
	static int[][] map, copy, dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
	static Brick[] top;
	static int[] selected;
	static Queue<Brick> queue = new LinkedList<>();
	static Stack<Integer> stack = new Stack<>();
	static int n, h, w, minBrick;
	static int tc;
	public static void main(String[] args) throws NumberFormatException, IOException {
		File file = new File("res/input.txt");
		BufferedReader br = new  BufferedReader(new FileReader(file));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T
		
		for(tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 구슬 발사 횟수
			w = Integer.parseInt(st.nextToken()); // 열
			h = Integer.parseInt(st.nextToken()); // 행
			
			map = new int[h][w]; // 벽돌 정보
			copy = new int[h][w];
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			selected = new int[n];
			copy = copy(map); // 원본 맵 저장
			minBrick = 200;
			permu(0);
			
			sb.append("#" + tc + " " + minBrick + "\n");
			
		}
		System.out.println(sb);
	}

	public static void permu(int count) {
		if (count == n) {
			map = copy(copy);
			getTop();
			
			for(int i = 0; i < n; i++) {
				Brick target = top[selected[i]];
				
				// 예외 처리
				// 목표 벽돌이 null인 경우 ~ getTop에서 0이면 null
				// 1. 해당 열의 모든 벽돌이 깨짐
				// 2. 전체 모든 벽돌이 깨짐
				if(target == null) {
					// 모든 벽돌이 깨진 경우
					if(getBrickCount() == 0) {
						minBrick = 0;
					}
					return; // 해당 열에서 깰 벽돌이 없는 경우 다음 순열로
				}
				hit(target);
				arrange();
				getTop();
			}
			// 수행을 마치고 최소 벽돌갯수 갱신
			getMinBrick();
			return;
		}
		
		// 중복 순열 : 어떤 열의 벽돌을 깰?까
		for(int i = 0; i < w; i++) {
			selected[count] = i; // count번째에 i열의 벽돌을 깨자 ~
			permu(count+1);
		}
		
	}
	
	// 벽돌 깨기
	public static void hit(Brick brick) {
		queue.add(brick); // 시작 벽돌
		while(!queue.isEmpty()) {
			Brick target = queue.poll();
			int row = target.x;
			int col = target.y;
			int k = target.range;
			
			map[row][col] = 0; // 시작 벽돌 깨기
			
			// 범위 내 벽돌 깨기
			// 1. 1인 경우 해당 벽돌만 깨기
			// 2. 2 이상인 경우 queue에 넣어서 연쇄 수행
			for(int d = 0; d < 4; d++) { // 4방향
				int dx = row;
				int dy = col;
				for(int c = 0; c < k-1; c++) { // 벽돌 값 - 1만큼 연쇄 hit
					dx += dir[d][0];
					dy += dir[d][1];
					
					// 이동한 좌표가 범위를 벗어나는지 확인
					if(dx < 0 || dx >= h || dy < 0 || dy >= w) break;
					
					if(map[dx][dy] > 1) {
						queue.add(new Brick(dx, dy, map[dx][dy]));
					}
					
					map[dx][dy] = 0;
				}
			}
		}
	}
	
	// 벽돌 내리기
	public static void arrange() {
		for (int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				if(map[j][i] != 0) {
					stack.push(map[j][i]);
					map[j][i] = 0;
					
				}
			}
			// 아래부터 채워주기
			int startRow = h - 1;
			while(!stack.isEmpty()) {
				map[startRow--][i] = stack.pop();
			}
			
		}
		
	}
	
	// 최상단 벽돌
	public static void getTop() {
		top = new Brick[w];
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				// 0이 아닐 때만 top에 등록
				if(map[j][i] != 0) {
					top[i] = new Brick(j, i, map[j][i]);
					break;
				}
			}
		}
	}
	
	// 배열 복사
	public static int[][] copy(int[][] arr){
		int[][] array = new int[h][w];
		
		for(int i = 0; i < arr.length; i++) {
			array[i] = arr[i].clone();
		}
		
		return array;
	}
	
	// 남은 벽돌의 개수 구하기
	public static int getBrickCount() {
		int sum = 0;
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if (map[i][j] > 0) sum += 1;
			}
		}
		
		return sum;
	}
	// 남은 벽돌의 최소 개수 갱신
	public static void getMinBrick() {
		int sum = getBrickCount();
		if (minBrick > sum) {
			minBrick = sum;
		}
	}
	
	public static void print(int[][] arr) {
		for(int[] row : arr) {
			for(int x : row) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}
}
