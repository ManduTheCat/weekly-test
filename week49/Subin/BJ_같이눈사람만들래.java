import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://lovelyunsh.tistory.com/233
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] h = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(h);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int elsa = h[i] + h[j];
                int start = 0, end = n - 1;

                while (start < end) {
                    if (start == i || start ==j) {
                        ++start;
                        continue;
                    }
                    if (end == i || end == j) {
                        --end;
                        continue;
                    }

                    int anna = h[start] + h[end];
                    min = Math.min(min, Math.abs(elsa - anna));

                    if (elsa > anna) ++start;
                    else if (elsa < anna) --end;
                    else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }

        System.out.println(min);
    }

}
