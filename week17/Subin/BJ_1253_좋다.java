import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_1253_좋다 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int sum = A[i] + A[j];
                int idx = Arrays.binarySearch(A, sum);
                if (idx < 0 || idx == i || idx == j) continue; // 좋은 숫자가 존재할 때만 (0 0 3 3 3 3 거르기)
                set.add(sum);
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (set.contains(A[i])) ++ans;
        }

        System.out.println(ans);
    }

}
