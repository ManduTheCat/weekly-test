import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1486_장훈이의높은선반 {
	
	static int Testcase, NumOfClark, Shelf, MinDiff;
	static boolean[] Checked;
	static int[] Heights;
	
	
	public static void combi(int target, int depth, int start) {
		if (depth == target) {
			int total = 0;
			for (int i = 0; i < NumOfClark; i++) {
				if (Checked[i]) {
					total += Heights[i];
				}
			}
			
			if (total >= Shelf) {
				MinDiff = Math.min(MinDiff, total - Shelf);
			}
			
			return;
		}
		
		for (int i = start; i < NumOfClark; i++) {
			if (Checked[i]) continue;
			Checked[i] = true;
			combi(target, depth+1, i);
			Checked[i] = false;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		Testcase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= Testcase; tc++) {
			st = new StringTokenizer(br.readLine());
			NumOfClark = Integer.parseInt(st.nextToken());
			Shelf = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			Heights = new int[NumOfClark];
			for (int i = 0; i < NumOfClark; i++) {
				Heights[i] = Integer.parseInt(st.nextToken());
			}
			
			//Combi
			MinDiff = Integer.MAX_VALUE;
			for (int target = 1; target <= NumOfClark; target++) {
				Checked = new boolean[NumOfClark];
				combi(target, 0, 0);
			}
			
			sb.append("#" + tc + " " + MinDiff + "\n");
		}
		
		System.out.println(sb.toString());
	}

}
