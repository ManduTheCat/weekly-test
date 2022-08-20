import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 총 N이닝 게임
 * 한 이닝에 3아웃 = 이닝 종료 ~ 공수교대
 * 경기 시작 전 타순 결정 ~ 변경 불가능
 * 9번 타자가지 쳤는데 3아웃 xxx ~ 1번 타자로 되돌아감
 * 타순은 이닝이 변경되어도 순서 유지 ~ 이전 이닝 6번 타자 종료 > 다음 이닝 7번 타자 시작
 * 1번 선수를 4번 타자로 미리 결정 ~ 나머지 타순 결정
 * 가장 많은 특점을 하는 타순을 찾고, 그 때의 득점 구하기
 * 각 이닝에는 아웃을 기록하는 타자가 적어도 한 명 존재
 */

public class BJ_17281_야구_문희주 {
	static int n, max = 0;
	static int[][] map;
	static int[] selected;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine()); // 이닝 수 N
		
		map = new int[n][9]; // 각 선수가 각 이닝에서 얻는 결과
		// 행 = 이닝, 열 = 선수
		// 아웃(0), n루타(n), 홈런(4)
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		selected = new int[9];
		permu(0, 0);
		
		System.out.println(max);
		
	}
	
	// 순열 인덱스, 비트마스크 플래그
	public static void permu(int cnt, int flag) {
		 // 4번 타자는 1번 선수로 미리 결정됨
		if (cnt == 3) {
			permu(cnt + 1, flag);
			return ;
		}
    
    // 순열 완성
		if(cnt == 9) {	
			max = Math.max(max, game()); // 해당 순열로 진행한 게임 득점과 최고 득점 대소비교 및 갱신
			return;
		}
		
		// 1번 선수(i = 0)는 이미 4번 타자로 결정 ~ 1부터 시작
		for(int i = 1; i < 9; i++) {
			// i번째 선수의 순서가 이미 정해졌다면 해당 비트 == 1 ~ continue
			if ((flag & 1 << i) != 0) continue;
			
			// i번째를 cnt번 타자로 결정
			selected[cnt] = i;
			
			// ex) 2번 타자 == 2번 선수(i = 1)
			// flag = 0000, 1 << i = 0010
			// flag | 1 << i = 0010 : 2번 선수 선택됨
			// 1번 타자는 고정이라 0이여도 상관 없음
			// ex) 3번 타자 = 3번 선수(i = 2)
			// flag = 0010,  1 << i = 0100
			// flag | 1 << i = 0110 : 2번, 3번 선수 선택됨
			permu(cnt + 1, flag | 1 << i);
		}
	}

	
	// num을 비트마스크로 언젠간 바꾸겟지 ..
	public static int game() {
		int out = 0; // 아웃
		int result = 0; // 득점
		boolean[] num; // index루 주자 존재 여부
		int index = 0; // 타자
		
		for(int i = 0; i < n; i++) {
			// 새 이닝 ~ 123루, 아웃 초기화
			out = 0; 
			num = new boolean[4]; // 0인덱스 사용 x
			
			
			while(out < 3) {
				index = index % 9;
///////////////////////////////////////////////////////
				// 아웃
				if(map[i][selected[index]] == 0) {
					out++;
				}
				// 홈런 = 타자 + 주자
				else if(map[i][selected[index]] == 4) {
					for(int j= 1; j < 4; j++) {
						if(num[j]) result++; // 주자가 존재하면 득점
						num[j] = false; // 해당 루수 비워두기
					}
					result++; // 타자 득점
				}
				// 123루타
				// 1. 주자 여부
				// 2. 기존 주자 이동
				// 3. 타자 이동
				else { // 123루타
					int now = map[i][selected[index]];
					
					// 3루부터 득점 판단
					for(int j= 3; j >= 1; j--) {
						// 주자 존재
						if(num[j]) {
							if(getScore(j + now)) { // 다음 이동이 홈루인 경우
								result++; // 득점
								num[j] = false; // 주자 없애기
							}
							else { // 다음 이동이 홈루가 아닌 경우
								// 주자 이동
								num[j] = false;
								num[j+now] = true;
							}
						}
					}
					num[now] = true; // 타자 이동
				}
///////////////////////////////////////////////////////
				index++; // 다음 타자로 변경
			}
		}
		
		return result; // 최종 득점
	}
	
	// 홈루에 들어왔는지 판단
	public static boolean getScore(int k) {
		if(k > 3) return true;
		return false;
	}
}
