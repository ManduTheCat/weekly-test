package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_1863_스카이라인쉬운거 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while(!stack.empty() && stack.peek() > y) {
                ans++;
                stack.pop();
            }

            if(!stack.empty() && stack.peek() == y) {
                continue;
            }
            stack.push(y);

        }
        while(!stack.empty()) {
            if(stack.pop() > 0) {
                ans++;
            }
        }

        System.out.println(ans);
    }

}
