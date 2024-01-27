package swea;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 낚시터자리잡기 {
	
	static int N, ans;
	static Point[] gate = new Point[4];
	static int[] selected= new int[4];
	static Point[] input = new Point[4];
	static boolean[] visited = new boolean[4];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			ans = Integer.MAX_VALUE;
			
			for(int i = 1; i <= 3; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				gate[i] = new Point(x, y);
			}
			
			perm(0, 1);
			sb.append("#" + tc + " " + ans + "\n");
			
		}
		System.out.println(sb);
		
	}
	
	static void perm(int cnt, int index) {	// gate 입장 순서를 정하기 위한 순열
		
		if(cnt == 3) {
			int[] seat = new int[N + 1];
			func(1,seat);
			return;
		}
		
		for(int i = 1; i <= 3; i++) {
			if(!visited[i]) {
				visited[i] = true;
				input[index] = gate[i];
				perm(cnt + 1, index + 1);
				visited[i] = false;
				
			}
		}
	}
	
	static void func(int cnt, int[] seat) {
		
		if(cnt == 4) {
			int sum = 0;
			for(int i = 1; i <= N; i++) {
				sum += seat[i];
			}
			ans = Math.min(ans, sum);
			return;
		}
		
		for(int i = 0; i < input[cnt].y; i++) {
			
			if(seat[input[cnt].x] == 0) {	
				seat[input[cnt].x] = 1;
				continue;
			}
			
			int left = findLeft(input[cnt].x, seat);
			int right = findRight(input[cnt].x, seat);
			
			if(i == input[cnt].y - 1) {	// 마지막 사람 입장
				if(input[cnt].x - left == right - input[cnt].x) {	// 왼쪽 오른쪽 거리차가 같으면 왼쪽에 넣은 경우, 오른쪽에 넣는 경우 따로 설정
					int[] leftCopy = Arrays.copyOf(seat, seat.length);
					int[] rightCopy = Arrays.copyOf(seat, seat.length);
					if(left != Integer.MAX_VALUE) {
						leftCopy[left] = input[cnt].x - left + 1;
						func(cnt + 1, leftCopy);
					}
					if(right != Integer.MAX_VALUE) {
						rightCopy[right] = right - input[cnt].x + 1;
						func(cnt + 1, rightCopy);
					}
				}else {	// 거리 차이가 같지 않다면 짧은 쪽에 추가
					if(input[cnt].x - left > right - input[cnt].x) {
						seat[right] = right - input[cnt].x + 1;
						func(cnt + 1, seat);
					}else if(input[cnt].x - left < right - input[cnt].x && left != Integer.MAX_VALUE) {
						seat[left] = input[cnt].x - left + 1;
						func(cnt + 1, seat);
					}
				}
			}else {	// 
				if(input[cnt].x - left > right - input[cnt].x) {
					seat[right] = right - input[cnt].x + 1;
				}else if(input[cnt].x - left < right - input[cnt].x && left != Integer.MAX_VALUE) {
					seat[left] = input[cnt].x - left + 1;	
				}
				else {				
					seat[left] = input[cnt].x - left + 1;				
				}	
			}
		}
	}
	
	static int findLeft(int x, int[] seat) {
		int index = x;
				
		for(int i = index ; i >= 1; i--) {
				if(seat[i] == 0) {
					return i;
				}
		}
		return	Integer.MAX_VALUE;
		
	}
	
	static int findRight(int x, int[] seat) {
			
			int index = x;
			
			for(int i = index ; i <= N; i++) {
					if(seat[i] == 0) {
						return i;
					}
			}
			return	Integer.MAX_VALUE;
	}

}
