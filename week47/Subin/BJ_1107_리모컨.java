import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1107_리모컨 {

    private static int N, M, answer;
    private static boolean[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        num = new boolean[10];
        Arrays.fill(num, true);

        StringTokenizer st = null;
        if (M > 0) st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            num[Integer.parseInt(st.nextToken())] = false;
        }

        answer = Math.abs(N - 100);
        findAnswer(0, 0);

        System.out.println(answer);
    }

    private static void findAnswer(int channel, int cnt) {
        if (cnt > 6) return;
        if (cnt > 0) answer = Math.min(answer, cnt + Math.abs(N - channel));

        for (int i = 0; i < 10; i++) {
            if (num[i]) findAnswer(channel * 10 + i, cnt + 1);
        }
    }

}
