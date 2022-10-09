import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17136 {

	static int Min;
	static int[] UsedPaper;
	static int[][] Map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		Min = (int)1e9;
		UsedPaper = new int[6];
		Map = new int[11][11];
		
		for (int row = 1; row <= 10; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col <= 10; col++) {
				Map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(0, 1, 1);
		
		if (Min == (int)1e9) {
			System.out.println(-1);
		} else {
			System.out.println(Min);
		}
	}
	
	public static void DFS(int count, int row, int col) {
		if (row == 10 && col > 10) {
			Min = Math.min(Min, count);
			return;
		}
		
		if (col > 10) {
			row = row + 1;
			col = 1;
		}
		
		
		if (Map[row][col] == 1) {
			for (int size = 5; size >= 1; size--) {
				if (UsedPaper[size] == 5) continue;
				if (check(size, row, col)) {
					// 사용
					changeMap(size, row, col, 0);
					UsedPaper[size]++;
					// DFS
					DFS(count+1, row, col + 1);
					// 복귀
					changeMap(size, row, col, 1);
					UsedPaper[size]--;
				}
			}
		}
		else {
			DFS(count, row, col + 1);
		}
		
	}
	
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= 10 && col >= 1 && col <= 10);
	}
	
	public static void changeMap(int size, int row, int col, int flag) {
		for (int r = row; r < row + size; r++) {
			for (int c = col; c < col + size; c++) {
				Map[r][c] = flag;
			}
		}
	}
	
	public static boolean check(int size, int row, int col) {
		for (int r = row; r < row + size; r++) {
			for (int c = col; c < col + size; c++) {
				if (!validateRange(r,c)) return false;
				if (Map[r][c] != 1) {
					return false;
				}
			}
		}
		
		return true;
	}

}
