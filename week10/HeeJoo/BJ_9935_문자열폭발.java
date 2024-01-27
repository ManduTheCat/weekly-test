package saturday.sat230107;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

/*
 * 단계 1. 폭발 문자열을 포함하고 있는 경우, 모든 폭발 문자열 폭발.
 * 단계 2. 남은 문자열을 순서대로 이어 붙여 새로운 문자열 만들기
 * 단계 3. 폭발 문자열이 없을 때까지 반복
 */
public class BJ_9935_문자열폭발 {
    static final String MOD = "FRULA";
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine(); // 입력 문자열
        String bomb = br.readLine(); // 폭발 문자열

        Stack<Character> stack = new Stack<>(); // 완성 문자열
        Stack<Character> stack2 = new Stack<>(); // 임시 폭발 문자열
        for(int i = input.length() - 1; i >= 0; i--) { // 스택이라 거꾸로 확인
            char target = input.charAt(i);

            if(target == bomb.charAt(0)) { // 현재 지정된 문자가 폭발 문자열의 시작 문자와 같다면, 폭발 문자열이 되는지 확인
                stack2.push(target);

                for(int j = 1; j < bomb.length(); j++) { // 폭발 문자열 길이만큼 확인
                    if(!stack.isEmpty() && stack.peek() == bomb.charAt(j)) { // 폭발 문자열과 같은 순서일 때만 임시 폭발 문자열에 추가
                        stack2.push(stack.pop());
                    } else {
                        break;
                    }
                }

                if (stack2.size() == bomb.length()) { // 임시 폭발 문자열과 주어진 폭발 문자열의 길이가 같으면 폭발 문자열이 완성됨을 의미함
                    stack2.clear();
                } else {
                    while(!stack2.isEmpty()) { // 폭발 문자열 미완성인 경우 다시 뱉기
                        stack.push(stack2.pop());
                    }
                }
            } else { // 폭발 문자열의 시작 문자가 아닌 경우 그냥 넣어 ~
                stack.push(target);
            }
        }

        StringBuilder sb = new StringBuilder();
        if(stack.size() == 0) { // 다 사라진 경우 "FRULA" 출력
            System.out.println(MOD);
        } else {
            while(!stack.isEmpty()) { // 완성된 문자열 출력
                sb.append(stack.pop());
            }

            System.out.println(sb);
        }
    }
}
