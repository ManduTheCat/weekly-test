import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class D4_1824_혁진이의프로그램검증_안수빈 {
	
	static int R, C;
	static char[][] arr;
	
	static final int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // 오른쪽, 왼쪽, 위, 아래

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			arr = new char[R][C];
			for (int r = 0; r < R; r++) {
				arr[r] = br.readLine().toCharArray();
			}
			
//			sb.append(Arrays.toString(arr[0])).append("\n");
			sb.append("#").append(t).append(" ").append(checkProgram() ? "YES" : "NO").append("\n");
		}
		System.out.println(sb);
	}

	private static boolean checkProgram() {
		boolean[][][][] visited = new boolean[R][C][16][4];
		Queue<int[]> q = new ArrayDeque<>();
		
		// r, c, memory, moveDir
		q.add(new int[] {0, 0, 0, 0});
		
		while (!q.isEmpty()) {
			int r = q.peek()[0];
			if (r == -1) r = R - 1;
			else if (r == R) r = 0;
			
			int c = q.peek()[1];
			if (c == -1) c = C - 1;
			else if (c == C) c = 0;
			
			int memory = q.peek()[2];
			int moveDir = q.peek()[3];
			
			q.poll();
			
			// 방문 체크
			if (visited[r][c][memory][moveDir]) continue ;
			visited[r][c][memory][moveDir] = true;
			
			switch (arr[r][c]) {
			case '<':
				moveDir = 1;
				break;
			case '>':
				moveDir = 0;
				break;
			case '^':
				moveDir = 2;
				break;
			case 'v':
				moveDir = 3;
				break;
			case '_':
				moveDir = memory == 0 ? 0 : 1;
				break;
			case '|':
				moveDir = memory == 0 ? 3 : 2;
				break;
			case '?':
//				q.add(new int[] {r, c - 1});
//				q.add(new int[] {r, c + 1});
//				q.add(new int[] {r - 1, c});
//				q.add(new int[] {r + 1, c});
				break;
			case '.':
				break;
			case '@':
				return true;
			case '+':
				if (memory == 15) memory = 0;
				else memory += 1;
				break;
			case '-':
				if (memory == 0) memory = 15;
				else memory -= 1;
				break;
			default:
				memory = arr[r][c] - '0';
				break;
			}
			if (arr[r][c] == '?') { // 랜덤으로 갈 수 있음 -> 4방향 다 갈 수 있다..
				for (int i = 0; i < 4; i++) {
					q.add(new int[] {r + dir[i][0], c + dir[i][1], memory, i});
				}
			} else
				q.add(new int[] {r + dir[moveDir][0], c + dir[moveDir][1], memory, moveDir});
		}
		
		return false;
	}
	
}
