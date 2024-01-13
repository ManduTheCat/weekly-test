import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0, r = n - 1;
        int answer = -1;
        int min = Integer.MAX_VALUE;
        while (l < r) {
            int s = a[l] + a[r];

            if (Math.abs(s) < min) {
                min = Math.abs(s);
                answer = s;
            }

            if (s < 0) ++l;
            else --r;
        }

        System.out.println(answer);
    }


}
