import java.util.Scanner;

public class BJ_1157_단어공부_안수빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 단어를 받아서 대문자로 바꾸기
		String word = sc.next();
		word = word.toUpperCase();
		
		int[] flag = new int[26];
		
		// 단어를 돌며 알파벳의 개수 세기
		for (char c: word.toCharArray()) {
			++flag[c - 'A'];
		}
		
		int max = 0;
		char ans = '?';
		
		// 가장 많이 등장하는 알파벳 구하기
		for (int i = 0; i < 26; i++) {
			if (flag[i] > max) {
				max = flag[i];
				ans = (char)('A' + i);
			} else if (flag[i] == max) {
				ans = '?';
			}
		}
		
		System.out.println(ans);
		
		sc.close();
	}
	
}
