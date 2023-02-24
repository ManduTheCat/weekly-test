import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_1863_스카이라인쉬운거 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while(!stack.isEmpty() && stack.peek() > y){
                count++;
                stack.pop();
            }

            if(!stack.isEmpty() && stack.peek() == y) continue;

            stack.push(y);
        }

        while(!stack.isEmpty()){
            if(stack.pop() > 0) count++;
        }
        System.out.println(count);
    }
}
