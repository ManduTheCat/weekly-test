import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_9935_문자열폭발_안수빈 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String word = br.readLine();
		String bomb = br.readLine();
		
		Stack<Character> s = new Stack<>();
		char last = bomb.charAt(bomb.length() - 1); // 스택에 들어간 문자와 폭발 문자열의 마지막 문자가 같으면 비교 시작 (구글링했음..)
		
		// word 하나씩 스택에 넣으면서 확인
		for (char w: word.toCharArray()) {
			s.push(w);
			
			if (w == last && s.size() >= bomb.length()) { // 마지막 문자와 같고 크기가 폭발 문자열의 길이보다 크면
				boolean flag = true; // 폭발 여부
				for (int i = 0, start = s.size() - bomb.length(); i < bomb.length(); i++) { // 폭발 문자열과 스택의 문자열 비교
					if (s.get(start + i) != bomb.charAt(i)) { // 일치하지 않으면 false 설정 후 break
						flag = false;
						break;
					}
				}
				if (flag) { // 폭발
					for (int i = 0; i < bomb.length(); i++) s.pop();
				}
			}
		}
		
		// 출력
		if (s.isEmpty()) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			while (!s.isEmpty()) {
				sb.append(s.pop());
			}
			sb.reverse();
			System.out.println(sb);
		}
	}
	
}
