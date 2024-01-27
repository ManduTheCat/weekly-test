import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_21921_블로그 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] visit = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            visit[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = X;

        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += visit[i];
        }
        int ans = sum;
        int cnt = 1;

        while (end < N) {
            sum -= visit[start++];
            sum += visit[end++];
            if (ans == sum) {
                ++cnt;
            } else if (ans < sum) {
                ans = sum;
                cnt = 1;
            }
        }

        if (ans == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(ans);
            System.out.println(cnt);
        }
    }

}
