import java.util.Scanner;

public class BJ_2292_벌집_안수빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// 1, 7, 19, 37, 61
		// +6 +12 +18 +24...
		int six = 6;
		int cnt = 1, ans = 1;
		while (cnt < n) {
			cnt += six;
			six += 6;
			++ans;
		}
		
		System.out.println(ans);
	}
	
}
