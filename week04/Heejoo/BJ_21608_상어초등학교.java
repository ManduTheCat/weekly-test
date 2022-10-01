package 새터데이.sat221001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Place implements Comparable<Place>{
	int x, y;
	int like, cnt;
	
	Place(int x, int y, int like, int cnt){
		this.x = x;
		this.y = y;
		this.like = like;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Place o) {
		// 1. 좋아하는 학생
		if(this.like > o.like) return -1;
		else if(this.like < o.like) return 1;
		else { // 2. 비어있는 칸
			if(this.cnt > o.cnt) return -1;
			else if(this.cnt < o.cnt) return 1;
			else { // 3. 행
				if(this.x < o.x) return -1;
				else if(this.x > o.x) return 1;
				else { // 4. 열
					if(this.y < o.y) return -1;
					else return 1;
				}
			}
		}
	}
	
	
}
public class BJ_21608_상어초등학교_문희주 {
	static int N;
	static int[] seq;
	static int[][] map, array, dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 호감 리스트 입력
		seq = new int[N*N]; // 학생 순서
		array = new int[N*N][4]; // 명단
		for(int i = 0; i < N*N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			seq[i] = Integer.parseInt(st.nextToken());
			for(int j = 0; j < 4; j++) {
				array[seq[i]-1][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for(int i = 0; i < N*N; i++) {
//			for(int j = 0; j < 4; j++) {
//				System.out.print(array[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		map = new int[N][N];
		for(int idx : seq) {
			func(idx);
		}
		
		System.out.println(getSatis());
		
	}
	public static void func(int idx) {
		PriorityQueue<Place> pq = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] != 0) continue;
				int like = 0;
				int cnt = 0;
				for(int k = 0; k < 4; k++) {
					int dx = i + dir[k][0];
					int dy= j + dir[k][1];
					
					if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
					if(map[dx][dy] == 0) cnt++;
					else if(checkLike(idx, map[dx][dy])) like++;
					
					pq.add(new Place(i, j, like, cnt));
				}
			}
		}
		
		Place now = pq.poll();
		

		map[now.x][now.y] =  idx;
	}
	
	public static boolean checkLike(int idx, int cons) {
		for(int i = 0; i < 4; i++) {
			if(array[idx-1][i] == cons) return true;;
		}
		return false;
	}
	public static int getSatis() {
		int result = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int r = map[i][j];
				int count = 0;
				
				for(int k = 0; k < 4; k++) {
					int dx = i + dir[k][0];
					int dy = j + dir[k][1];
					
					if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
					if(checkLike(r, map[dx][dy])) count++;
				}
				
				switch (count) {
				case 0:
					result += 0;
					break;
				case 1:
					result += 1;
					break;
				case 2:
					result += 10;
					break;
				case 3:
					result += 100;
					break;
				case 4:
					result += 1000;
					break;
				}
			}
		}
		return result;
	}
	
	public static void print(int[][] array) {
		for(int[] arr : array) {
			for(int k : arr) {
				System.out.print(k + " ");
			}
			System.out.println();
		}
	}
}
