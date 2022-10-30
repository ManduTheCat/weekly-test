import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2240_자두나무 {
	
	static class Plum {
		int count;
		int tree;
		int moved;
		
		public Plum(int count, int tree, int moved) {
			this.count = count;
			this.tree = tree;
			this.moved = moved;
		}

		@Override
		public String toString() {
			return "Plum [" + count + ", " + tree + ", " + moved + "]";
		}
	}
	
	static int T, W;
	static int[] Tree;
	static Plum[][] Table;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		Tree = new int[T+1];
		for (int i = 1; i <= T; i++) {
			Tree[i] = Integer.parseInt(br.readLine());
		}
		
		Table = new Plum[W+1][T+1];
		
		// 초기화
		for (int w = 0; w <= W; w++) {
			Table[w][0] = new Plum(0, 1, 0);
		}
		
		for (int t = 1; t <= T; t++) {
			if (Tree[t] == 1) {
				Table[0][t] = new Plum(Table[0][t-1].count + 1, 1, 0);
			} else {
				Table[0][t] = new Plum(Table[0][t-1].count, 1, 0);
			}
		}
		
		// 테이블 갱신 
		for (int w = 1; w <= W; w++) {
			for (int t = 1; t <= T; t++) {
				Plum left = Table[w][t-1];
				Plum up = Table[w-1][t];
				int plum = Tree[t];
				int count = 0;
				int tree = 0;
				int moved = 0;
				
				if (left.tree == plum) {					
					count = left.count + 1;
					tree = plum;
					moved = left.moved;
				}
				else {
					if (left.moved < w) {
						count = left.count + 1;
						tree = plum;
						moved = left.moved + 1;
					}
					else {
						count = left.count;
						tree = left.tree;
						moved = left.moved;
					}
				}
				
				if (count > up.count) {
					Table[w][t] = new Plum(count, tree, moved);
				} else if (count < up.count) {
					Table[w][t] = new Plum(up.count, up.tree, up.moved);
				} else {
					// 같을 경우 
					if (up.moved <= moved) {
						Table[w][t] = new Plum(up.count, up.tree, up.moved);
					} else {
						Table[w][t] = new Plum(count, tree, moved);
					}
				}
			}
		}
		
//		// 디버그 
//		for (int w = 0; w <= W; w++) {
//			for (int t = 0; t <= T; t++) {
//				System.out.print(Table[w][t] + "\t");
//			}
//			System.out.println();
//		}
		
		
		System.out.println(Table[W][T].count);
	}

}
