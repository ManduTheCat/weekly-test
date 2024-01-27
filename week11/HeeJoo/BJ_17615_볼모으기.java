package 새터데이.sat230114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/*
 * 빨간색 볼, 파란색 볼 -> 일직선상에 섞여 놓여 있음
 * 볼을 옮겨서 같은 색 볼끼리 인접하게 놓이도록 하려고 함
 * 규칙1. 바로 옆에 다른 색깔의 볼이 있으면 그 볼을 모두 뛰어 넘어 옮길 수 있음.
 * 즉, 빨볼은 옆에 있는 파볼 무더기를 한 번에 뛰어 넘어 옮길 수 있음.
 * 규칙2. 옮길 수 있는 볼의 색깔은 한 가지
 * 최소 이동횟수를 찾는 프로그램
 */

public class BJ_17615_볼모으기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); // 볼의 총 개수 N
		String input = br.readLine(); // 볼에 관한 정보
		
		int Rcnt = 0;
		int Bcnt = 0;
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == 'R') Rcnt++;
			else Bcnt++;
		}
		
		int cnt = (int)1e9; // 이동해야 하는 공의 개수
		int index = 0; // 기준 위치 공의 개수
		
		// 1. 빨간 공 이동
		// 1-1. 빨간 공을 왼쪽으로
		while(index < N && input.charAt(index) == 'R') {
			index++;
		}
		
		cnt = Math.min(cnt, Rcnt - index);

		// 1-2. 빨간 공을 오른쪽으로
		index = 0;
		while(index < N && input.charAt(N - 1 - index) == 'R') {
			index++;
		}
		
		cnt = Math.min(cnt, Rcnt - index);

		// 2. 파란 공 이동
		// 2-1. 파란 공을 왼쪽으로
		index = 0;
		while(index < N && input.charAt(index) == 'B') {
			index++;
		}
		
		cnt = Math.min(cnt, Bcnt - index);
		
		// 2-2. 파란 공을 오른쪽으로
		index = 0;
		while(index < N && input.charAt(N - 1 - index) == 'B') {
			index++;
		}
		
		cnt = Math.min(cnt, Bcnt - index);
	
		sb.append(cnt);
		System.out.println(sb);
	}
}
