package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


class Point {
	int r, c, memory, dir;

	public Point(int r, int c, int memory, int dir) {
		super();
		this.r = r;
		this.c = c;
		this.memory = memory;
		this.dir = dir;
	}
	
}

public class D4_1824_혁진이의프로그램검증_이우승 {
	
	static int r, c;
	static char[][] arr;
	static boolean[][][][] visited;
	static int dr[] = {0, 1, 0, -1};	// 0 - 오, 1 - 아, 2 - 왼, 3 - 위
	static int dc[] = {1, 0, -1, 0}; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			visited = new boolean[r][c][16][4];
			
			arr = new char[r][c];
			
			for(int i = 0; i < r; i++) {
				arr[i] = br.readLine().toCharArray();
			}
//			System.out.println("=================" + t +"======================");
			String ans;
			if(bfs()) {
				ans = "YES"; 
			}else {
				ans = "NO";
			}
			
			System.out.println("#" + t + " " + ans);
			
		}
		
	}
	// 37,  64, 65, 66, 67, 68
	static boolean bfs() {
		
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(0, 0, 0, 0));
		
		while(!q.isEmpty()) {
			
			Point p = q.poll();
			
			int cR = p.r;
			if(cR >= r) {
				cR = 0;
			}else if(cR < 0){
				cR = r - 1;
			}
			int cC = p.c;
			if(cC >= c) {
				cC = 0;
			}else if(cC < 0){
				cC = c - 1;
			}
			int memory = p.memory;
			int dir = p.dir;
//			System.out.println("cR : " + cR + " cC : " + cC + " memory : " + memory + " dir : " + dir);
			if(visited[cR][cC][memory][dir]) {
				continue;
			}
			visited[cR][cC][memory][dir] = true;
			
			char ch = arr[cR][cC];
			
			if(ch == '<') {
				dir = 2;
			}else if(ch == '>') {
				dir = 0; 
			}else if(ch == '^') {
				dir = 3;
			}else if(ch == 'v') {
				dir = 1;
			}else if(ch == '_') {
				if(memory == 0) {
					dir = 0;
				}else {
					dir = 2;
				}
			}else if(ch == '|') {
				if(memory == 0) {
					dir = 1;
				}else {
					dir = 3;
				}
			}else if(ch == '?') {
				q.add(new Point(cR + dr[0], cC + dc[0], memory, 0));
				q.add(new Point(cR + dr[1], cC + dc[1], memory, 1));
				q.add(new Point(cR + dr[2], cC + dc[2], memory, 2));
				q.add(new Point(cR + dr[3], cC + dc[3], memory, 3));
			}else if(ch == '@') {
				return true;
			}else if(ch == '+') {
				if(memory == 15) {
					memory = 0;
				}else {
					memory += 1;
				}
			}else if(ch == '-') {
				if(memory == 0) {
					memory = 15;
				}else {
					memory -= 1;
				}
			}else {
				if(ch >= '0' && ch <= '9') {
					memory = ch - '0';
				}
			}
 			q.add(new Point(cR + dr[dir], cC + dc[dir], memory, dir));
		}
		return false;
	}

}
