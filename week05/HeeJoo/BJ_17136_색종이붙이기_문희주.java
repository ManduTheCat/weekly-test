package 새터데이.sat221008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 다섯 종류의 색종이 : 1x1 ~ 5x5 ~ 각각 5개씩 보유
 * 색종이를 10x10 종이 위에 붙이려고 함 ~ 각각의 칸에는 0 or 1이 적혀 있음
 * 1이 적힌 칸은 모두 색종이로 덮여져야 함
 * 색종이를 붙일 때는 종이의 경계 밖으로 나가서는 안되고, 겹쳐도 안됨
 * 0이 적힌 칸에는 색종이가 있으면 안됨
 * 1이 적힌 모든 칸을 붙이는데 필요한 색종이의 최소 개수를 구하는 프로그램
 * 
 * 1. 1은 색종이, 0은 색종이 xxx
 * 2. 딱 맞는 크기, 겹 xxx
 * 
 * 1. 5 -> 1
 * 2. 크기별 정사각형 ~ ABCDE
 */

public class BJ_17136_색종이붙이기_문희주 {
	static final int INF = (int)1e9;
	static int cnt;
	static char[][] Map;
	static int[] cp = new int[] {0, 5 ,5 ,5 ,5 ,5};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map = new char[10][10];
		int sum = 0; // 색종이를 붙여야 하는 칸의 수
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++) {
				Map[i][j] = st.nextToken().charAt(0);
				
				if(Map[i][j] == '1') sum++;
			}
		}
		
		cnt = INF;

		DFS(0, 0, 0, sum);

		if(cnt == INF) System.out.println(-1);
		else System.out.println(cnt);
	}
	
	// (x, y)를 기준으로 색종이 붙이기
	public static void DFS(int x, int y, int count, int sum) {
		if(y == 10) { // 행 이동
			x++;
			y = 0;
		}
		
		if(count > cnt) return; // 가지치기 : 기록된 최솟값보다 크면 return
		
		if(sum == 0) { // 모든 칸을 붙인 경우 ...
			cnt = Math.min(cnt, count);
			return;
		}
		
		if(!isRange(x, y)) return; // (x, y)의 범위 체크
		
		if(Map[x][y] != '1') DFS(x, y+1, count, sum); // '0'이면 다음 좌표로 이동
		else { // '1'인 경우
			for(int k = 5; k > 0; k--) { // 크기가 큰 5x5부터 붙여보기
				if(search(x, y, k)) { // 크기가 k인 종이를 붙일 수 있는지 확인
					DFS(x, y+1, count+1, sum-change(x, y, k, '0')); // 해당 종이를 붙인 채로 DFS
					change(x, y, k, '1'); // 종이 떼기
				}
			}
		}
	}
	
	public static boolean search(int x, int y, int size) {
		for(int i = x; i < x + size; i++) {
			for(int j = y; j < y + size; j++) {
				if(!isRange(i, j)) return false;
				if(Map[i][j] != '1') {
					return false;
				}
			}
		}
		
		if(cp[size] > 0) {
			return true;
		}

		return false;
	}
	
	public static int change(int x, int y, int size, char flag) {
		for(int i = x; i < x+size; i++) {
			for(int j = y; j < y+size; j++) {
				Map[i][j] = flag;
			}
		}
		
		if(flag == '1') cp[size]++;
		else cp[size]--;
		
		return size * size;
	}
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || y < 0 || x >= 10 || y >= 10) return false;
		return true;
	}
	
	public static void print(char[][] map) {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
