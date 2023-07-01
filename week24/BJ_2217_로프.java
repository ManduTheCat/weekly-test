import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] ropes = new int[n];
		for (int i = 0; i < n; i++) {
			ropes[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(ropes);
		
		int ans = 0;
		int cnt = 1;
		for (int i = n - 1; i >= 0; i--) {
			ans = Math.max(ans, ropes[i] * cnt++);
		}
		
		System.out.println(ans);
	}
	
}
