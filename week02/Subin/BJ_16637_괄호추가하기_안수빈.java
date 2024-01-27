import java.util.Scanner;

public class BJ_16637_괄호추가하기_안수빈 {
	
	static int N, ans = Integer.MIN_VALUE;
	static char[] input;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		input = sc.next().toCharArray();
		
		solve(1, input[0] - '0');
		System.out.println(ans);
		
		sc.close();
	}
	
	public static void solve(int idx, int sum) {
		if (idx >= N - 1) {
			ans = Math.max(ans, sum); // 최대값 갱신
			return;
		}
		
		if (idx < N - 3) // 뒤에 괄호를 추가할 수 있다면
			solve(idx + 4, calc(sum, input[idx], calc(input[idx + 1] - '0', input[idx + 2], input[idx + 3] - '0'))); // 뒤 먼저 계산
		solve(idx + 2, calc(sum, input[idx], input[idx + 1] - '0')); // 앞 먼저 계산
		
	}
	
	public static int calc(int n1, char oper, int n2) {
		switch (oper) {
		case '+':
			return n1 + n2;
		case '-':
			return n1 - n2;
		default: // *
			return n1 * n2;
		}
	}
}
