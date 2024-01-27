import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_8979_올림픽_안수빈 {
	
	private static int stoi(String s) { return Integer.parseInt(s); } // parse

	static int N, K;
	
	private static class Info implements Comparable<Info> {
		int no;
		int gold;
		int silver;
		int bronze;
		
		public Info(int no, int gold, int silver, int bronze) {
			this.no = no;
			this.gold = gold;
			this.silver = silver;
			this.bronze = bronze;
		}

		@Override
		public int compareTo(Info o) {
			int comp1 = Integer.compare(o.gold, this.gold);
			if (comp1 == 0) {
				int comp2 = Integer.compare(o.silver, this.silver);
				if (comp2 == 0) {
					return Integer.compare(o.bronze, this.bronze); 
				}
				return comp2;
			}
			return comp1;
		}
		
	}
	
	// 1. 금메달 수
	// 2. 은메달 수
	// 3. 동메달 수
	// 로 정렬
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		K = stoi(st.nextToken());
		
		Info[] infos = new Info[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int no = stoi(st.nextToken()); // 나라 번호
			int gold = stoi(st.nextToken());
			int silver = stoi(st.nextToken());
			int bronze = stoi(st.nextToken());
			infos[i] = new Info(no, gold, silver, bronze);
		}
		Arrays.sort(infos);

		int ans = 0;
		for (int i = 0; i < N; i++) {
			if (infos[i].no == K) {
				ans = i;
				break;
			}
		}
		
		int cnt = 0;
		for (int i = ans - 1; i >= 0; i--) {
			if (infos[i].gold != infos[ans].gold || 
				infos[i].silver != infos[ans].silver || 
				infos[i].bronze != infos[ans].bronze) break;
			++cnt;
		}
		
		System.out.println(ans + 1 - cnt);
	}
	
}
