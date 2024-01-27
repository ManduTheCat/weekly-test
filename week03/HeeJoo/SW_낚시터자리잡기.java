package 새터데이;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 하나의 출입구씩 선택하여 순차적 입장
 * 2. 자신의 위치에서 가장 가까운 빈 낚시터에 자리잡기
 * 3. 해당 출입구의 마지막 사람의 경우, 가장 가까운 빈 자리가 두 곳이라면 하나를 선택
 * 낚시꾼들 각각의 이동거리를 모두 더한 값이 최소가 되도록 자리잡는 방법
 * 
 * 1. 입구 순서 선택 ~ 순열
 * 2. 낚시꾼 투입
 * 가장 짧은 거리가 비어있으면 투입
 * 1명 남은 경우, 가장 짧은 거리가 두 가지라면 분기 나누기
 */

public class SW_낚시터자리잡기 {

	static int N, minCost;
	static int[] selected, order;
	static int[][] gate = new int[3][2]; // 3개의 게이트 : {위치, 인원}
	public static void main(String[] args) throws IOException {
		File file = new File("res/input.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 총 테스트 케이스 개수 T
		for(int tc = 1; tc <= T; tc++){
			N = Integer.parseInt(br.readLine()); // 낚시터의 개수 N
			
			for(int i = 0; i < 3; i++) {
				st = new StringTokenizer(br.readLine());
				gate[i][0] = Integer.parseInt(st.nextToken()) - 1; // 출입구의 번호
				gate[i][1] = Integer.parseInt(st.nextToken()); // 해당 게이트에 대기 중인 인원
			}
			
			minCost = (int)1e9;
			order = new int[3];
			perm(0, 0);
			
			System.out.println("#" + tc + " " + minCost);
		}
		
		
	}
	public static void perm(int cnt, int flag) {
		if (cnt == 3) {
			// 낚시꾼 자리잡기
			selected = new int[N];
			DFS(0, 0);
			return;
		}
		
		for(int i = 1; i <= 3; i++) {
			if((flag & 1 << i) != 0) continue;
			order[cnt] = i;
			perm(cnt+1, flag | 1 << i);
		}
	}
	
	public static void DFS(int cnt, int cost) {
		if(cnt >= 3) {
			minCost = Math.min(minCost, cost);
			return;
		}
		
		int start = gate[order[cnt]-1][0]; // 선택된 게이트
		int p = gate[order[cnt]-1][1]; // 게이트 대기 인원
		
		// 1명이 될 때까지 가까운 곳에 순차적 투입
		// 1명이 됐을 때 두 가지 경우가 존재하면 나눠서 진행
		int index = 0;
		while(p > 1) {
			if(check(start-index) && selected[start-index] == 0) {
				selected[start-index] = 1;
				cost += index + 1;
				p--;
			}
			if(check(start+index) && selected[start+index] == 0) {
				selected[start+index] = 1;
				cost += index + 1;
				p--;
			}
			index++;
		}
		// 분기 나누기
		if(p == 0) {
			DFS(cnt+1, cost);
		}
		else if(p == 1) {
			int[] copy = selected.clone();
			boolean flag = false;
			while(true) {
				if(check(start-index) && selected[start-index] == 0) {
					flag = true;
					break;
				}
				if(check(start+index) && selected[start+index] == 0) {
					flag = true;
					break;
				}
				index++;
			}
			if(check(start-index) && selected[start-index] == 0) {
				selected[start-index] = 1;
				cost += index + 1;
				DFS(cnt+1, cost);
				cost -= index + 1;
				selected[start-index] = 0;
			}
			selected = copy.clone();
			if(check(start+index) && selected[start+index] == 0) {
				selected[start+index] = 1;
				cost += index + 1;
				DFS(cnt+1, cost);
				cost -= index + 1;
				selected[start+index] = 0;
			}
		}
	}
	
	public static void BFS(int cnt) {
		if(cnt == 1) {
			
		}
		int index = 0;
	}

	public static boolean check(int index) {
		if(index < 0 || index >= N) return false;
		return true;
	}
}
