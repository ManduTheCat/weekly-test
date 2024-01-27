package 새터데이.sat221029;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 길이가 N인 컨베이어 벨트와 2N인 벨트가 N벨트를 위아래로 감싸며 돌고 있음
 * 1부터 2N까지의 번호
 * 벨트 회전 1 > 2 > 3 ... 2N > 1
 * 1번 : 올리는 위치
 * N번 : 내리는 위치
 * 로봇은 1번에서만 올릴 수 있고, N번에 도착하면 즉시 내림
 * 로봇을 올리거나 칸을 이동하면 해당 칸의 내구도 -1
 * 
 * ** N번에 로봇이 있으면 바로 내리기
 * 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
 * 2. 가장 먼저 벨트에 올라간 로봇부터, 회전 방향으로 한 칸 이동이 가능하다면 이동
 * 2-1. 이동하려는 칸에 로봇 x
 * 2-2. 해당 칸의 내구도 1 이상
 * 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 로봇 올리기
 * 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정 종료
 * 
 * 종료되었을 때 몇 번째 단계가 진행 중이었는지 구하기
 */

class Block{
	int dura;
	int robot = 0;
	
	public Block(int dura) {
		this.dura = dura;
	}
	
}
public class BJ_20055_컨베이어벨트위의로봇_문희주 {
	static int N, K;
	static Block[] belt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 벨트 크기 N
		K = Integer.parseInt(st.nextToken()); // 종료 조건 K
		
		st = new StringTokenizer(br.readLine());
		belt = new Block[2 * N];
		for(int i = 0; i < 2 * N; i++) {
			int dura = Integer.parseInt(st.nextToken());
			belt[i] = new Block(dura);
		}
		
		System.out.println(simul());
	}
	
	// 1. 회전 ~ 내리기
	// 2. 순서대로 한 칸 이동 ~ 내리기
	// 3. 로봇 올리기
	// 4. 내구도 0인 칸의 개수 체크
	// 응 지옥의 이프문이야 ~~ 하 .. 개빡세네
	public static int simul() {
		int cnt = 0; // 단계
		
		int k = 0; // 내구도 0인 칸의 개수
		
		Queue<Integer> q = new ArrayDeque<>(); // 로봇 인덱스
		while(k < K) { // 내구도 0인 칸이 K개 이상이면 종료
			// 1. 회전
			rotate();
			if(belt[N-1].robot == 1) belt[N-1].robot = 0; // 로봇 내리기
			
			// 2. 순서대로 한 칸 이동
			int size = q.size();
			while(size-- > 0) {
				int idx = q.poll() + 1; // 벨트가 회전해서 인덱스 + 1
				
				if(idx == N - 2) { // N - 2
					if(belt[idx+1].dura >= 1) { // belt[N-1]은 비어있음
						belt[idx+1].dura -= 1; // 내구도 -1
						belt[idx].robot = 0; // 이전 칸 로봇 x
						// 로봇 내리므로 큐에 안넣음
					}
				} else if (idx == 2 * N - 1){ // 2N - 1
					if(belt[0].robot == 0 && belt[0].dura >= 1) { // 0인덱스로 돌아감
						belt[0].robot = 1; // 로봇 이동
						belt[0].dura -= 1; // 내구도 -1
						belt[idx].robot = 0; // 이전 칸 로봇 x
						q.offer(0); // 이동한 칸의 인덱스
					} else q.offer(idx); // 이동 못한 경우
				} else if(idx == N - 1){ // N - 1
					continue; // 로봇 내려서 없음
				} else { // 0 ~ 2N - 2 | N - 1, N - 2 제외
					// 2-1. 다음 칸 로봇 x
					// 2-2. 다음 칸 내구도 1 이상
					if(belt[idx+1].robot == 0 && belt[idx+1].dura >= 1) {
						belt[idx+1].robot = 1; // 로봇 이동
						belt[idx+1].dura -= 1; // 내구도 -1
						belt[idx].robot = 0; // 이전 칸 로봇 x
						q.offer(idx + 1); // 이동한 칸의 인덱스
					} else q.offer(idx); // 이동 못한 경우
				} 
			}
			
			// 3. 로봇 올리기
			if(belt[0].robot == 0 && belt[0].dura >= 1) {
				belt[0].robot = 1;
				belt[0].dura -= 1;
				q.offer(0);
			}
			
			// 4. 내구도 0인 칸의 개수 세기
			k = 0;
			for(int i = 0; i < 2 * N; i++) {
				if(belt[i].dura == 0) k++;
			}
			cnt++;
		}
		
		return cnt;
	}
	
	// 배열 회전
	public static void rotate() {
		Block b = null; // 다음 블록
		Block tmp = belt[0]; // 현재 회전하는 블록
		for(int i = 1; i < 2 * N; i++) {
			b = belt[i];
			belt[i] = tmp;
			tmp = b;
		}
		belt[0] = b; // 마지막 블록은 시작지점(0번)으로
	}
	
	public static void print(Block[] array) {
		for(Block b : array) {
			System.out.printf("(%d, %d) ", b.dura, b.robot);
		}
		System.out.println();
	}
}
