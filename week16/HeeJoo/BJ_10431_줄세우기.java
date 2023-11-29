package saturday.sat230304;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 20명 고정. 키 오름차순 번호 부여(1 ~ 20)
 * 1. 아무나 한 명 맨 앞에 세우기
 * 2. 다음 한 명 맨 뒤에 세우기
 * 2-1. 자기보다 큰 사람이 앞에 없으면 제자리
 * 2-2. 자기보다 킅 사람이 앞에 있으면 그중 가장 앞에 있는 학생(A) 앞에 서기 ~ 이 때 A부터 그 뒤의 모든 학생들은 한 발씩 뒤로 물러선다.
 * 학생들은 총 몇 번 뒤로 물러설까 ?
 */
public class BJ_10431_줄세우기 {
    public static void main(String[] args) throws Exception {
        int ans = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int P = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for(int p = 0; p < P; p++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken()); // 테스트 케이스 번호

            Stack<Integer> stack = new Stack<>();
            Stack<Integer> stack2 = new Stack<>();
            stack.push(Integer.parseInt(st.nextToken()));
            for(int i = 1; i < 20; i++) {
                int now = Integer.parseInt(st.nextToken());
                if(stack.peek() > now) {
                    while(!stack.isEmpty() && stack.peek() > now) {
                        stack2.push(stack.pop());
                    }
                }

                stack.push(now);
                ans += stack2.size();

                while(!stack2.isEmpty()) {
                    stack.push(stack2.pop());
                }
            }

            sb.append(t + " " + ans +"\n");
            ans = 0;
        }

        System.out.print(sb);
    }
}
