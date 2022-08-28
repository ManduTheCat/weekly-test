package 새터데이;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 제일 가까운 몹
 * 2. 해당 몹의 고객 vs 다음 몹
 * 3-1. 고객이라면, 현재까지 잡은 몹의 고객 vs 새로운 몹
 * 3-2. 몹이라면, 현재까지 잡은 몹의 고객 vs 새로운 몹

 */
public class SW_헌터 {
	static int M, minDist;
	static Point[] array;
	static int[] selected;
	public static void main(String[] args) throws IOException {
		File file = new File("res/input2.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 총 테스트 케이스 개수 T
		for(int tc = 1; tc <= T; tc++){
			int N = Integer.parseInt(br.readLine()); // 맵의 크기 N
			
			
			int[][] map = new int[N][N];
			M = 0; // 몬스터 수
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(map[i][j] > 0) M++;
				}
			}
			
			// 0 ~ M-1 : 몬스터
			// M ~ 2M -1 : 고객
			array = new Point[M * 2];
			selected = new int[M * 2];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] != 0) {
						int index = map[i][j];
						
						if(index > 0) array[index-1] = new Point(i, j);
						else array[M - index - 1] = new Point(i, j); // 고객은 M부터 시작하며 음수
					}
				}
			}
			minDist = (int)1e9;
			perm(0, 0);
			sb.append("#" + tc + " " + minDist + "\n");
		}
		System.out.println(sb);
	}
	
	public static void perm(int cnt, int flag) {
		if(cnt == 2 * M) { // 순열 완성
			// 이동 거리 계산
			Point p = new Point(0, 0); // 헌터 시작 지점(0, 0)
			int sumDist = 0;
			
			for(int i = 0; i < 2*M ; i++) {
				sumDist += getDist(p, array[selected[i]]);
				
				if(sumDist > minDist) return; // 가지치기 : 현재까지 구한 최소 거리보다 합이 큰 경우 더 이상 진행 x
				p = array[selected[i]]; // 다음 지점으로 시작 점 변경
			}
			
			minDist = sumDist; // 위에서 큰 경우 return했기때문에 sumDist가 최솟값 ~ 최솟값 갱신
			return;
		}
		
		for(int idx = 0; idx < 2 * M; idx++) {
			if((flag & 1 << idx) != 0) continue; // 방문 체크
			if(idx > M -1 && (flag & 1 << (idx - M)) == 0) continue; // 고객 방문 전 의뢰를 완료했는지 체크
			
			selected[cnt] = idx;
			perm(cnt+1, flag | 1 << idx);
		}
		
	}
	
	public static int getDist(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
	
	
}