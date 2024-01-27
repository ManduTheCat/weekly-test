import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] isEven = new boolean[S];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            int n = Integer.parseInt(st.nextToken());
            isEven[i] = n % 2 == 0 ? true : false;
        }

        int l = 0;
        int r = 0;
        int cnt = 0;
        int answer = 0;

        while (r < S) {
            if (cnt <= K) {
                if (!isEven[r++]) ++cnt;
            } else if (cnt > K) {
                if (!isEven[l++]) --cnt;
            }

            if (cnt <= K) answer = Math.max(answer, r - l - cnt);
        }

        System.out.println(answer);
    }

}
