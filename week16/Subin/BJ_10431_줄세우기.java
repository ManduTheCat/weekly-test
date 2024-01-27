import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_10431_줄세우기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int P = Integer.parseInt(br.readLine());
		
		while (P-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int T = Integer.parseInt(st.nextToken());
			
			int[] h = new int[20];
			for (int i = 0; i < 20; i++) {
				h[i] = Integer.parseInt(st.nextToken());
			}
			
			int ans = 0;
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				int cnt = 0;
				for (int l: list) {
					if (l < h[i]) {
						++cnt;
					} else {
						break;
					}
				}
				ans += list.size() - cnt;
				list.add(cnt, h[i]);
			}
			
			System.out.println(T + " " + ans);
		}
	}
	
}
