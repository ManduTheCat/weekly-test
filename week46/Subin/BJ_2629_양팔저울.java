import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://arinnh.tistory.com/4
public class BJ_2629_양팔저울 {

    private static int N, M;
    private static int[] weights;
    private static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        weights = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        // 가능한 추의 조합 구하기
        dp = new boolean[N + 1][15001]; // (500 * 30) + 1
        comb(0, 0);

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int q = Integer.parseInt(st.nextToken());
            boolean avail = false;
            if (q <= 15000 && dp[N][q]) avail = true; // 구슬의 무게가 15000을 넘어가면 불가능. 추의 최대 무게가 15000이다.

            sb.append(avail ? "Y " : "N ");
        }

        System.out.println(sb);
    }

    private static void comb(int cnt, int weight) {
        if (dp[cnt][weight]) return;
        dp[cnt][weight] = true;

        if (cnt == N) return;

        comb(cnt + 1, weight + weights[cnt]); // 현재 추를 구슬이 놓이지 않은 저울에 올린다.
        comb(cnt + 1, weight); // 추를 올리지 않는다.
        comb(cnt + 1, Math.abs(weight - weights[cnt])); // 현재 추를 구슬이 놓인 저울에 올린다.
    }

}
