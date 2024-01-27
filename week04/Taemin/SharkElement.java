import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	
	static class Node {
		int row;
		int col;
		int empty;
		int favorite;
		
		public Node(int row, int col, int empty, int favorite) {
			this.row = row;
			this.col = col;
			this.empty = empty;
			this.favorite = favorite;
		}
	}
	
	static int N;
	static ArrayList<Integer> Sequence;
	static ArrayList<ArrayList<Integer>> Favorite;
	static ArrayList<ArrayList<Node>> Adjacency;
	static ArrayList<ArrayList<Node>> EmptyAdj;
	static ArrayList<ArrayList<Node>> SortInRow;
	
	/* 4 directions to check */
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	/* Map */
	static int[][] Map;
	
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= N && col >= 1 && col <= N);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		Map = new int[N+1][N+1];
		
		Sequence = new ArrayList<Integer>();
		Favorite = new ArrayList<ArrayList<Integer>>();

		
		for (int i = 0; i <= N * N; i++) {
			Favorite.add(new ArrayList<Integer>());
		}
		

		
		for (int i = 1; i <= N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			
			Sequence.add(student);
			
			for (int j = 1; j <= 4; j++) {
				Favorite.get(student).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		

	
		for (int i = 0; i < N * N; i++) {
			int maxFavorite = 0;
			
			// (1) 비어있는 칸 중에서 좋아하는 학생이 인접 칸에 가장 많은 칸 찾기
			int student = Sequence.get(i); 
			
			Adjacency = new ArrayList<ArrayList<Node>>();
			for (int j = 0; j <= 4; j++) {
				Adjacency.add(new ArrayList<Node>());
			}
			
			for (int row = 1; row <= N; row++) {
				for (int col = 1; col <= N; col++) {
					
					if (Map[row][col] != 0) continue;
					
					int empty = 0;
					int favorite = 0;
					
					for (int d = 0; d < 4; d++) {
						int checkRow = row + Dx[d];
						int checkCol = col + Dy[d];
						
						if (!validateRange(checkRow, checkCol)) continue;
						
						if (Map[checkRow][checkCol] == 0) empty++;
						
						ArrayList<Integer> checkFavorite = Favorite.get(student);
						for (Integer cf : checkFavorite) {
							if (Map[checkRow][checkCol] == cf) {
								favorite++;
								break;
							}
						}
					}
					
					if (favorite > maxFavorite) {
						maxFavorite = favorite;
					}
					
					Adjacency.get(favorite).add(new Node(row, col, empty, favorite));
				}
			}
			
			// 찾은 경우
			if (Adjacency.get(maxFavorite).size() == 1) {
				Node node = Adjacency.get(maxFavorite).get(0);
				Map[node.row][node.col] = student;
				continue;
			}
			
			// (2) (1) 칸이 여러개면 인접한 칸 중에서 비어있는 칸이 가장 많은 칸
			int maxEmpty = 0;
			EmptyAdj = new ArrayList<ArrayList<Node>>();
			for (int j = 0; j <= 4; j++) {
				EmptyAdj.add(new ArrayList<Node>());
			}
			
			for (Node node : Adjacency.get(maxFavorite)) {
				int empty = node.empty;
				if (empty > maxEmpty) maxEmpty = empty;
				
				EmptyAdj.get(empty).add(node);
			}
			
			
			// 찾은 경우
			if (EmptyAdj.get(maxEmpty).size() == 1) {
				Node node = EmptyAdj.get(maxEmpty).get(0);
				Map[node.row][node.col]= student;
				continue;
			}
			
			
			// (3) (2) 칸이 여러개면 행의 번호가 작은 것, 열의 번호가 작은 것
			int minRow = N + 1;
			SortInRow = new ArrayList<ArrayList<Node>>();
			for (int j = 0; j <= N; j++) {
				SortInRow.add(new ArrayList<Node>());
			}
			
			for (Node node : EmptyAdj.get(maxEmpty)) {
				int row = node.row;
				if (minRow > row) minRow = row;
				
				SortInRow.get(row).add(node);
			}
			
			// 찾은 경우
			if (SortInRow.get(minRow).size() == 1) {
				Node node = SortInRow.get(minRow).get(0);
				Map[node.row][node.col]= student;
				continue;
			}
			
			int minCol = N + 1;
			Node resultNode = null;
			for (Node node : SortInRow.get(minRow)) {
				if (minCol > node.col) {
					minCol = node.col;
					resultNode = node;
				}
			}
			
			Map[resultNode.row][resultNode.col] = student;
			continue;
		}
	
//		// DEBUG
//		System.out.println();
//		for (int row = 1; row <= N; row++) {
//			System.out.println();
//			for (int col = 1; col <= N; col++) {
//				System.out.print(Map[row][col] + " ");
//			}
//		}
		
		int answer = 0;
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= N; col++) {
				int student = Map[row][col];
				int count = 0;
				
				for (int d = 0; d < 4; d++) {
					int checkRow = row + Dx[d];
					int checkCol = col + Dy[d];
					
					if (!validateRange(checkRow, checkCol)) continue;
					
					for (Integer check : Favorite.get(student)) {
						if (check == Map[checkRow][checkCol]) {
							count++;
							break;
						}
					}
				}
				
				switch(count) {
				case 0:
					answer += 0;
					break;
				case 1:
					answer += 1;
					break;
				case 2:
					answer += 10;
					break;
				case 3:
					answer += 100;
					break;
				case 4:
					answer += 1000;
					break;
				}
				
			}
		}
		
		System.out.println(answer);

	}
}
