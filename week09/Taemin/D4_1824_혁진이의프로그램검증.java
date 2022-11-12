import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class D4_1824_혁진이의프로그램검증 {
	
	static class Pos {
		int row;
		int col;
		int memory;
		int direction;
		
		public Pos(int row, int col, int memory, int direction) {
			this.row = row;
			this.col = col;
			this.memory = memory;
			this.direction = direction;
		}
	}
	
	static int Testcase, R, C;
	static char[][] Map;
	static Queue<Pos> Queue;
	static boolean[][][][] Visited;
	
	// Direction: North, East, South, West
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= R && col >= 1 && col <= C);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		Testcase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= Testcase; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			Map = new char[R+1][C+1];
			for (int r = 1; r <= R; r++) {
				String line = br.readLine();
				for (int c = 1; c <= C; c++) {
					Map[r][c] = line.charAt(c-1);
				}
			}
			
			Queue = new LinkedList<Pos>();
			Queue.add(new Pos(1, 1, 0, 1));
			Visited = new boolean[R+1][C+1][16][4];
			Visited[1][1][0][1] = true;
			boolean found = false;
			
			while (!Queue.isEmpty()) {
				if (found) break;
				
				Pos p = Queue.poll();
				int row = p.row;
				int col = p.col;
				int direction = p.direction;
				int memory = p.memory;
				
				
				char command = Map[row][col];
				int nextRow, nextCol;
				switch (command) {
				case '<':
					direction = 3;
					nextRow = row + Dx[direction];
					nextCol = col + Dy[direction];
					
					if (!validateRange(nextRow, nextCol)) {
						nextCol = C;
					}
					
					if (!Visited[nextRow][nextCol][memory][direction]) {
						Visited[nextRow][nextCol][memory][direction] = true;
						Queue.add(new Pos(nextRow, nextCol, memory, direction));
					}
					
					break;
				case '^':
					direction = 0;
					nextRow = row + Dx[direction];
					nextCol = col + Dy[direction];
					
					if (!validateRange(nextRow, nextCol)) {
						nextRow = R;
					}
					
					if (!Visited[nextRow][nextCol][memory][direction]) {
						Visited[nextRow][nextCol][memory][direction] = true;
						Queue.add(new Pos(nextRow, nextCol, memory, direction));
					}
					
					
					break;
				case '>':
					direction = 1;
					nextRow = row + Dx[direction];
					nextCol = col + Dy[direction];
					
					if (!validateRange(nextRow, nextCol)) {
						nextCol = 1;
					}
					
					if (!Visited[nextRow][nextCol][memory][direction]) {
						Visited[nextRow][nextCol][memory][direction] = true;
						Queue.add(new Pos(nextRow, nextCol, memory, direction));
					}
					
					break;
				case 'v':
					direction = 2;
					nextRow = row + Dx[direction];
					nextCol = col + Dy[direction];
					
					if (!validateRange(nextRow, nextCol)) {
						nextRow = 1;
					}
					
					if (!Visited[nextRow][nextCol][memory][direction]) {
						Visited[nextRow][nextCol][memory][direction] = true;
						Queue.add(new Pos(nextRow, nextCol, memory, direction));
					}
					
					break;
				case '_':
					direction = (memory == 0) ? 1 : 3;
					nextRow = row + Dx[direction];
					nextCol = col + Dy[direction];
					
					if (direction == 1) {
						if (!validateRange(nextRow, nextCol)) {
							nextCol = 1;
						}
					}
					
					if (direction == 3) {
						if (!validateRange(nextRow, nextCol)) {
							nextCol = C;
						}
					}
					
					if (!Visited[nextRow][nextCol][memory][direction]) {
						Visited[nextRow][nextCol][memory][direction] = true;
						Queue.add(new Pos(nextRow, nextCol, memory, direction));
					}
				
					break;
				case '|':
					direction = (memory == 0) ? 2 : 0;
					nextRow = row + Dx[direction];
					nextCol = col + Dy[direction];
					
					if (direction == 2) {
						if (!validateRange(nextRow, nextCol)) {
							nextRow = 1;
						}
					}
					
					if (direction == 0) {
						if (!validateRange(nextRow, nextCol)) {
							nextRow = R;
						}
					}
					
					if (!Visited[nextRow][nextCol][memory][direction]) {
						Visited[nextRow][nextCol][memory][direction] = true;
						Queue.add(new Pos(nextRow, nextCol, memory, direction));
					}
					
					break;
				case '?':
					for (int d = 0; d < 4; d++) {
						direction = d;
						nextRow = row + Dx[direction];
						nextCol = col + Dy[direction];
						
						if (!validateRange(nextRow, nextCol)) {
							if (direction == 0) {
								nextRow = R;
							} else if (direction == 1) {
								nextCol = 1;
							} else if (direction == 2) {
								nextRow = 1;
							} else {
								nextCol = C;
							}
						}
						
						if (!Visited[nextRow][nextCol][memory][direction]) {
							Visited[nextRow][nextCol][memory][direction] = true;
							Queue.add(new Pos(nextRow, nextCol, memory, direction));
						}
					}
					
					break;
				case '.':
					nextRow = row + Dx[direction];
					nextCol = col + Dy[direction];
					
					if (!validateRange(nextRow, nextCol)) {
						if (direction == 0) {
							nextRow = R;
						} else if (direction == 1) {
							nextCol = 1;
						} else if (direction == 2) {
							nextRow = 1;
						} else {
							nextCol = C;
						}
					}
					
					if (!Visited[nextRow][nextCol][memory][direction]) {
						Visited[nextRow][nextCol][memory][direction] = true;
						Queue.add(new Pos(nextRow, nextCol, memory, direction));
					}
					
					
					break;
				case '@':
					found = true;
					break;
				case '0': case '1': case '2': case '3': case '4': 
				case '5': case '6': case '7': case '8': case '9':
					memory = command - '0';
					nextRow = row + Dx[direction];
					nextCol = col + Dy[direction];
					
					if (!validateRange(nextRow, nextCol)) {
						if (direction == 0) {
							nextRow = R;
						} else if (direction == 1) {
							nextCol = 1;
						} else if (direction == 2) {
							nextRow = 1;
						} else {
							nextCol = C;
						}
					} 
					
					if (!Visited[nextRow][nextCol][memory][direction]) {
						Visited[nextRow][nextCol][memory][direction] = true;
						Queue.add(new Pos(nextRow, nextCol, memory, direction));
					}
					
					break;
				case '+':
					if (memory == 15) {
						memory = 0;
					} else {
						memory++;
					}
					nextRow = row + Dx[direction];
					nextCol = col + Dy[direction];
					
					if (!validateRange(nextRow, nextCol)) {
						if (direction == 0) {
							nextRow = R;
						} else if (direction == 1) {
							nextCol = 1;
						} else if (direction == 2) {
							nextRow = 1;
						} else {
							nextCol = C;
						}
					} 
					
					if (!Visited[nextRow][nextCol][memory][direction]) {
						Visited[nextRow][nextCol][memory][direction] = true;
						Queue.add(new Pos(nextRow, nextCol, memory, direction));
					}
					
					break;
				case '-':
					if (memory == 0) {
						memory = 15;
					} else {
						memory--;
					}
					nextRow = row + Dx[direction];
					nextCol = col + Dy[direction];
					
					if (!validateRange(nextRow, nextCol)) {
						if (direction == 0) {
							nextRow = R;
						} else if (direction == 1) {
							nextCol = 1;
						} else if (direction == 2) {
							nextRow = 1;
						} else {
							nextCol = C;
						}
					} 
					
					if (!Visited[nextRow][nextCol][memory][direction]) {
						Visited[nextRow][nextCol][memory][direction] = true;
						Queue.add(new Pos(nextRow, nextCol, memory, direction));
					}
					
					break;
				}
			}
			
			if (found) {
				sb.append("#" + tc + " YES\n");
			} else {
				sb.append("#" + tc + " NO\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
