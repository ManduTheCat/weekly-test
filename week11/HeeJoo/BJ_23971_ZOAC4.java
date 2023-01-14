package 새터데이.sat230114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 한 명씩 앉을 수 있는 테이블 이 행마다 W개씩 H행에 걸쳐 있을 때,
 * 모든 참가자는 세로로 N칸 또는 가로로 M칸 이상 비우고 앉아야함
 * 즉, 다른 모든 참가자와 세로줄 번호의 차가 N보다 크거나 가로줄 번호의 차가 M보다 큰 곳에만 앉을 수 있음
 * 최대 몇 명을 수용할 수 있는지 구하기
 */

public class BJ_23971_ZOAC4 {

	public static void main(String[] args) throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int h = 0;
		int w = 0;
		
		if(H%(N+1) == 0) h += H/(N+1);
		else h += H/(N+1) + 1;
		
		if(W%(M+1) == 0) w += W/(M+1);
		else w +=  W/(M+1) + 1;

		sb.append(h * w);
		
		System.out.println(sb);
	}

}
