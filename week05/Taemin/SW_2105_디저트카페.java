import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_2105 {
	
	static class Node {
		int row;
		int col;
		int direction;
		ArrayList<Integer> desert;
		
		public Node(int row, int col, int direction) {
			this.row = row;
			this.col = col;
			this.direction = direction;
			this.desert = new ArrayList<Integer>();
		}
		
		public void add(int number) {
			this.desert.add(number);
		}
		
		public boolean isContain(int number) {
			return desert.contains(number);
		}
	}
	
	static int T, N, Max;
	static int[] Dx = { -1, -1, 1, 1 };
	static int[] Dy = { -1, 1, -1, 1 };
	static int[][] Map;
	
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= N && col >= 1 && col <= N);
	}
	
	public static void BFS(int row, int col) {
		Queue<Node> queue = new LinkedList<Node>();
		Node start = new Node(row, col, -1);
		start.add(Map[row][col]);
		queue.offer(start);
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int direction = node.direction;
			
			// 시작 노드이면
			if (direction == -1) {
				int checkRow = node.row + Dx[3];
				int checkCol = node.col + Dy[3];
				if (validateRange(checkRow, checkCol) && 
					!node.isContain(Map[checkRow][checkCol])) {
					
					Node next = new Node(checkRow, checkCol, 3);
					ArrayList<Integer> desert = node.desert;
					for (Integer d : desert) {
						next.add(d);
					}
					next.add(Map[checkRow][checkCol]);
					
					queue.offer(next);
					continue;
				}
			}
			
			// 시작 노드가 아니면 이전 direction에 따라 움직이는 범위가 달라진다
			// 움직이게 되는 장소가 node의 desert에 포함되면 안된다
			// 움직이게 되는 장소가 home이면 Max를 갱신한다
			
			if (direction == 3) {
				int[] possibleWay = new int[] {2, 3};
				for (int d = 0; d < possibleWay.length; d++) {
					int checkRow = node.row + Dx[possibleWay[d]];
					int checkCol = node.col + Dy[possibleWay[d]];
					
					// 범위 체크
					if (!validateRange(checkRow, checkCol)) continue;
					
					// 도착했다면
					if (checkRow == row && checkCol == col) {
						ArrayList<Integer> desert = node.desert;
						Max = Math.max(Max, desert.size());
						continue;
					}
					
					// 도착하지 않았다면
					if (!node.isContain(Map[checkRow][checkCol])) {
						Node next = new Node(checkRow, checkCol, possibleWay[d]);
						ArrayList<Integer> desert = node.desert;
						for (Integer de : desert) {
							next.add(de);
						}
						next.add(Map[checkRow][checkCol]);
						
						queue.offer(next);
						continue;
					}
				}
			}
			else if (direction == 2) {
				int[] possibleWay = new int[] {0, 2};
				for (int d = 0; d < possibleWay.length; d++) {
					int checkRow = node.row + Dx[possibleWay[d]];
					int checkCol = node.col + Dy[possibleWay[d]];
					
					// 범위 체크
					if (!validateRange(checkRow, checkCol)) continue;
					
					// 도착했다면
					if (checkRow == row && checkCol == col) {
						ArrayList<Integer> desert = node.desert;
						Max = Math.max(Max, desert.size());
						continue;
					}
					
					// 도착하지 않았다면
					if (!node.isContain(Map[checkRow][checkCol])) {
						Node next = new Node(checkRow, checkCol, possibleWay[d]);
						ArrayList<Integer> desert = node.desert;
						for (Integer de : desert) {
							next.add(de);
						}
						next.add(Map[checkRow][checkCol]);
						
						queue.offer(next);
						continue;
					}
				}
			}
			else if (direction == 1) {
				int[] possibleWay = new int[] {1};
				for (int d = 0; d < possibleWay.length; d++) {
					int checkRow = node.row + Dx[possibleWay[d]];
					int checkCol = node.col + Dy[possibleWay[d]];
					
					// 범위 체크
					if (!validateRange(checkRow, checkCol)) continue;
					
					// 도착했다면
					if (checkRow == row && checkCol == col) {
						ArrayList<Integer> desert = node.desert;
						Max = Math.max(Max, desert.size());
						continue;
					}
					
					// 도착하지 않았다면
					if (!node.isContain(Map[checkRow][checkCol])) {
						Node next = new Node(checkRow, checkCol, possibleWay[d]);
						ArrayList<Integer> desert = node.desert;
						for (Integer de : desert) {
							next.add(de);
						}
						next.add(Map[checkRow][checkCol]);
						
						queue.offer(next);
						continue;
					}
				}
			}
			else if (direction == 0) {
				int[] possibleWay = new int[] {0, 1};
				for (int d = 0; d < possibleWay.length; d++) {
					int checkRow = node.row + Dx[possibleWay[d]];
					int checkCol = node.col + Dy[possibleWay[d]];
					
					// 범위 체크
					if (!validateRange(checkRow, checkCol)) continue;
					
					// 도착했다면
					if (checkRow == row && checkCol == col) {
						ArrayList<Integer> desert = node.desert;
						Max = Math.max(Max, desert.size());
						continue;
					}
					
					// 도착하지 않았다면
					if (!node.isContain(Map[checkRow][checkCol])) {
						Node next = new Node(checkRow, checkCol, possibleWay[d]);
						ArrayList<Integer> desert = node.desert;
						for (Integer de : desert) {
							next.add(de);
						}
						next.add(Map[checkRow][checkCol]);
						
						queue.offer(next);
						continue;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			Map = new int[N+1][N+1];
			Max = -1;
			
			for (int row = 1; row <= N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 1; col <= N; col++) {
					Map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int row = 1; row <= N; row++) {
				for (int col = 1; col <= N; col++) {
					BFS(row, col);
				}
			}
			
			sb.append("#" + tc + " " + Max + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
