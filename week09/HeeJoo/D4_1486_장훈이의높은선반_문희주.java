package 새터데이.sat221112;

import java.io.*;
import java.util.StringTokenizer;

/*
 * 높이 B인 선반 , N명의 점원, 각 점원의 키는 Hi
 * 탑을 쌓아서 선반 위의 물건 사용
 * 탑은 점원 1명 이상, 2명 이상일 경우 탑을 만든 모든 점원의 키의 합
 * 높이가 B 이상인 탑 중에서 높이가 가장 낮은 탑을 구하는 프로그램
 * 
 * 출력 : 만들 수 있는 높이가 B 이상인 탑 중에서 탑의 높이와 B의 차이가 가장 작은 것
 */

public class D4_1486_장훈이의높은선반_문희주 {
	static int N, B, ans;
	static int[] height;
	public static void main(String[] args) throws IOException {
		//File file = new File("res/input1.txt");
		//BufferedReader br = new BufferedReader(new FileReader(file));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 점원 수
			B = Integer.parseInt(st.nextToken()); // 선반 높이
			
			// 점원들의 키
			height = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = (int)1e9;
			permu(0, 0, 0);
			
			sb.append("#" + tc + " " + Math.abs(B-ans) + "\n");
			
		}
		System.out.println(sb);
	}
	
	public static void permu(int cnt, int idx, int sum) {
		if(idx == N) {
			if(sum >= B) {
				ans = Math.min(ans, sum);
			}
			return;
		}
		
		permu(cnt+1, idx+1, sum+height[idx]);
		permu(cnt, idx+1, sum);
	}

}
