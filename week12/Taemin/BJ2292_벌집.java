import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2292_벌집 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        double param = 0;

        while (true) {
            param++;
            if (func(param) >= n) break;
        }

        System.out.println((int)param);
    }

    public static double func(double param) {
        return 3 * Math.pow(param, 2) - 3 * param + 1;
    }
}
