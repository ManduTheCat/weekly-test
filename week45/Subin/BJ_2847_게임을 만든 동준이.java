import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] scores = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            scores[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        int before = scores[0];
        for (int i = 1; i < n; i++) {
            if (scores[i] >= before) {
                answer += scores[i] - (before - 1);
                scores[i] = before - 1;
            }
            before = scores[i];
        }

        System.out.println(answer);
    }

}
