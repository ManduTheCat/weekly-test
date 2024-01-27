import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_낚시터자리잡기_안수빈 {

	static int T, N, gate[][], selected[], ans;
	static boolean finish, isReserved[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			// 0: 출입구의 위치, 1: 해당 출입구에 대기하고 있는 낚시꾼들의 수
			gate = new int[3][2]; 
			for (int i = 0; i < 3; i++) {
				st = new StringTokenizer(br.readLine());
				gate[i][0] = Integer.parseInt(st.nextToken()) - 1;
				gate[i][1] = Integer.parseInt(st.nextToken());
			}
			
			selected = new int[3];
			ans = Integer.MAX_VALUE;
			perm(0, 0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	// 게이트 문 여는 순서 모든 경우의 수: 3P3
	public static void perm(int cnt, int flag) {
		if (cnt == 3) {
			isReserved = new boolean[N];
			finish = false;
			simul(0, gate[selected[0]][1], 0, 0);
			return ;
		}
		
		for (int i = 0; i < 3; i++) {
			if ((flag & 1 << i) != 0) continue ;
			selected[cnt] = i;
			perm(cnt + 1, flag | 1 << i);
		}
	}
	
	// i: 순열 번호, remainPerson: 남은 낚시꾼의 수, dis: 낚시터까지의 거리, disSum: 모든 낚시꾼의 이동 거리의 합
	public static void simul(int i, int remainPerson, int dis, int disSum) {
		if (remainPerson == 0) { // 남은 사람이 없다면
			if (++i == 3) { // 게이트 이동, 이때 모든 게이트를 돌았다면
				finish = true; // finish 설정
				ans = Math.min(ans, disSum); // 정답 최소값 갱신				
				return; // 종료
			}
			remainPerson = gate[selected[i]][1]; // 현재 남은 낚시꾼의 수를 다음 게이트에 대기중인 낚시꾼의 수로 갱신
			dis = 0; // 낚시터까지의 거리 초기화
		}
		int gateNo = gate[selected[i]][0]; // 게이트 번호
		
		while (!finish) {
			// 마지막 사람이고 갈 수 있는 낚시터가 여러개라면
			if (remainPerson == 1 && 
				gateNo - dis > 0 && !isReserved[gateNo - dis] && 
				gateNo + dis < N && !isReserved[gateNo + dis]) {
				boolean[] temp = Arrays.copyOf(isReserved, N); // 현재 낚시꾼 방문 상태 저장
				// 왼쪽 낚시터 방문
				isReserved[gateNo - dis] = true;
				simul(i, remainPerson - 1, dis, disSum + dis + 1);
				
				// 오른쪽 낚시터 방문
				finish = false;
				isReserved = temp;
				isReserved[gateNo + dis] = true;
				simul(i, remainPerson - 1, dis, disSum + dis + 1);
				return ;
			}
			
			if (gateNo - dis >= 0 && !isReserved[gateNo - dis]) { // 왼쪽 낚시터 방문
				isReserved[gateNo - dis] = true;
				simul(i, remainPerson - 1, dis, disSum + dis + 1);
			} else if (gateNo + dis < N && !isReserved[gateNo + dis]) { // 오른쪽 낚시터 방문
				isReserved[gateNo + dis] = true;
				simul(i, remainPerson - 1, dis, disSum + dis + 1);
			} else { // 둘다 못 간다면 낚시터까지의 거리 증가시키기
				++dis;
			}
		}
	}
	
}
