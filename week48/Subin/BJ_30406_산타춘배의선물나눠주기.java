import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_30406_산타춘배의선물나눠주기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] values = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(st.nextToken());
            ++values[val];
        }

        int ans = 0;
        int[][][] xor = {
                {{0, 3}, {1, 2}}, // xor 3
                {{0, 2}, {1, 3}}, // xor 2
                {{0, 1}, {2, 3}}}; // xor 1

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                while (values[xor[i][j][0]] > 0 && values[xor[i][j][1]] > 0) {
                    --values[xor[i][j][0]];
                    --values[xor[i][j][1]];
                    ans += (3 - i);
                }
            }
        }

        System.out.println(ans);
    }

}
