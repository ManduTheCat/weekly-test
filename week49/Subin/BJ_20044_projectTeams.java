import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] w = new int[n * 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n * 2; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(w);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, w[i] +  w[n * 2 - i - 1]);
        }
        System.out.println(min);
    }

}
